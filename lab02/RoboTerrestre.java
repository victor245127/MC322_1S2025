package lab02;
import java.util.Scanner;

public class RoboTerrestre extends Robo {
    protected int velocidadeMax;
    protected int velocidadeAtual;

    public RoboTerrestre (String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual){
        super(nome, direcao, posicaoX, posicaoY);
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
    }

    public void mover (int deltaX, int deltaY){
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite a nova velocidade do robo: ");
        int velNova = ler.nextInt();
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
        if (velNova <= velocidadeMax){
            velocidadeAtual = velNova;
            System.out.printf("Indo para posicao (%d, %d) a %d m/s", posicaoX, posicaoY, velocidadeAtual);
        }
        else {
            System.out.printf("Velocidade nova excede a maxima. Voltando a velocidade original em direcao a posicao (%d, %d).", posicaoX, posicaoY);
        }
        ler.close();
    }
}
