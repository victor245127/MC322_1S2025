package RoboBase;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Sensor.Sensor;
import Sensor.SensorColisao;

// subclasse de robo para alterar a velocidade de movimento
public class RoboTerrestre extends Robo {
    protected int velocidadeMax;
    protected int velocidadeAtual;
    // atributos padrao de robos terrestres

    public RoboTerrestre (String nome, int posicaoX, int posicaoY, int posicaoZ, int velocidadeMax, int velocidadeAtual, Sensor sensor, SensorColisao sensor_colisao){
        super(nome, posicaoX, posicaoY, posicaoZ, sensor, sensor_colisao);
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
    } // construtor de robos terrestres

    // metodos para mudar a velocidade e mostra-la, respectivamente
    public void setVelocidade(int vel){
        this.velocidadeAtual = vel;
    }

    public void setVelocidadeMax(int velMax){
        this.velocidadeMax = velMax;
    }

    private int getVelocidade(){
        return this.velocidadeAtual;
    } 

    //metodo de sobrecarga de mover, que adiciona a mudanca de velocidade, se nao exceder a maxima
    public void mover (int posX, int posY, Ambiente ambiente, int velNova, ArrayList<Obstaculos> obstaculos){
        if (ambiente.dentroDosLimites(posX, posY, getZ()) && !sensor_colisao.detectarColisoes(obstaculos, ambiente, posX, posY, getZ())){
            this.posicaoX = posX;
            this.posicaoY = posY;
            if (velNova <= velocidadeMax){
                setVelocidade(velNova);
                System.out.printf("Indo para posicao (%d, %d, %d) a %d m/s\n", getX(), getY(), getZ(), getVelocidade());
            }
            else {
                System.out.printf("Velocidade nao permitida. Indo para (%d, %d, %d) a %d m/s\n", getX(), getY(), getZ(), getVelocidade());
            }
        }
        else {
            System.out.printf("Posicao (%d, %d, %d) nao pode ser alcancada\n", posX, posY, getZ());
        }
    } 
}
