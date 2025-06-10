package RoboVariacoes;

import java.util.Random;

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
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

    public void executarTarefa(Ambiente ambiente) throws ColisaoException, RoboDesligadoException, ForaDosLimitesException { // Habilidade do observador que procura obstáculos dentro do seu raio de visão
        try {
            int x = getX()[0];
            int y = getY()[0];
            int z = getZ()[0];
            boolean encontrou = false;

            // verifica se o robô está desligado
            if (getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            } 

            // Busca um obstáculo dentro de seu raio de observação
            for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
                for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                    for (int k = z - raioObservacao; k <= z + raioObservacao; k++){
                        if (i >= 0 && i < ambiente.getLargura() && j >= 0 && j < ambiente.getProfundidade() && k >= 0 && k < ambiente.getAltura() && ambiente.temObstaculoEm(i, j, k)) {
                            System.out.printf("Obstaculo detectado em (%d, %d, %d)\n", i, j, k);
                            encontrou = true;
                        }
                    }
                }
            }

            if (!encontrou) {
                System.out.println("Nenhum obstaculo encontrado na area.\n");
            }
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // Método autonomia herdado da interface autônomo
    public void Autonomia(Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException{
        Random random = new Random();
        int acao = random.nextInt(4);
        int novoX = random.nextInt(ambiente.getLargura());
        int novoY = random.nextInt(ambiente.getProfundidade());
        int novoZ = random.nextInt(ambiente.getAltura());
        // Randomiza a ação a ser tomada, podendo mover para uma nova posição aleatória, ligar ou desligar, ou executar sua tarefa
        switch (acao) {
            case 0:
                moverPara(novoX, novoY, novoZ);
                exibirPosicao();
                break;
            case 1:
                desligar();
                System.out.println("Desligando...");
                break;
            case 2:
                ligar();
                System.out.println("Ligando...");
                break;
            case 3:
                executarTarefa(ambiente);
            default:
                break;
        }
    }

    public void executarMissao(Ambiente ambiente, CentralComunicacao central) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException {
        if (tem_missao()){
            missao.executar(this, ambiente, central);
        }
        else {
            System.out.println("Sem missoes atribuidas.");
        }
    }

    // Descreve o robô
    public String getDescricao() {
        return "Robo do tipo aereo em que sua tarefa eh observar o ambiente a sua volta dentro de um certo raio de observacao.";
    }
}