package RoboVariacoes;

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;

// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
 // Conta quantos passos conseguiu dar durante a exploração.
 
 public class RoboTerrestreExplorador extends RoboTerrestre {
    private int passosDados; // Atributo

    public RoboTerrestreExplorador(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima);
        this.passosDados = 0;
    } // Construtor

    public void explorar(Ambiente ambiente) { 
        // Habilidade do explorador que avança até o limite do ambiente ou caso chegue em um obstáculo
        // e avança de acordo com sua direção
        int x = getPosX();
        int y = getPosY();
        String dir = getDirecao().toLowerCase();

        System.out.printf("Iniciando exploração para %s\n", dir);

        // Loop para avançar
        while (true) {
            int novoX = x;
            int novoY = y;

            if (dir.equals("norte")) novoY++;
            else if (dir.equals("sul")) novoY--;
            else if (dir.equals("leste")) novoX++;
            else if (dir.equals("oeste")) novoX--;
            else {
                System.out.println("Direção inválida.\n");
                break;
            }

            if (!ambiente.dentroDosLimites(novoX, novoY) || ambiente.temObstaculoEm(novoX, novoY)) {
                System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                break;
            }

            mover(novoX - x, novoY - y, ambiente);
            passosDados++;
            x = novoX;
            y = novoY;
        }

        System.out.printf("Exploração finalizada. Passos dados: %d\n", passosDados);
    }

    // Método que retorna os passos dados pelo robô
    public int getPassosDados() {
        return passosDados;
    }
}