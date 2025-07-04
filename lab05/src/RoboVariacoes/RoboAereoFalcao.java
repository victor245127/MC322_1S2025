package RoboVariacoes;

// Varre o ambiente na direção atual até um certo alcance visual, detectando obstáculos à frente.

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Direcionavel_vertical;
import RobosBase.EstadoRobo;
import RobosBase.RoboAereo;

public class RoboAereoFalcao extends RoboAereo implements Direcionavel_vertical {
    private int alcanceVisual;
    // atributo do falcão

    public RoboAereoFalcao(String id, int x, int y, int z, int altitudeMaxima, int alcanceVisual) {
        super(id, x, y, z, altitudeMaxima);
        this.alcanceVisual = alcanceVisual;
    } // Construtor do falcão

    public void executarTarefa(Ambiente ambiente) throws RoboDesligadoException, ForaDosLimitesException {
        // Habilidade do falcão que verifica se há obstáculos dentro do seu alcance visual
        // verifica o ambiente de acordo com sua direção
        try {
            int x = getX()[0];
            int y = getY()[0];
            int z = getZ()[0];
            String dir = direcionar_v(ambiente).toLowerCase();
            // chama o método de sua interface
            boolean encontrou = false;

            // verifica se o robô está desligado
            if (getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }

            // Condicionais para direção
            if (dir.equals("norte")) {
                for (int i = 1; i <= alcanceVisual && y + i <= ambiente.getAltura(); i++) {
                    if (ambiente.estaOcupado(x, y+i, z)) {
                        System.out.printf("Obstaculo detectado em (%d, %d, %d)\n", x, (y + i), z);
                        encontrou = true;
                    }
                }
            } else if (dir.equals("sul")) {
                for (int i = 1; i <= alcanceVisual && y - i >= 0; i++) {
                    if (ambiente.estaOcupado(x, y - i, z)) {
                        System.out.printf("Obstaculo detectado em (%d, %d, %d)\n", x, (y - i), z);
                        encontrou = true;
                    }
                }
            } else {
                System.out.println("Direcao invalida.");
                return;
            }

            if (!encontrou) {
                System.out.println("Nenhum obstaculo encontrado na linha de visao.");
            }
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // Método de sua interface
    public String direcionar_v(Ambiente ambiente){
        if (y >= ambiente.getProfundidade()/2){
            return "norte";
        }
        else {
            return "sul";
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
    public String getDescricao(){
        return "Robo do tipo aereo que detecta obstaculos ao olhar em uma direcao com um certo alcance visual.";
    }
}