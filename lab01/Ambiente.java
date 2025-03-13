package lab01;

public class Ambiente {
    public int largura;
    public int altura;
    
    public Ambiente(int x, int y){
        this.altura = y;
        this.largura = x;
    }

    public boolean dentroDosLimites(int x, int y){
        //perguntar como fazer 0<=
        if (x <= largura && y <= altura){
            return true;
        }
        else {
            return false;
        }
    }
}
