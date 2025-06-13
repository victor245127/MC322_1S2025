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

// Missão de exploração do ambiente
public class MissaoExplorar implements Missao {
    public MissaoExplorar(){}

    public void executar(Robo r, Ambiente ambiente, CentralComunicacao central) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException {
        System.out.printf("Robo %s iniciando exploracao...\n", r.getId());
        String dir = "";
        int x = r.getX()[0];
        int y = r.getY()[0];
        int z = r.getZ()[0];
        // Caso o robô seja um dos direcionáveis, já pré define sua direção
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
                    System.out.printf("Exploracao interrompida em (%d, %d)\n", novoX, novoY);
                    break;
                }
                
                // Caso o robô tenha implementação de sensores, os ativa a cada passo
                if (r instanceof Sensoreavel){
                    System.out.println("Acionando sensores...");
                    ((Sensoreavel)r).acionarSensores(ambiente);
                }

                // Robôs terrestres movem-se a uma velocidade pré-definida de 5 m/s
                ambiente.moverEntidade(r, novoX, novoY, z, 5);
                passosDados++;
                x = novoX;
                y = novoY;
            }
            // Exibe a quantidade de passos dados durante a exploração
           System.out.printf("Exploracao finalizada. Passos dados na direcao %s: %d\n", dir, passosDados);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}
