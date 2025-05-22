package RoboVariacoes;

// Varre o ambiente na direção atual até um certo alcance visual, detectando obstáculos à frente.

import Ambiente.Ambiente;
import Exceptions.RoboDesligadoException;
import RobosBase.EstadoRobo;
import RobosBase.RoboAereo;

public class RoboAereoFalcao extends RoboAereo {
    private int alcanceVisual;
    private String direcao;
    // atributo do falcão

    public RoboAereoFalcao(String id, int x, int y, int z, int altitudeMaxima, int alcanceVisual, String direcao) {
        super(id, x, y, z, altitudeMaxima);
        this.alcanceVisual = alcanceVisual;
        this.direcao = direcao;
    } // Construtor do falcão

    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException {
        // Habilidade do falcão que verifica se há obstáculos dentro do seu alcance visual
        // verifica o ambiente de acordo com sua direção
        int x = getX();
        int y = getY();
        String dir = direcao.toLowerCase();
        boolean encontrou = false;

        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }

        // Condicionais para direção
        if (dir.equals("norte")) {
            for (int i = 1; i <= alcanceVisual && y + i <= ambiente.getAltura(); i++) {
                if (ambiente.estaOcupado(x, y+i, z)) {
                    System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", x, (y + i), z);
                    encontrou = true;
                }
            }
        } else if (dir.equals("sul")) {
            for (int i = 1; i <= alcanceVisual && y - i >= 0; i++) {
                if (ambiente.estaOcupado(x, y - i, z)) {
                    System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", x, (y - i), z);
                    encontrou = true;
                }
            }
        } else if (dir.equals("leste")) {
            for (int i = 1; i <= alcanceVisual && x + i <= ambiente.getLargura(); i++) {
                if (ambiente.estaOcupado(x + i, y, z)) {
                    System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", (x+1), y, z);
                    encontrou = true;
                }
            }
        } else if (dir.equals("oeste")) {
            for (int i = 1; i <= alcanceVisual && x - i >= 0; i++) {
                if (ambiente.estaOcupado(x - i, y, z)) {
                    System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", (x-1), y, z);
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

    // Descreve o robô
    public String getDescricao(){
        return "Robô do tipo aéreo que detecta obstáculos ao olhar em uma direção com um certo alcance visual.\n";
    }
}