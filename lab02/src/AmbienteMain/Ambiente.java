package lab02.src.AmbienteMain;

import lab02.src.RoboBase.Robo;

public class Ambiente {
    private int largura;
    private int altura;
    private Robo RobosAtivos[];
    private int obstaculos[][];
    
    //construtor do ambiente
    public Ambiente(int a, int l, Robo RobosAtivos[], int obstaculos[][]){
        this.altura = a;
        this.largura = l;
        this.RobosAtivos = RobosAtivos;
        this.obstaculos = obstaculos;
    }

    public void setLargura(int l){
        this.largura = l;
    } // usado

    public void setAltura(int a){
        this.altura = a;
    } // usado

    //metodo para mostrar as dimensoes do ambiente
    public int getLargura(){
        return largura;
    } // usado

    public int getAltura(){
        return altura;
    } // usado

    //metodo que verifica se a nova posicao do robo esta dentro dos limites
    public boolean dentroDosLimites(int largura, int altura){
        if ((0 <= largura && largura <= this.largura) && (0<= altura && altura <= this.altura)){
            return true;
        }
        else {  
            return false;
        }
    } // usado

    public void setObstaculo(int X, int Y, int pos){
        this.obstaculos[pos][0] = X;
        this.obstaculos[pos][1] = Y;
    } // usado

    public void setRobo(Robo r, int pos){
        this.RobosAtivos[pos] = r;
    } // usado
}
