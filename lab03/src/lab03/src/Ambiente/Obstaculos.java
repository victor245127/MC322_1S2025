package lab03.src.Ambiente;

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

    public int getTamanhoX(){
        return (this.posicaoX2 - this.posicaoX1);
    }

    public int getTamanhoY(){
        return (this.posicaoY2 - this.posicaoY1);
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

    public TiposObstaculo getObstaculo(){
        return this.tipo;
    }
}
