package Comunicacao;

import Exceptions.RoboDesligadoException;

// Interface de comunicação com seus métodos
public interface Comunicavel {
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) throws RoboDesligadoException;
    public void receberMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central);
}
