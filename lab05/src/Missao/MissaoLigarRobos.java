package Missao;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Comunicacao.Comunicavel;
import Entidade.Entidade;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import RobosBase.RoboTerrestre;
import Sensor.Sensoreavel;


// Missão na qual o robô escolhido vai para uma posição próxima aos outros robôs e os liga, caso estejam desligados
public class MissaoLigarRobos implements Missao {
    public MissaoLigarRobos(){}

    public void executar(Robo r, Ambiente ambiente, CentralComunicacao central) throws ColisaoException, ForaDosLimitesException{
        try {
            System.out.println("Comecando missao de ligar todos os robos desligados...");
            int i, x, y, z;
            // Lista com os robos
            ArrayList<Entidade> robos = ambiente.getRobos();

            // Caso o robo escolhido esteja desligado, trata o erro
            if (r.getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }
            
            for (i = 0; i < robos.size(); i++){
                // Caso o robo na lista seja o mesmo que o escolhido para executar missao, pula para o proximo
                if (((Robo) robos.get(i)) == r){
                    continue;
                }

                // Define as posições do robô para o mais perto do robô possível
                x = robos.get(i).getX()[0] - 1;
                y = robos.get(i).getY()[0] - 1;
                // Caso o robô seja terrestre, não pode alterar seu z
                if (r instanceof RoboTerrestre){
                    if (robos.get(i).getZ()[0] != 0){
                        System.out.println("Robo eh terrestre, nao conseguiu alcancar a posicao do aereo");
                        continue;
                    }
                    z = 0;
                } 
                else {
                    z = robos.get(i).getZ()[0] - 1;
                }
                // Dentro do moverEntidade, verifica se a posição nova está dentro dos limites ou se 
                // tem alguma colisão eminente
                ambiente.moverEntidade(((Entidade) r), x, y, z, 5);
                // Ao mudar ou não de posição, o robô aciona seus sensores
                if (r instanceof Sensoreavel){
                    ((Sensoreavel) r).acionarSensores(ambiente);
                }
                
                // Caso robô do momento esteja desligado, liga ele
                if (((Robo) ambiente.getRobos().get(i)).getEstado() == EstadoRobo.desligado){
                    ((Robo) ambiente.getRobos().get(i)).ligar();
                    if (r instanceof Comunicavel && ambiente.getRobos().get(i) instanceof Comunicavel){
                        // Caso ambos robôs sejam comunicáveis, o robô da missão envia uma mensagem ao
                        // robô que foi ligado
                        ((Comunicavel) r).enviarMensagem(((Comunicavel) ambiente.getRobos().get(i)), ((Comunicavel) r), "Te liguei, agora voce esta livre para explorar o ambiente.", central);
                    }
                }
            }
        } catch (RoboDesligadoException d){
            System.out.println("ERRO: " + d.getMessage());
        }
    }
}
