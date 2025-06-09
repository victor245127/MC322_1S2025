package RoboVariacoes;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Direcionavel_horizontal;
import Missao.MissaoExplorar;
import RobosBase.AgenteInteligente;
import RobosBase.EstadoRobo;

// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
 // Conta quantos passos conseguiu dar durante a exploração.
 
 public class RoboTerrestreExplorador extends AgenteInteligente implements Direcionavel_horizontal {
    private int passosDados; // Atributos

    public RoboTerrestreExplorador(String id, int x, int y, int z) {
        super(id, x, y, z);
        this.passosDados = 0;
    } // Construtor

    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException{ 
        // Habilidade do explorador que avança até o limite do ambiente ou caso chegue em um obstáculo
        // e avança de acordo com sua direção
        try {
            int x = getX()[0];
            int y = getY()[0];
            int z = getZ()[0];
            String dir = direcionar_h(ambiente);
            // Utiliza o método de sua interface

            // Verifica se o robô está ligado
            if (getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }

            System.out.printf("Iniciando exploração para %s\n", dir);

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

                moverPara(novoX, novoY, z);
                passosDados++;
                x = novoX;
                y = novoY;
            }

            System.out.printf("Exploração finalizada. Passos dados: %d\n", passosDados);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // Implementação do método de sua interface
    public String direcionar_h(Ambiente ambiente){
        if (x >= ambiente.getLargura()/2){
            return "leste";
        }
        else {
            return "oeste";
        }
    }

    // Método que retorna os passos dados pelo robô
    public int getPassosDados() {
        return passosDados;
    }

    public String getDescricao(){
        return "Robo do tipo terrestre que explora o mapa em uma certa direcao, ate esbarrar em algo ou chegar no limite do mapa. Tambem possui uma autonomia, que aciona uma acao aleatoria.";
    }

    public void executarMissao(Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException {
        if (tem_missao()){
            if (missao instanceof MissaoExplorar){
                missao.executar(this, ambiente);
            }
        }
        else {
            System.out.println("Sem missoes atribuidas.");
        }
    }
}