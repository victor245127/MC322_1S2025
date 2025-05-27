package RoboVariacoes;

import java.util.Random;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Autonomo;
import RobosBase.EstadoRobo;
import RobosBase.RoboAereo;

// subclasse de robo aereo que observa o ambiente e identifica os obstaculos em um dado raio
public class RoboAereoObservador extends RoboAereo implements Autonomo{
    private int raioObservacao;
    // Atributo do robô

    public RoboAereoObservador(String id, int x, int y, int z, int altitudeMaxima, int raioObservacao) {
        super(id, x, y, z, altitudeMaxima);
        this.raioObservacao = raioObservacao;
    } // Construtor

    public void executarTarefa(Ambiente ambiente) throws ColisaoException, RoboDesligadoException { // Habilidade do observador que procura obstáculos dentro do seu raio de visão
        int x = getX()[0];
        int y = getY()[0];
        boolean encontrou = false;

        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }

        for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
            for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                for (int k = z - raioObservacao; k <= z + raioObservacao; k++){
                    if (ambiente.dentroDosLimites(i, j, k) && ambiente.estaOcupado(i, j, k)) {
                        System.out.printf("Obstáculo detectado em (%d, %d)\n", i, j);
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na área.\n");
        }
    }

    // Método autonomia herdado da interface autônomo
    public void Autonomia(Ambiente ambiente) throws RoboDesligadoException, ColisaoException{
        Random random = new Random();
        int acao = random.nextInt(4);
        int novoX = random.nextInt(ambiente.getLargura());
        int novoY = random.nextInt(ambiente.getProfundidade());
        int novoZ = random.nextInt(ambiente.getAltura());
        // Randomiza a ação a ser tomada, podendo mover para uma nova posição aleatória, ligar ou desligar, ou executar sua tarefa
        switch (acao) {
            case 0:
                moverPara(novoX, novoY, novoZ);
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


    // Descreve o robô
    public String getDescricao() {
        return "Robô do tipo aéreo em que sua tarefa é observar o ambiente à sua volta dentro de um certo raio de observação.\n";
    }
}