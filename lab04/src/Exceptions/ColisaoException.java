package Exceptions;

// Tratamento de excessão para casos de colisão
public class ColisaoException extends Exception {
    public ColisaoException (int x, int y, int z){
        super("Posição " + x + ", " + y + ", " + z + " já ocupada no mapa.");
    }
}
