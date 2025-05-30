package Exceptions;

// Tratamento de excessão para casos de robôs que não conseguem se comunicam
public class ErroComunicacaoException extends Exception {
    public ErroComunicacaoException(){
        super("Um dos robos não possui a capacidade de se comunicar.");
    }
}
