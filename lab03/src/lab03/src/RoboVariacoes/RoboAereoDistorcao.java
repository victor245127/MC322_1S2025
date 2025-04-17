package lab03.src.RoboVariacoes;

import lab03.src.Ambiente.Ambiente;
import lab03.src.RoboBase.RoboAereo;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorColisao;
import lab03.src.Sensor.SensorIdentificacao;

// subclasse de robo aereo que distorce as dimensoes, as aumentando ou diminuindo
public class RoboAereoDistorcao extends RoboAereo {
    private int deltaAltura;
    private int deltaLargura;
    // atributos de robo aereo de distorcao

    public RoboAereoDistorcao(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, Sensor sensor, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident, int deltaAltura, int deltaLargura){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax, sensor, sensor_ident, sensor_colisao);
        this.deltaAltura = deltaAltura;
        this.deltaLargura = deltaLargura;
    } // construtor desse robo

    // metodo em que o robo muda as dimensoes do ambiente
    public void Distorcer(Ambiente ambiente){
        ambiente.setAltura(ambiente.getAltura()+this.deltaAltura);
        ambiente.setLargura(ambiente.getLargura()+this.deltaLargura);
        System.out.printf("Ambiente distorcido para %d m x %d m %d m\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
    }
}
