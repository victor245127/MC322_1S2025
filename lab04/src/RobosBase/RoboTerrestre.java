package RobosBase;

import Exceptions.RoboDesligadoException;

// subclasse de robo para alterar a velocidade de movimento
public abstract class RoboTerrestre extends Robo{
    protected int velocidadeMaxima;
    // Atributo do robô

    public RoboTerrestre(String id, int posicaoX, int posicaoY, int posicaoZ, int velocidadeMaxima){
        super(id, posicaoX, posicaoY, posicaoZ);
        this.velocidadeMaxima = velocidadeMaxima;
    } // Construtor do robô

    // Método de overload de "mover" que adiciona a velocidade nova do robô
    public void moverPara(int x, int y, int z, int velocidade) throws RoboDesligadoException{ 
        moverPara(x, y, z);
        if (velocidade > this.velocidadeMaxima) {
            System.out.println("Velocidade acima da permitida!");  
        }
    } 
}