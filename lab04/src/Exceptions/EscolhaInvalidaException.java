package Exceptions;

// Tratamento de erro para casos de opção inválida no scanner
public class EscolhaInvalidaException extends Exception {
    public EscolhaInvalidaException(){
        super("Opção inválida...");
    }
}
