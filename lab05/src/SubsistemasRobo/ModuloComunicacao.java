package SubsistemasRobo;

import Comunicacao.CentralComunicacao;
import Comunicacao.Comunicavel;
import Exceptions.RoboDesligadoException;
import RobosBase.Robo;

public class ModuloComunicacao {

    // Métodos do módulo de comunicação que fazem uso da central de comunicação
    public void enviarMensagem(Comunicavel destinatario, Comunicavel remetente, String mensagem, CentralComunicacao central) throws RoboDesligadoException{
        receberMensagem(destinatario, remetente, mensagem, central);
    }

    // Método que determina se o destinatário recebe ou não a mensagem
    public void receberMensagem(Comunicavel destinatario, Comunicavel remetente, String mensagem, CentralComunicacao central){
        if (((Robo)destinatario).getX()[0] >= (((Robo) remetente).getX()[0] - central.getRaio()) && ((Robo)destinatario).getX()[0] <= (((Robo) remetente).getX()[0] + central.getRaio()) && ((Robo)destinatario).getY()[0] >= (((Robo) remetente).getY()[0] - central.getRaio()) && ((Robo)destinatario).getY()[0] <= (((Robo) remetente).getY()[0] + central.getRaio())){
            central.registrarMensagem(((Robo) destinatario).getId(), (((Robo) remetente)).getId(), mensagem);
        } // Caso o destinatário esteja dentro do raio de comunicação, recebe a mensagem e a registra
        else {
            System.out.println("Robo a uma distancia maior que o alcance do raio, nao foi possivel receber a mensagem.");
        }
    }
}
