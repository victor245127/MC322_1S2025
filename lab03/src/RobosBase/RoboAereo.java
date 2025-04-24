package RobosBase;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Sensor.SensorColisao;
import Sensor.SensorIdentificacao;

// subclasse de robos que alteram sua altitude
public class RoboAereo extends Robo {
    protected int altitudeMax;
    protected SensorIdentificacao sensor_identificacao;
    //atributos padrao dos robos aereos

    // falar que mudei altitude para posicaoZ
    public RoboAereo (String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident){
        super(nome, posicaoX, posicaoY, posicaoZ, sensor_colisao);
        this.altitudeMax = altitudeMax;
        this.sensor_identificacao = sensor_ident;
    } // construtor do robo aereo

    //metodos para aumentar ou diminuir a altitude do robo, e checam se a nova esta dentro dos limites
    public void subir(int metros, Ambiente ambiente, ArrayList<Obstaculos> obstaculos){
        if (((getZ()+metros) <= altitudeMax && (getZ()+metros) <= ambiente.getAltura()) && !sensor_colisao.detectarColisoes(ambiente, getX(), getY(), (getZ()+metros))){
            this.posicaoZ += metros;
            System.out.printf("Altitude nova de %d m.\n", getZ());
        }
        else {
            System.out.println("Altitude nao permitida.\n");
        }
    } 
    
    public void descer(int metros, Ambiente ambiente){
        if ((getZ()-metros) >= 0 && !sensor_colisao.detectarColisoes(ambiente, getX(), getY(), (getZ()-metros))){
            this.posicaoZ -= metros;
            System.out.printf("Altitude nova de %d m.\n", getZ());
        }
        else {
            System.out.printf("Altitude de %d m nao permitida.\n", (getZ()-metros));
        }
    }

    public void identificar(Ambiente ambiente){
        sensor_identificacao.monitorar(ambiente, posicaoX, posicaoY, posicaoZ);
    }

    public void exibirPosicaoAereo(){
        System.out.printf("Posicao de %s: (%d, %d, %d)\n", getNome(), getX(), getY(), getZ());
    }
}