package Exceptions;

// Tratamento de excessão para casos de robôs desligados
public class RoboDesligadoException extends Exception {
    public RoboDesligadoException (){
        super("Robô desligado, não foi possível executar ação.");
    }
}
