package RobosBase;

import Sensor.Sensoreavel;

// subclasse de robo para alterar a velocidade de movimento
public abstract class RoboTerrestre extends Robo implements Sensoreavel{
    private int velocidadeMaxima;
    // Atributo do robô

    public RoboTerrestre(String id, int posicaoX, int posicaoY, int posicaoZ, int velocidadeMaxima){
        super(id, posicaoX, posicaoY, posicaoZ);
        this.velocidadeMaxima = velocidadeMaxima; 
    } // Construtor do robô

    // Método de overload de "mover" que adiciona a velocidade nova do robô
    public void moverPara(int x, int y, int z, int velocidade){ 
        if (velocidade <= this.velocidadeMaxima) {
            moverPara(x, y, z);
        }
        else{
            System.out.println("Velocidade acima da permitida!\n");
        }
    } 

    // Parametro do raio??
    public void acionarSensores(){
        
    }
}