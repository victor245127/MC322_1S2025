package Ambiente;

import java.util.ArrayList;

import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.EntidadeImovelException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RobosBase.Robo;
import RobosBase.RoboTerrestre;
import Sensor.Sensoreavel;

// classe do ambiente, que possui suas dimensões, mapa e entidades presentes
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
        this.mapa = new TipoEntidade[largura][profundidade][altura];
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

    public void adicionarEntidade(Entidade e) { // Adiciona uma entidade no mapa e no array de entidades
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            for (int i = e.getX()[0]; i < (e.getX()[1] + 1); i++){
                for (int j = e.getY()[0]; j < (e.getY()[1] + 1); j++){
                    for (int k = 0; k < (e.getZ()[0] + 1); k++){
                        this.mapa[i][j][k] = e.getTipo();
                    }
                } 
            }
        }
        else {
            this.mapa[e.getX()[0]][e.getY()[0]][e.getZ()[0]] = e.getTipo();
        }
        entidades.add(e);
    }

    public void removerEntidade(Entidade e) { // Remove uma entidade do mapa e do array de entidades
        if (e.getTipo() == TipoEntidade.OBSTACULO){
            for (int i = e.getX()[0]; i <= (e.getX()[1] + 1); i++){
                for (int j = e.getY()[0]; j <= (e.getY()[1] + 1); j++){
                    for (int k = 0; k <= (e.getZ()[0] + 1); k++){
                        this.mapa[i][j][k] = TipoEntidade.VAZIO;
                    }
                } 
            }
        }
        else {
            this.mapa[e.getX()[0]][e.getY()[0]][e.getZ()[0]] = TipoEntidade.VAZIO;
        }
        entidades.remove(e);
    }

    public boolean dentroDosLimites(int x, int y, int z) { // Verifica se uma posição está dentro dos limites
        // Tentativa de erro para caso esteja fora dos limites
        try {
            estaOcupado(x, y, z);
        } catch (ForaDosLimitesException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        // retorna se está dentro dos limites
        return (x>=0 && x<this.largura && y>=0 && y<this.profundidade && z >= 0 && z < this.altura);
    }

    public boolean estaOcupado(int x, int y, int z) throws ForaDosLimitesException{ // Verifica se uma certa posição está ocupada
        if (x<0 || x>=this.largura || y<0 || y>=this.profundidade || z < 0 || z >=this.altura){
            throw new ForaDosLimitesException();
        } // Procura excessão de fora dos limites
        if (mapa[x][y][z] == TipoEntidade.VAZIO){   
            return false;
        }
        return true;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ, int velNova){ // Move um robô de posição, caso o seja, 
        try {
            // caso não seja um robô, a entidade é imóvel
            if (e.getTipo() != TipoEntidade.ROBO){
                throw new EntidadeImovelException();
            }
            if (dentroDosLimites(novoX, novoY, novoZ)){
                // Caso esteja dentro do limite, muda a posição antiga no mapa para vazia e define a nova
                this.mapa[((Robo)e).getX()[0]][((Robo)e).getY()[0]][((Robo)e).getZ()[0]] = TipoEntidade.VAZIO;
                if (e instanceof RoboTerrestre){
                    ((RoboTerrestre)e).moverPara(novoX, novoY, 0, velNova);
                    this.mapa[novoX][novoY][0] = TipoEntidade.ROBO;
                }
                else {
                    ((Robo)e).moverPara(novoX, novoY, novoZ);
                    this.mapa[novoX][novoY][novoZ] = TipoEntidade.ROBO;
                }
                ((Robo) e).exibirPosicao();
                
            }
        } catch (RoboDesligadoException r){
            System.out.println("ERRO: " + r.getMessage());
        } catch (EntidadeImovelException h){
            System.out.println("ERRO: " + h.getMessage());
        } // Pega erros de entidade imóvel ou robô desligado
    }

    public void executarSensores(Ambiente ambiente) throws RoboDesligadoException, ColisaoException {
        // Aciona os sensores de todos os robôs com interface sensoreável
        for (Entidade ent : entidades){
            if (ent.getTipo() == TipoEntidade.ROBO){
                if (ent instanceof Sensoreavel){
                    ((Sensoreavel) ent).acionarSensores(ambiente);
                }
            }
        }
    }

    public void verificaColisoes() { // Método que verifica se há colisão com algum robô
        boolean erro = false;
        try {
            for (Entidade robo : getRobos()) {
                int x = ((Robo) robo).getX()[0];
                int y = ((Robo) robo).getY()[0];
                int z = ((Robo) robo).getZ()[0];
    
                // Verifica se um robô está na posição de um obstáculo
                for (Entidade obs : getObstaculos()) {
                    int x1 = ((Obstaculos) obs).getX()[0];
                    int y1 = ((Obstaculos) obs).getY()[0];
                    int x2 = ((Obstaculos) obs).getX()[1];
                    int y2 = ((Obstaculos) obs).getY()[1];
                    int h = ((Obstaculos) obs).getTipoObstaculo().getAltura();
                    if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z <= h) {
                        throw new ColisaoException(x, y, z);
                    }
                }
            }
        } catch (ColisaoException e){
            System.out.println("ERRO: " + e.getMessage());
            erro = true;
            // Caso haja colisões, lança a excessão de colisão
        }  finally {
            if (!erro){
                // caso a variável erro esteja falsa no fim de tudo, não há colisão no ambiente
                System.out.println("Sem colisoes no momento.");
            }
        }
    }


    public void visualizarAmbiente(){ // Mostra o ambiente em um plano XY
        System.out.println("Visao superior do plano XY:");
        for (int j = profundidade - 1; j >= 0; j--){
            for (int i = 0; i < largura; i++){
                TipoEntidade tipoVisivel = TipoEntidade.VAZIO;
                for (int k = profundidade-1; k >= 0; k--){
                    if (mapa[i][j][k] != TipoEntidade.VAZIO){
                        tipoVisivel = mapa[i][j][k];
                        break;
                    }
                }
                System.out.print(tipoVisivel.getRepresentacao(tipoVisivel));
            }
        System.out.println();
        }        
    }

    public void exibirRobos(){ // Exibe os robôs com as informações de nome e estado(ligado/desligado)
        int i = 1;
        for (Entidade r : getRobos()){
            System.out.printf("%d. %s - %s\n", i, ((Robo) r).getId(), ((Robo)r).getEstado());
            i++;
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
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h >= z) {
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
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h >= z) {
                removerEntidade(obstaculos.get(i));
                System.out.printf("Obstaculo removido na posicao (%d, %d, %d)\n", x, y, z);
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
    
            // verifica se (x,y, z) está dentro do paralelepípedo do obstáculo
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && h >= z) {
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