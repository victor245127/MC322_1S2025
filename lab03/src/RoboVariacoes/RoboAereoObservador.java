package RoboVariacoes;

import Ambiente.Ambiente;
import RobosBase.RoboAereo;
import Sensor.SensorColisao;
import Sensor.SensorIdentificacao;

// subclasse de robo aereo que observa o ambiente e identifica os obstaculos em um dado raio
public class RoboAereoObservador extends RoboAereo {
    private int raioObservacao;

    public RoboAereoObservador(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMaxima, int raioObservacao, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident) {
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMaxima, sensor_colisao, sensor_ident);
        this.raioObservacao = raioObservacao;
    }

    public void observar(Ambiente ambiente) {
        int x = getX();
        int y = getY();
        boolean encontrou = false;

        for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
            for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                if (ambiente.dentroDosLimites(i, j, getZ()) && ambiente.temObstaculoEm(i, j, getZ())) {
                    System.out.println("Obstáculo detectado em (" + i + ", " + j + ")");
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na área.");
        }
    }
}