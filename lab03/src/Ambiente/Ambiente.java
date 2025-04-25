package Ambiente;

import java.util.ArrayList;
// Uso de arraylist para os obstáculos e robôs presentes no ambiente

import RobosBase.Robo;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int altura;
    private int profundidade;

    private ArrayList<Robo> robosAtivos;
    private ArrayList<Obstaculos> obstaculos;
    // Atributos do ambiente

    public Ambiente(int largura, int altura, int profundidade){ // Construtor do ambiente
        this.largura = largura;
        this.altura = altura;
        this.profundidade = profundidade;
        this.robosAtivos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
    }
    

    public void adicionarRobo(Robo r) { // Adiciona um robo
        robosAtivos.add(r);
    }

    public void removerRobo(Robo r) { // Remove um robo
        robosAtivos.remove(r);
    }

    public boolean dentroDosLimites(int x, int y) { // Verifica se uma posição X, Y está dentro do ambiente
        return (x >= 0 && x <= this.largura && y >= 0 && y <= this.altura);
    }

    public boolean dentroDosLimites(int x, int y, int z){ // Mesma de cima, mas para robôs aéreos
        return (x>=0 && x<=this.largura && y>=0 && y<=this.altura && z >= 0 && z <= this.profundidade);
    }

    public void adicionarObstaculo(Obstaculos obstaculo) { // Adiciona obstáculo
        this.obstaculos.add(obstaculo);
    }

    public void removerObstaculo(Obstaculos obstaculo) { // Remove obstáculo
        this.obstaculos.remove(obstaculo);
    }

    public int getResistenciaEm(int x, int y) { // Procura um obstáculo em X, Y e, caso tenha, retorna a resistência do obstáculo
        for (Obstaculos obs : obstaculos) { // Loop para verificar obstáculo por obstáculo, usado em vários métodos seguintes
            int x1 = obs.getPosicaoX1();
            int x2 = obs.getPosicaoX2();
            int y1 = obs.getPosicaoY1();
            int y2 = obs.getPosicaoY2();
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                int resistencia = obs.getResistencia();
                return resistencia;
            }
        }
        return 0;
    }

    public void removerObstaculoEm(int x, int y) { // Procura um obstáculo em X, Y e, caso tenha,
        for (Obstaculos obs : obstaculos) { // o remove do ambiente
            int x1 = obs.getPosicaoX1();
            int x2 = obs.getPosicaoX2();
            int y1 = obs.getPosicaoY1();
            int y2 = obs.getPosicaoY2();
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                obstaculos.remove(obs);
                System.out.printf("Obstáculo removido na posição (%d, %d)\n", x, y);
                return;
            }
        }
    }
    
    public boolean temObstaculoEm(int x, int y) { // Procura um obstáculo em X, Y
        for (Obstaculos obs : obstaculos) {
            int x1 = obs.getPosicaoX1();
            int y1 = obs.getPosicaoY1();
            int x2 = obs.getPosicaoX2();
            int y2 = obs.getPosicaoY2();
    
            // verifica se (x,y) está dentro do retângulo do obstáculo
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                return true;
            }
        }
        return false;
    }
    

    public void detectarColisoes() { // Método que verifica se há colisão com algum robô
        for (Robo robo : robosAtivos) {
            int x = robo.getPosX();
            int y = robo.getPosY();
    
            // Verifica se um robô está na posição de um obstáculo
            for (Obstaculos obs : obstaculos) {
                int x1 = obs.getPosicaoX1();
                int y1 = obs.getPosicaoY1();
                int x2 = obs.getPosicaoX2();
                int y2 = obs.getPosicaoY2();
    
                if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                    System.out.printf("Colisão detectada: Robô %s está sobre o obstáculo (%d, %d)\n", robo.getNome(), x, y);
                }
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

    public ArrayList<Robo> getRobosAtivos() {
        return robosAtivos;
    }
    
    public ArrayList<Obstaculos> getObstaculos(){
        return obstaculos;
    }
}