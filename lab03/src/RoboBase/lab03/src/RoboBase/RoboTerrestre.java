package lab03.src.RoboBase;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorIdentifica;

// subclasse de robo para alterar a velocidade de movimento
public class RoboTerrestre extends Robo {
    protected int velocidadeMax;
    protected int velocidadeAtual;
    protected SensorIdentifica sensor_identificacao;
    // atributos padrao de robos terrestres

    public RoboTerrestre (String nome, String direcao, int posicaoX, int posicaoY, int posicaoZ, int velocidadeMax, int velocidadeAtual, Sensor sensor, SensorIdentifica sensor_ident){
        super(nome, direcao, posicaoX, posicaoY, posicaoZ, sensor);
        this.velocidadeMax = velocidadeMax;
        this.velocidadeAtual = velocidadeAtual;
        this.sensor_identificacao = sensor_ident;
    } // construtor de robos terrestres

    // metodos para mudar a velocidade e mostra-la, respectivamente
    private void setVelocidade(int vel){
        this.velocidadeAtual = vel;
    }

    private int getVelocidade(){
        return this.velocidadeAtual;
    } 

    //metodo de sobrecarga de mover, que adiciona a mudanca de velocidade, se nao exceder a maxima
    public void mover (int posX, int posY, Ambiente ambiente, int velNova){
        this.posicaoX = posX;
        this.posicaoY = posY;
        if (ambiente.dentroDosLimites(posX, posY, getZ())){
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
