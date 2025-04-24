package Ambiente;

import java.util.ArrayList;

import RobosBase.Robo;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int comprimento;
    private int altura;
    private ArrayList<Robo> RobosAtivos;
    private ArrayList<Obstaculos> obstaculos;
    //atributos do ambiente
    
    //construtor do ambiente
    public Ambiente(int a, int l, int c, ArrayList<Robo> RobosAtivos, ArrayList<Obstaculos> obstaculos){
        this.altura = a;
        this.largura = l;
        this.comprimento = c;
        this.RobosAtivos = RobosAtivos;
        this.obstaculos = obstaculos;
    }

    public void setLargura(int l){
        this.largura = l;
    } //muda a largura

    public void setAltura(int a){
        this.altura = a;
    } // muda a altura

    //metodos para mostrar as dimensoes do ambiente
    public int getLargura(){
        return this.largura;
    } 

    public int getAltura(){
        return this.altura;
    } 

    public int getComprimento(){
        return this.comprimento;
    }

    public ArrayList<Obstaculos> getObstaculos(){
        return this.obstaculos;
    }

    //metodo que verifica se a nova posicao do robo esta dentro dos limites das 3 dimensoes
    public boolean dentroDosLimites(int X, int Y, int Z){
        return ((0 <= X && X <= this.largura) && (0 <= Y && Y <= this.comprimento) && (0 <= Z && Z <= this.altura));
    }

    public void detectarColisoes() {
        for (Robo robo : RobosAtivos) {
            int x = robo.getX();
            int y = robo.getY();
            int z = robo.getZ();
    
            for (Obstaculos obs : obstaculos) {
                int x1 = obs.getX1();
                int y1 = obs.getY1();
                int x2 = obs.getX2();
                int y2 = obs.getY2();
                int h = obs.getObstaculo().getAltura();
    
                if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z <= h) {
                    System.out.printf("Colisão detectada: Robô %s está sobre o obstáculo %s (%d, %d, %d)", robo.getNome(), obs.getObstaculo().getTipo(), x, y, z);
                }
            }
        }
    }

    public int getResistenciaEm(int x, int y, int z) { // Retorna a resistencia de um obstaculo do ponto x, y
        for (Obstaculos obs : obstaculos) {
            int x1 = obs.getX1();
            int x2 = obs.getX2();
            int y1 = obs.getY1();
            int y2 = obs.getY1();
            int h = obs.getObstaculo().getAltura();
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z <= h) {
                int resistencia = obs.getObstaculo().getResistencia();
                return resistencia;
            }
        }
        return 0;
    }

    public boolean temObstaculoEm(int x, int y, int z) {
        for (Obstaculos obs : obstaculos) {
            int x1 = obs.getX1();
            int y1 = obs.getY1();
            int x2 = obs.getX2();
            int y2 = obs.getY2();
            int h = obs.getObstaculo().getAltura();
    
            // verifica se (x,y) está dentro do retângulo do obstáculo
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z <= h) {
                return true;
            }
        }
        return false;
    }

    public void removerObstaculoEm(int x, int y, int z) {
        for (Obstaculos obs : obstaculos) {
            int x1 = obs.getX1();
            int x2 = obs.getX2();
            int y1 = obs.getY1();
            int y2 = obs.getY2();
            int h = obs.getObstaculo().getAltura();
    
            if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z <= h) {
                obstaculos.remove(obs);
                System.out.println("Obstáculo removido na posição (" + x + ", " + y + ")");
                return;
            }
        }
    }

    public void setRobo(Robo r){
        this.RobosAtivos.add(r);
    } // metodo para adicionar robos ao ambiente

    public void setObstaculo(Obstaculos obstaculo){
        this.obstaculos.add(obstaculo);
    }

    public void removeObstaculo(Obstaculos obstaculo){
        this.obstaculos.remove(obstaculo);
    }
}
