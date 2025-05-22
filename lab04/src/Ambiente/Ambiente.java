package Ambiente;

import java.util.ArrayList;
// Uso de arraylist para os obstáculos e robôs presentes no ambiente

import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.EntidadeImovelException;
import Exceptions.ErroSensorException;
import Exceptions.RoboDesligadoException;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import RobosBase.RoboTerrestre;
import Sensor.Sensoreavel;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int altura;
    private int profundidade;

    private ArrayList<Entidade> entidades;
    private TipoEntidade[][][] mapa;
    // Atributos do ambiente

    public Ambiente(int largura, int altura, int profundidade, TipoEntidade[][][] mapa){ // Construtor do ambiente
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.entidades = new ArrayList<>();
        this.mapa = mapa;
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
        entidades.add(e);
    }

    public void removerEntidade(Entidade e) { // Remove uma entidade
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

    public void executarSensores(Robo r) throws RoboDesligadoException, ErroSensorException {// Aciona os sensores de um robô específico
        if (r instanceof Sensoreavel){
            if (r.getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }
            ((RoboTerrestre) r).acionarSensores();
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
                // FAZER IFS DAS ENTIDADES PARA PRINTAR SEU CHAR NA POSICAO CERTA
            }
        }
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
}