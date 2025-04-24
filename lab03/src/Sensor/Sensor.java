package Sensor;

import Ambiente.Ambiente;

public abstract class Sensor {
    protected double raio;
    //trocar para double e perguntar

    public Sensor (double raio){
        this.raio = raio;
    }

    public abstract void monitorar(Ambiente ambiente, int posX, int posY, int posZ);

    public double getRaio(){
        return this.raio;
    }
}
