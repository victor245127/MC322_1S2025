package Comunicacao;

import Exceptions.RoboDesligadoException;

// Interface de comunicação
public interface Comunicavel {
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) throws RoboDesligadoException;
    public void receberMensagem(String mensagem) throws RoboDesligadoException;
}
