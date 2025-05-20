package Comunicacao;

import Exceptions.RoboDesligadoException;

// Interface de comunicação
public interface Comunicavel {
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException;
    public void receberMensagem(String mensagem);
}
