package Missao;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboTerrestreExplorador;
import RobosBase.EstadoRobo;
import RobosBase.Robo;

public class MissaoExplorar implements Missao {
    public MissaoExplorar(){}

    public void executar(Robo r, Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException {
        System.out.printf("Robo %s iniciando exploracao...\n", r.getId());
        String dir = "";
        int x = r.getX()[0];
        int y = r.getY()[0];
        int z = r.getZ()[0];
        if (r instanceof RoboTerrestreExplorador){
            dir = ((RoboTerrestreExplorador) r).direcionar_h(ambiente);
        }
        else if (r instanceof RoboAereoFalcao){
            dir = ((RoboAereoFalcao) r).direcionar_v(ambiente);
        }
        int passosDados = 0;
        // Utiliza o método de sua interface

        // Verifica se o robô está ligado
        try {
            if (r.getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }

            // Loop para avançar
            while (true) {
                int novoX = x;
                int novoY = y;

                if (dir.equals("leste")) novoX++;
                else if (dir.equals("oeste")) novoX--;
                else {
                    System.out.println("Direção inválida.\n");
                    break;
                }

                if (!ambiente.dentroDosLimites(novoX, novoY, z) || ambiente.estaOcupado(novoX, novoY, z)) {
                    System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                    break;
                }

                ambiente.moverEntidade(r, novoX, novoY, z, novoY);
                passosDados++;
                x = novoX;
                y = novoY;
            }
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        System.out.printf("Exploracao finalizada. Passos dados na direcao %s: %d\n", dir, passosDados);
    }
}
