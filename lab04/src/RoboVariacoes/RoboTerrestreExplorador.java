package RoboVariacoes;

import java.util.Random;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Autonomo;
import RobosBase.EstadoRobo;
import RobosBase.RoboTerrestre;

// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
 // Conta quantos passos conseguiu dar durante a exploração.
 
 public class RoboTerrestreExplorador extends RoboTerrestre implements Autonomo {
    private int passosDados; // Atributos
    private String direcao;

    public RoboTerrestreExplorador(String id, int x, int y, int z, int velocidadeMaxima, String direcao) {
        super(id, x, y, z, velocidadeMaxima);
        this.passosDados = 0;
        this.direcao = direcao;
    } // Construtor

    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, ColisaoException{ 
        // Habilidade do explorador que avança até o limite do ambiente ou caso chegue em um obstáculo
        // e avança de acordo com sua direção
        int x = getX();
        int y = getY();
        String dir = direcao.toLowerCase();

        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }

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

            if (!ambiente.dentroDosLimites(novoX, novoY, z) || ambiente.estaOcupado(novoX, novoY, z)) {
                System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                break;
            }

            moverPara(novoX - x, novoY - y, z);
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

    // Método autonomia herdado da interface autônomo
    public void Autonomia(Ambiente ambiente) throws RoboDesligadoException, ColisaoException{
        Random random = new Random();
        int acao = random.nextInt(4);
        int novoX = random.nextInt(ambiente.getLargura());
        int novoY = random.nextInt(ambiente.getProfundidade());
        int novoZ = random.nextInt(ambiente.getAltura());
        int novaVel = random.nextInt(velocidadeMaxima);
        // Randomiza a ação a ser tomada, podendo mover para uma nova posição aleatória, ligar ou desligar, ou executar sua tarefa
        switch (acao) {
            case 0:
                moverPara(novoX, novoY, novoZ, novaVel);
                break;
            case 1:
                desligar();
                break;
            case 2:
                ligar();
                break;
            case 3:
                executarTarefa(ambiente);
            default:
                break;
        }
    }

    public String getDescricao(){
        return "Robô do tipo terrestre que explora o mapa em uma certa direção, até esbarrar em algo ou chegar no limite do mapa.\n";
    }
}