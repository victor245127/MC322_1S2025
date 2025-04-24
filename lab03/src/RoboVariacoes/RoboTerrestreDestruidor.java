package RoboVariacoes;

// Robo que consegue destruir um obstáculo ao redor dele

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;
import Sensor.SensorColisao;

public class RoboTerrestreDestruidor extends RoboTerrestre {
    private int forcaDestruicao; //forca da destruicao

    public RoboTerrestreDestruidor(String nome, int posicaoX, int posicaoY, int velocidadeMaxima, int velocidadeAtual, int forcaDestruicao, SensorColisao sensor_colisao) {
        super(nome, posicaoX, posicaoY, velocidadeMaxima, velocidadeAtual, sensor_colisao);
        this.forcaDestruicao = forcaDestruicao;
    }

    public void destruirObstaculo(Ambiente ambiente) {
        int x = getX();
        int y = getY();
        int z = getZ();
                
        if (ambiente.temObstaculoEm(x + 1, y, z)) {
            int resistencia = ambiente.getResistenciaEm(x + 1, y, z);
            if (this.forcaDestruicao >= resistencia){
                System.out.println("Obstáculo à direita. Destruindo com forca " + forcaDestruicao);
                ambiente.removerObstaculoEm(x + 1, y, z);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.");
            }

        } else if (ambiente.temObstaculoEm(x - 1, y, z)) {
            int resistencia = ambiente.getResistenciaEm(x - 1, y, z);
            if (this.forcaDestruicao >= resistencia){
                System.out.println("Obstáculo à esquerda. Destruindo com forca " + forcaDestruicao);
                ambiente.removerObstaculoEm(x - 1, y, z);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.");
            }

        } else if (ambiente.temObstaculoEm(x, y + 1, z)) {
            int resistencia = ambiente.getResistenciaEm(x, y + 1, z);
            if (this.forcaDestruicao >= resistencia){
                System.out.println("Obstáculo acima. Destruindo com forca " + forcaDestruicao);
                ambiente.removerObstaculoEm(x, y + 1, z);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.");
            }

        } else if (ambiente.temObstaculoEm(x, y - 1, z)) {
            int resistencia = ambiente.getResistenciaEm(x, y - 1, z);
            if (this.forcaDestruicao >= resistencia){
                System.out.println("Obstáculo abaixo. Destruindo com forca " + forcaDestruicao);
                ambiente.removerObstaculoEm(x, y - 1, z);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.");
            }
        } else {
            System.out.println("Nenhum obstáculo ao redor para destruir.");
        }
    }
    
}