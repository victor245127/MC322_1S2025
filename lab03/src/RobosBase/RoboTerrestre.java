package RobosBase;

import Ambiente.Ambiente;

// subclasse de robo para alterar a velocidade de movimento
public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;
    // Atributo do robô

    public RoboTerrestre(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima){
        super(nome,direcao,posicaoX,posicaoY);
        this.velocidadeMaxima = velocidadeMaxima; 
    } // Construtor do robô

    public void mover(int deltaX, int deltaY, Ambiente ambiente, int velocidade){ 
        if (velocidade <= this.velocidadeMaxima) {
            mover(deltaX,deltaY,ambiente);
        }
        else{
            System.out.println("Velocidade acima da permitida!\n");
        }
    } // Método de overload de "mover" que adiciona a velocidade nova do robô
}