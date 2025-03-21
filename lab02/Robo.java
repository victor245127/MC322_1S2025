package lab02;

public class Robo {
    protected String nome;
    protected String direcao;
    protected int posicaoX;
    protected int posicaoY;

    public Robo (String nome, String direcao, int posicaoX, int posicaoY){
        this.nome = nome;
        this.direcao = direcao;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public void mover(int deltaX, int deltaY){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public boolean identificarObstaculo(int obstaculos[][], int x, int y){
        int i = 0;
        do {
            if (obstaculos[i][0] == x && obstaculos[i][1] == y){
                return true;
            }
            i++;
        } while (i < obstaculos.length);
        return false;
    } 

    public void exibirPosicao(){
        System.out.printf("O robo %s esta na posicao (%d, %d) na direcao %s.", nome, posicaoX, posicaoY, direcao);
    }
}
