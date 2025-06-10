package Missao;

import java.util.Random;

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboTerrestreExplorador;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import Sensor.Sensoreavel;

public class MissaoExplorar implements Missao {
    public MissaoExplorar(){}

    public void executar(Robo r, Ambiente ambiente, CentralComunicacao central) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException {
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
        else {
            // Caso o robô da missão não tenha uma interface de direcionamento implementada, sua direção
            // é escolhida de forma aleatória
            Random random = new Random();
            int dir_esc = random.nextInt(4);
            switch (dir_esc) {
                case 0:
                    dir = "norte";
                    break;
                case 1:
                    dir = "sul";
                    break;
                case 2:
                    dir = "oeste";
                    break;
                case 3:
                    dir = "leste";
                    break;
                default:
                    break;
            }
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

                if (dir.equals("norte")) novoY++;
                else if (dir.equals("sul")) novoY--;
                else if (dir.equals("leste")) novoX++;
                else if (dir.equals("oeste")) novoX--;
                else {
                    System.out.println("Direção inválida.\n");
                    break;
                }

                // Caso a próxima posição seja inacessível, para na atual, executa sua tarefa e finaliza a exploração
                if (!ambiente.dentroDosLimites(novoX, novoY, z) || ambiente.estaOcupado(novoX, novoY, z)) {
                    System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                    r.executarTarefa(ambiente);                
                    break;
                }
                

                if (r instanceof Sensoreavel){
                    ((Sensoreavel)r).acionarSensores(ambiente);
                }

                ambiente.moverEntidade(r, novoX, novoY, z, novoY);
                passosDados++;
                x = novoX;
                y = novoY;
            }
            System.out.printf("Exploracao finalizada. Passos dados na direcao %s: %d\n", dir, passosDados);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
