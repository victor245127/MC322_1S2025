package Sensor;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;

public class Sensor {
    protected double raio;
    //trocar para double e perguntar

    public Sensor (double raio){
        this.raio = raio;
    }

    public boolean monitorar(Ambiente ambiente, Obstaculos obstaculos[], int posX, int posY, int posZ){
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.length; a++){
            for (i = posX - r_int; i <= posX+r_int; i++){
                for (j = posY-r_int; j <= posY+r_int; j++){
                    for (k = posZ-r_int; k <= posZ+r_int; k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos[a].getX1() <= i && i <= obstaculos[a].getX2()){
                                if (obstaculos[a].getY1() <= j && j <= obstaculos[a].getY2()){
                                    if (obstaculos[a].getObstaculo().getAltura() <= k){
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    } // verificar se ha parametros ou nao

    public double getRaio(){
        return this.raio;
    }
}
