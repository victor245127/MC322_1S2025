package Exceptions;

public class EntidadeImovelException extends Exception {
    public EntidadeImovelException(){
        super("Essa entidade nao eh capaz de se mover.");
    }
}
