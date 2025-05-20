package Exceptions;

// Tratamento de excessão para casos de robôs que não conseguem se comunicam
public class ErroComunicacaoException extends Exception {
    public ErroComunicacaoException(String id){
        super("O robô " + id + " não possui a capacidade de se comunicar.\n");
    }
}
