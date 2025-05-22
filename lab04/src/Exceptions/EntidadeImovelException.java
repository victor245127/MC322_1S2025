package Exceptions;

public class EntidadeImovelException extends Exception {
    public EntidadeImovelException(){
        super("Essa entidade não é capaz de se mover.\n");
    }
}
