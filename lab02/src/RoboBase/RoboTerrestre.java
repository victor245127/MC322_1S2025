package lab02.src.RoboBase;

public class RoboTerrestre extends Robo {
    protected int velocidadeMax;
    protected int velocidadeAtual;
    // atributos padrao de robos terrestres

    public RoboTerrestre (String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual){
        super(nome, direcao, posicaoX, posicaoY);
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
    } // construtor de robos terrestres

    // metodos para mudar a velocidade e mostra-la, respectivamente
    private void setVelocidade(int vel){
        this.velocidadeAtual = vel;
    }

    private int getVelocidade(){
        return this.velocidadeAtual;
    } 

    //metodo de sobrecarga de mover, que adiciona a mudanca de velocidade, se nao exceder a maxima
    public void mover (int posX, int posY, int velNova){
        this.posicaoX = posX;
        this.posicaoY = posY;
        if (velNova <= velocidadeMax){
            setVelocidade(velNova);
            System.out.printf("Indo para posicao (%d, %d) a %d m/s\n", posicaoX, posicaoY, getVelocidade());
        }
        else {
            System.out.printf("Velocidade nao permitida. Indo para (%d, %d)\n", posX, posY);
        }
    } 
}
