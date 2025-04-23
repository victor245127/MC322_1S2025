package RoboVariacoes;

import java.util.ArrayList;

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;
import Sensor.Sensor;
import Sensor.SensorColisao;
import Sensor.SensorIdentificacao;
import Ambiente.Obstaculos;

import java.util.ArrayList;

// subclasse de robo terrestre que remove um possivel obstaculo em uma certa posicao
public class RoboTerrestreRemocao extends RoboTerrestre {
    private int removeX;
    private int removeY;
    //atributos do robo terrestre de remocao

    public RoboTerrestreRemocao(String nome, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int removeX, int removeY, Sensor sensor, SensorColisao sensor_colisao){
        super(nome, posicaoX, posicaoY, velocidadeMax, velocidadeAtual, removeY, sensor, sensor_colisao);
        this.removeX = removeX;
        this.removeY = removeY;
    } // construtor do robo 

    // metodo que remove um obstaculo proximo a nova posicao do robo
    public void removeObstaculo(Ambiente ambiente, ArrayList<Obstaculos> obstaculos, int velNova){
        int i = 0;
        mover(this.removeX, this.removeY, ambiente, velNova);
        // vai para a posicao dado nos atributos
        do {
            if (identificarObstaculo(obstaculos, removeX, removeY)){
                obstaculos[i][0] = -1;
                obstaculos[i][1] = -1;
                System.out.println("Obstaculo removido.\n");
                return;
                // muda as posicoes do obstaculo para -1, pois assim nao serao mais acessiveis
            }
            i++;
        } while (i < obstaculos.length);
        // loop do while para verificar se ha obstaculos a sua volta e, caso haja, o(s) remove
        System.out.println("Obstaculo nao encontrado.\n");
        // caso loop finalize, significa que nao ha obstaculos em sua volta
    }
}
