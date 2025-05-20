package Exceptions;

// Tratamento de excessão para casos de robôs desligados
public class RoboDesligadoException extends Exception {
    public RoboDesligadoException (){
        super("Robô desligado, nao foi possivel executar acao.\n");
    }
}
