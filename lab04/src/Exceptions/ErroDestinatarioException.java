package Exceptions;

// Tratamento de erro para caso o destinatário de uma mensagem seja igual ao remetente
public class ErroDestinatarioException extends Exception {
    public ErroDestinatarioException(){
        super("O destinatário não pode ser igual ao remetente.");
    }
}
