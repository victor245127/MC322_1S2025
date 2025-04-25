package Ambiente;

// Classe de obstáculos
public class Obstaculos {
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private TiposObstaculo tipo;
    // Atributos dos obstáculos
    
    public Obstaculos(int x1, int y1, int x2, int y2, TiposObstaculo tipo){ // Construtor de obstáculo
        this.posicaoX1 = x1;
        this.posicaoY1 = y1;
        this.posicaoX2 = x2;
        this.posicaoY2 = y2;
        this.tipo = tipo;
    }

    // Métodos get que retornam os atributos do obstáculo
    public TiposObstaculo getTipo(){
        return this.tipo;
    }
    public int getResistencia(){
        return tipo.getResistenciaTipo();
    }
    
    public int getPosicaoX1() {
        return this.posicaoX1;
    }

    public int getPosicaoX2() {
        return this.posicaoX2;
    }

    public int getPosicaoY1() {
        return this.posicaoY1;
    }

    public int getPosicaoY2() {
        return this.posicaoY2;
    }
}