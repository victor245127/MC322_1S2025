package Exceptions;

public class RoboAutonomoException extends Exception {
    public RoboAutonomoException(){
        super("Nao eh possivel mexer no robo, pois eh autonomo.");
    }
}
