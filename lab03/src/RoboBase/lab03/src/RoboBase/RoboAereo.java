package lab03.src.RoboBase;

import java.util.ArrayList;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorIdentificacao;
import lab03.src.Sensor.SensorColisao;

// subclasse de robos que alteram sua altitude
public class RoboAereo extends Robo {
    protected int altitudeMax;
    protected SensorIdentificacao sensor_identificacao;
    //atributos padrao dos robos aereos

    // falar que mudei altitude para posicaoZ
    public RoboAereo (String nome, String direcao, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax, Sensor sensor, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident){
        super(nome, direcao, posicaoX, posicaoY, posicaoZ, sensor, sensor_colisao);
        this.altitudeMax = altitudeMax;
        this.sensor_identificacao = sensor_ident;
    } // construtor do robo aereo

    public void setAltitudeMax(int altM){
        this.altitudeMax = altM;
    }

    public void setSensores(Sensor sensor, SensorIdentificacao sensor_ident, SensorColisao sensor_colisao){
        this.sensor = sensor;
        this.sensor_identificacao = sensor_ident;
        this.sensor_colisao = sensor_colisao;
    }

    //metodos para aumentar ou diminuir a altitude do robo, e checam se a nova esta dentro dos limites
    public void subir(int metros, Ambiente ambiente, ArrayList<Obstaculos> obstaculos){
        if (((getZ()+metros) <= altitudeMax && (getZ()+metros) <= ambiente.getAltura()) && sensor_colisao.detectarColisoes(obstaculos, ambiente, getX(), getY(), (getZ()+metros))){
            this.posicaoZ += metros;
            System.out.printf("Altitude nova de %d m.\n", getZ());
        }
        else {
            System.out.println("Altitude nao permitida.\n");
        }
    } 
    
    public void descer(int metros, Ambiente ambiente, ArrayList<Obstaculos> obstaculos){
        if ((getZ()-metros) >= 0 && sensor_colisao.detectarColisoes(obstaculos, ambiente, getX(), getY(), (getZ()-metros))){
            this.posicaoZ -= metros;
            System.out.printf("Altitude nova de %d m.\n", getZ());
        }
        else {
            System.out.printf("Altitude de %d m nao permitida.\n", (getZ()-metros));
        }
    }

    public void identificar(Ambiente ambiente, ArrayList<Obstaculos> obstaculos){
        sensor_identificacao.monitorar_identificar(ambiente, obstaculos, posicaoX, posicaoY, posicaoZ);
    }

    public void exibirPosicaoAereo(){
        System.out.printf("Posicao de %s: (%d, %d, %d)\n", getNome(), getX(), getY(), getZ());
    }
}