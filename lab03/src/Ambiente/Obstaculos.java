package Ambiente;

public class Obstaculos {
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private TiposObstaculo tipo;

    public Obstaculos(int x1, int y1, int x2, int y2, TiposObstaculo tipo){
        this.posicaoX1 = x1;
        this.posicaoY1 = y1;
        this.posicaoX2 = x2;
        this.posicaoY2 = y2;
        this.tipo = tipo;
    }

    public int getX1(){
        return this.posicaoX1;
    }

    public int getX2(){
        return this.posicaoX2;
    }

    public int getY1(){
        return this.posicaoY1;
    }

    public int getY2(){
        return this.posicaoY2;
    }

    public void setX1(int X1){
        this.posicaoX1 = X1;
    }

    public void setX2(int X2){
        this.posicaoX2 = X2;
    }

    public void setY1(int Y1){
        this.posicaoY1 = Y1;
    }

    public void setY2(int Y2){
        this.posicaoY2 = Y2;
    }

    public void setTipoObstaculo(TiposObstaculo tipo){
        this.tipo = tipo;
    }

    public TiposObstaculo getObstaculo(){
        return this.tipo;
    }
}
