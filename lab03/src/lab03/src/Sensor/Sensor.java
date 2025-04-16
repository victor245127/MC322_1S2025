package lab03.src.Sensor;

import lab03.src.Ambiente.Obstaculos;
import lab03.src.Ambiente.Ambiente;

public class Sensor {
    protected int raio;
    //trocar para double e perguntar

    public Sensor (int raio){
        this.raio = raio;
    }

    public boolean monitorar(Ambiente ambiente, Obstaculos obstaculos[], int posX, int posY, int posZ){
        int a, i, j, k;
        for (a = 0; a < obstaculos.length; a++){
            for (i = posX - raio; i <= posX+raio; i++){
                for (j = posY-raio; j <= posY+raio; j++){
                    for (k = posZ-raio; k <= posZ+raio; k++){
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
    } // metodo para monitorar objetos ao redor de um robo a partir de seu raio

    public double getRaio(){
        return this.raio;
    }
}
