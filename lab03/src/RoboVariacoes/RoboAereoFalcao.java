package RoboVariacoes;

// Varre o ambiente na direção atual até um certo alcance visual, detectando obstáculos à frente.

import Ambiente.Ambiente;
import RobosBase.RoboAereo;
import Sensor.SensorIdentificacao;
import Sensor.SensorColisao;

public class RoboAereoFalcao extends RoboAereo {
    private int alcanceVisual;

    public RoboAereoFalcao(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMaxima, int alcanceVisual, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident) {
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMaxima, sensor_colisao, sensor_ident);
        this.alcanceVisual = alcanceVisual;
    }

    public void visao(Ambiente ambiente) {
        int x = getX();
        int y = getY();
        int z = getZ();
        String dir = getDirecao().toLowerCase();
        boolean encontrou = false;

        if (dir.equals("norte")) {
            for (int i = 1; i <= alcanceVisual && y + i <= ambiente.getAltura(); i++) {
                if (ambiente.temObstaculoEm(x, y + i, z)) {
                    System.out.println("Obstáculo detectado em (" + x + ", " + (y + i) + ")");
                    encontrou = true;
                }
            }
        } else if (dir.equals("sul")) {
            for (int i = 1; i <= alcanceVisual && y - i >= 0; i++) {
                if (ambiente.temObstaculoEm(x, y - i, z)) {
                    System.out.println("Obstáculo detectado em (" + x + ", " + (y - i) + ")");
                    encontrou = true;
                }
            }
        } else if (dir.equals("leste")) {
            for (int i = 1; i <= alcanceVisual && x + i <= ambiente.getLargura(); i++) {
                if (ambiente.temObstaculoEm(x + i, y, z)) {
                    System.out.println("Obstáculo detectado em (" + (x + i) + ", " + y + ")");
                    encontrou = true;
                }
            }
        } else if (dir.equals("oeste")) {
            for (int i = 1; i <= alcanceVisual && x - i >= 0; i++) {
                if (ambiente.temObstaculoEm(x - i, y, z)) {
                    System.out.println("⚠️ Obstáculo detectado em (" + (x - i) + ", " + y + ")");
                    encontrou = true;
                }
            }
        } else {
            System.out.println("Direção inválida.");
            return;
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na linha de visão.");
        }
    }
}