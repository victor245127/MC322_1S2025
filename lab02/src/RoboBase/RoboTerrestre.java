package lab02.src.RoboBase;

public class RoboTerrestre extends Robo {
    protected int velocidadeMax;
    protected int velocidadeAtual;

    public RoboTerrestre (String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual){
        super(nome, direcao, posicaoX, posicaoY);
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
    }

    private void setVelocidade(int vel){
        this.velocidadeAtual = vel;
    } //usado

    private int getVelocidade(){
        return this.velocidadeAtual;
    } // usado

    public void mover (int deltaX, int deltaY, int velNova){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
        if (velNova <= velocidadeMax){
            setVelocidade(velNova);
            System.out.printf("Indo para posicao (%d, %d) a %d m/s", posicaoX, posicaoY, getVelocidade());
        }
        else {
            System.out.printf("Velocidade nova excede a maxima. Voltando a velocidade original em direcao a posicao (%d, %d).", posicaoX, posicaoY);
        }
    } // usado
}
