package Sensor;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;

// Classe abstrata do sensor, onde seu método "monitorar" é definido nas subclasses do sensor 
public abstract class Sensor {
    protected double raio;
    // Atributo

    public Sensor(double raio){
        this.raio = raio;
    } // Construtor

    public abstract void monitorar(Ambiente ambiente, int x, int y, int z) throws ColisaoException; // x, y, z do robo
}