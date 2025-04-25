package RoboVariacoes;

// Varre o ambiente na direção atual até um certo alcance visual, detectando obstáculos à frente.

import Ambiente.Ambiente;
import RobosBase.RoboAereo;

public class RoboAereoFalcao extends RoboAereo {
    private int alcanceVisual;
    // atributo do falcão

    public RoboAereoFalcao(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, int alcanceVisual) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.alcanceVisual = alcanceVisual;
    } // Construtor do falcão

    public void visao(Ambiente ambiente) {
        // Habilidade do falcão que verifica se há obstáculos dentro do seu alcance visual
        // verifica o ambiente de acordo com sua direção
        int x = getPosX();
        int y = getPosY();
        String dir = getDirecao().toLowerCase();
        boolean encontrou = false;

        // Condicionais para direção
        if (dir.equals("norte")) {
            for (int i = 1; i <= alcanceVisual && y + i <= ambiente.getAltura(); i++) {
                if (ambiente.temObstaculoEm(x, y + i)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", x, (y + i));
                    encontrou = true;
                }
            }
        } else if (dir.equals("sul")) {
            for (int i = 1; i <= alcanceVisual && y - i >= 0; i++) {
                if (ambiente.temObstaculoEm(x, y - i)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", x, (y - i));
                    encontrou = true;
                }
            }
        } else if (dir.equals("leste")) {
            for (int i = 1; i <= alcanceVisual && x + i <= ambiente.getLargura(); i++) {
                if (ambiente.temObstaculoEm(x + i, y)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", (x+1), y);
                    encontrou = true;
                }
            }
        } else if (dir.equals("oeste")) {
            for (int i = 1; i <= alcanceVisual && x - i >= 0; i++) {
                if (ambiente.temObstaculoEm(x - i, y)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", (x-1), y);
                    encontrou = true;
                }
            }
        } else {
            System.out.println("Direção inválida.\n");
            return;
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na linha de visão.\n");
        }
    }
}