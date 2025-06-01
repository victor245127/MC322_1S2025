package Exceptions;

// Tratamento de erro para caso uma posição esteja além dos limites do ambiente
public class ForaDosLimitesException extends Exception {
    public ForaDosLimitesException(){
        super("Posicao fora dos limites, inalcancavel.");
    }
}
