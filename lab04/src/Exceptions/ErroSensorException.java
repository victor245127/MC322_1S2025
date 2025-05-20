package Exceptions;

public class ErroSensorException extends Exception{
    public ErroSensorException(String id){
        super("Robô " + id + " não possui sensor.\n");
    }
}
