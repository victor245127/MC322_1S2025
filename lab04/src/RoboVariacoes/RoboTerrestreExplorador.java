package RoboVariacoes;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Direcionavel_horizontal;
import RobosBase.EstadoRobo;
import RobosBase.RoboTerrestre;

// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
 // Conta quantos passos conseguiu dar durante a exploração.
 
 public class RoboTerrestreExplorador extends RoboTerrestre implements Direcionavel_horizontal {
    private int passosDados; // Atributos

    public RoboTerrestreExplorador(String id, int x, int y, int z, int velocidadeMaxima) {
        super(id, x, y, z, velocidadeMaxima);
        this.passosDados = 0;
    } // Construtor

    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, ColisaoException{ 
        // Habilidade do explorador que avança até o limite do ambiente ou caso chegue em um obstáculo
        // e avança de acordo com sua direção
        int x = getX()[0];
        int y = getY()[0];
        int z = getZ()[0];
        String dir = direcionar_h(ambiente).toLowerCase();

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
    }

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
        return "Robô do tipo terrestre que explora o mapa em uma certa direção, até esbarrar em algo ou chegar no limite do mapa. Também possui uma autonomia, que aciona uma ação aleatória.\n";
    }
}