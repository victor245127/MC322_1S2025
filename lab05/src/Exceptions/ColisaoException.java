package Exceptions;

// Tratamento de excessão para casos de colisão
public class ColisaoException extends Exception {
    public ColisaoException (int x, int y, int z){
        super("Posicao " + x + ", " + y + ", " + z + " ja ocupada no mapa.");
    }
}
