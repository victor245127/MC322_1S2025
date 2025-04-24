// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
// Conta quantos passos conseguiu dar durante a exploração.

package RoboVariacoes;

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;
import Sensor.SensorColisao;

// subclasse de robo terrestre que explora o ambiente em uma direcao ate chegar ao limite ou ser barrado
// por um obstaculo
// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
 // Conta quantos passos conseguiu dar durante a exploração.
 
 public class RoboTerrestreExplorador extends RoboTerrestre {
    private int passosDados;

    public RoboTerrestreExplorador(String nome, int posicaoX, int posicaoY, int velocidadeMaxima, int velocidadeAtual, Ambiente ambiente, SensorColisao sensor_colisao) {
        super(nome, posicaoX, posicaoY, velocidadeMaxima, velocidadeAtual, sensor_colisao);
        this.passosDados = 0;
    }

    public void explorar(Ambiente ambiente) {
        int x = getX();
        int y = getY();
        String dir = getDirecao().toLowerCase();

        System.out.println("Iniciando exploração para " + dir);

        while (true) {
            int novoX = x;
            int novoY = y;

            if (dir.equals("norte")) novoY++;
            else if (dir.equals("sul")) novoY--;
            else if (dir.equals("leste")) novoX++;
            else if (dir.equals("oeste")) novoX--;
            else {
                System.out.println("Direção inválida.");
                break;
            }

            if (!ambiente.dentroDosLimites(novoX, novoY, getZ()) || ambiente.temObstaculoEm(novoX, novoY, getZ())) {
                System.out.println("Exploração interrompida em (" + novoX + ", " + novoY + ")");
                break;
            }

            mover(novoX - x, novoY - y, ambiente);
            passosDados++;
            x = novoX;
            y = novoY;
        }

        System.out.println("Exploração finalizada. Passos dados: " + passosDados);
    }

    public int getPassosDados() {
        return passosDados;
    }
}
