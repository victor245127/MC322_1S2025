package Ambiente;

import java.util.ArrayList;
// Uso de arraylist para os obstáculos e robôs presentes no ambiente

import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.EntidadeImovelException;
import Exceptions.ErroSensorException;
import Exceptions.RoboDesligadoException;
import RoboVariacoes.RoboTerrestreDestruidor;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import Sensor.Sensoreavel;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int altura;
    private int profundidade;

    private ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;
    // Atributos do ambiente

    public Ambiente(int largura, int altura, int profundidade){ // Construtor do ambiente
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.entidades = new ArrayList<>();
        this.mapa = new TipoEntidade[getLargura()][getProfundidade()][getAltura()];
    }
    
    public void inicializarMapa(){ // Inicializa o mapa com todas as posições vazias
        int x, y, z;
        for (x = 0; x < largura; x++){
            for (y = 0; y < profundidade; y++){
                for (z = 0; z < altura; z++){
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void adicionarEntidade(Entidade e) { // Adiciona uma entidade
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            for (int i = e.getX()[0]; i <= (e.getX()[1] + 1); i++){
                for (int j = e.getY()[0]; j <= (e.getY()[1] + 1); j++){
                    for (int k = 0; k <= (e.getZ()[0] + 1); k++){
                        mapa[i][j][k] = e.getTipo();
                    }
                } 
            }
        }
        else {
            mapa[e.getX()[0]][e.getY()[0]][e.getZ()[0]] = e.getTipo();
        }
        entidades.add(e);
    }

    public void removerEntidade(Entidade e) { // Remove uma entidade
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            for (int i = e.getX()[0]; i <= (e.getX()[1] + 1); i++){
                for (int j = e.getY()[0]; j <= (e.getY()[1] + 1); j++){
                    for (int k = 0; k <= (e.getZ()[0] + 1); k++){
                        mapa[i][j][k] = TipoEntidade.VAZIO;
                    }
                } 
            }
        }
        else {
            mapa[e.getX()[0]][e.getY()[0]][e.getZ()[0]] = TipoEntidade.VAZIO;
        }
        entidades.remove(e);
    }

    public boolean dentroDosLimites(int x, int y, int z) throws ColisaoException { // Verifica se uma posição está dentro dos limites
        if (estaOcupado(x, y, z)){
            throw new ColisaoException(x, y, z);
        }
        return (x>=0 && x<=this.largura && y>=0 && y<=this.altura && z >= 0 && z <= this.profundidade);
    }

    public boolean estaOcupado(int x, int y, int z){ // Verifica se uma certa posição está ocupada
        if (mapa[x][y][z] == TipoEntidade.VAZIO){   
            return false;
        }
        return true;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws EntidadeImovelException{ // Move um robô de posição, caso o seja, 
        if (e.getTipo() == TipoEntidade.ROBO){ // visto que é a única entidade móvel no mapa
            ((Robo)e).moverPara(novoX, novoY, novoZ);
        }
        throw new EntidadeImovelException();
    }

    public void executarSensores(Robo r, Ambiente ambiente) throws RoboDesligadoException, ErroSensorException, ColisaoException {// Aciona os sensores de um robô específico
        if (r instanceof Sensoreavel){
            if (r.getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }
            ((RoboTerrestreDestruidor) r).acionarSensores(ambiente);
        }
        throw new ErroSensorException(r.getId());
    }

    // ColisaoException
    public void verificaColisoes(){ // Método que verifica se há colisão com algum robô
        // IMPLEMENTAR APOS MEXER NAS OUTRAS CLASSES
    }

    public void visualizarAmbiente(){ // Mostra o ambiente em um plano XY
        for (int i = -1; i < (largura+1); i++){
            for (int j = -1; j < (profundidade+1); j++){
                if (i == -1 || i == largura || j == -1 || j == profundidade){
                    System.out.println("#"); // Desenhando as bordas do plano
                }
                for (int k = 0; k <= getAltura(); k++){ // Desenhando as entidades do ambiente
                    switch (mapa[i][j][k]) {
                    case TipoEntidade.ROBO:
                        System.out.println("R");
                        break;
                    case TipoEntidade.OBSTACULO:
                        System.out.println("O");
                        break;
                    case TipoEntidade.VAZIO:
                        System.out.println(" ");
                    default:
                        break;
                    }
                }
            }
        }
    }

    public int getResistenciaEm(int x, int y, int z) { // Procura um obstáculo em X, Y, Z e, caso tenha, retorna a resistência do obstáculo
        ArrayList<Entidade> obstaculos = getObstaculos();
        for (int i = 0; i < obstaculos.size(); i++) { // Loop para verificar obstáculo por obstáculo, usado em vários métodos seguintes
            int x1 = ((Obstaculos) obstaculos.get(i)).getX()[0];
            int x2 = ((Obstaculos) obstaculos.get(i)).getX()[1];
            int y1 = ((Obstaculos) obstaculos.get(i)).getY()[0];
            int y2 = ((Obstaculos) obstaculos.get(i)).getY()[1];
            int h = ((Obstaculos) obstaculos.get(i)).getZ()[0];
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h <= z) {
                int resistencia = ((Obstaculos) obstaculos.get(i)).getResistencia();
                return resistencia;
            }
        }
        return 0;
    }

     public void removerObstaculoEm(int x, int y, int z) { // Procura um obstáculo em X, Y e, caso tenha, o remove do ambiente
        ArrayList<Entidade> obstaculos = getObstaculos();
        for (int i = 0; i < obstaculos.size(); i++) { 
            int x1 = ((Obstaculos) obstaculos.get(i)).getX()[0];
            int x2 = ((Obstaculos) obstaculos.get(i)).getX()[1];
            int y1 = ((Obstaculos) obstaculos.get(i)).getY()[0];
            int y2 = ((Obstaculos) obstaculos.get(i)).getY()[1];
            int h = ((Obstaculos) obstaculos.get(i)).getTipoObstaculo().getAltura();
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h <= z) {
                removerEntidade(obstaculos.get(i));
                System.out.printf("Obstáculo removido na posição (%d, %d)\n", x, y);
                return;
            }
        }
    }

    public boolean temObstaculoEm(int x, int y, int z) { // Procura um obstáculo em X, Y, Z
        ArrayList<Entidade> obstaculos = getObstaculos();
        for (int i = 0; i < obstaculos.size(); i++) {
            int x1 = ((Obstaculos) obstaculos.get(i)).getX()[0];
            int y1 = ((Obstaculos) obstaculos.get(i)).getY()[0];
            int x2 = ((Obstaculos) obstaculos.get(i)).getX()[1];
            int y2 = ((Obstaculos) obstaculos.get(i)).getY()[1];
            int h = ((Obstaculos) obstaculos.get(i)).getTipoObstaculo().getAltura();
    
            // verifica se (x,y) está dentro do retângulo do obstáculo
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h <= z) {
                return true;
            }
        }
        return false;
    }
    
    // Métodos get para retornar atributos do ambiente
    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public ArrayList<Entidade> getEntidades(){
        return entidades;
    }

    // Retorna uma lista com todos os robôs presentes no ambiente
    public ArrayList<Entidade> getRobos(){
        ArrayList<Entidade> robos = new ArrayList<>();
        for (int i = 0; i < entidades.size(); i++){
            if (entidades.get(i).getTipo() == TipoEntidade.ROBO){
                robos.add(entidades.get(i));
            }
        }
        return robos;
    }

    // Retorna uma lista com todos os obstáculos presentes no ambiente
    public ArrayList<Entidade> getObstaculos(){
        ArrayList<Entidade> obstaculos = new ArrayList<>();
        for (int i = 0; i < entidades.size(); i++){
            if (entidades.get(i).getTipo() == TipoEntidade.OBSTACULO){
                obstaculos.add(entidades.get(i));
            }
        }
        return obstaculos;
    }
}