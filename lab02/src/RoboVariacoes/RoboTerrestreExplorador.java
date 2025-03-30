// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
// Conta quantos passos conseguiu dar durante a exploração.

package lab02.src.RoboVariacoes;

import lab02.src.AmbienteMain.Ambiente;
import lab02.src.RoboBase.RoboTerrestre;

public class RoboTerrestreExplorador extends RoboTerrestre {
    private int passosDados;

    public RoboTerrestreExplorador(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.passosDados = 0;
    }

    public void explorar(Ambiente ambiente, int obstaculos[][]) {
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

            if (!ambiente.dentroDosLimites(novoX, novoY) || identificarObstaculo(obstaculos, novoX, novoY)) {
                System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                break;
            }

            if (ambiente.dentroDosLimites(novoX - x, novoY - y)){
                mover(novoX - x, novoY - y);
            }
            passosDados++;
            x = novoX;
            y = novoY;
        }

        System.out.printf("Exploração finalizada. Passos dados: %d\n", passosDados);
    }

    public int getPassosDados() {
        return passosDados;
    }
}