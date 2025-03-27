package lab02.src.RoboBase;

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

    public String getNome(){
        return this.nome;
    } // usado

    public int getX(){
        return this.posicaoX;
    } // usado

    public int getY(){
        return this.posicaoY;
    } // usado

    public void mover(int posX, int posY){
        this.posicaoX = posX;
        this.posicaoY = posY;
    }

    public boolean identificarObstaculo(int obstaculos[][]){
        int j, i = 0;
        do {
            for (j = -1; j < 2; j++){
                if (obstaculos[i][0] == (this.posicaoX + j) && obstaculos[i][1] == (this.posicaoY + j)){
                    return true;
                }
            }
            i++;
        } while (i < obstaculos.length);
        return false;
    } // encontra o obstaculo a no maximo 1 posicao de distancia de X e/ou Y do robo
    // usado

    public void exibirPosicao(){
        System.out.printf("O robo %s esta na posicao (%d, %d) na direcao %s.", nome, posicaoX, posicaoY, direcao);
    } // usado
}
