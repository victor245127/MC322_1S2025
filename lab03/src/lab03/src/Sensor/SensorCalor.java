package lab03.src.Sensor;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;

public class SensorCalor extends Sensor {
    private int obstaculosTermicos;

    public SensorCalor(int raio, int obsTermicos) {
        super(raio);
        this.obstaculosTermicos = obsTermicos;
    } 

    public int monitorar_calor(Ambiente ambiente, Obstaculos obstaculos[], int posX, int posY, int posZ){
        int a, i, j, k;
        for (a = 0; a < obstaculos.length; a++){
            for (i = posX - raio; i <= posX+raio; i++){
                for (j = posY-raio; j <= posY+raio; j++){
                    for (k = posZ-raio; k <= posZ+raio; k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos[a].getX1() <= i && i <= obstaculos[a].getX2()){
                                if (obstaculos[a].getY1() <= j && j <= obstaculos[a].getY2()){
                                    if (obstaculos[a].getObstaculo().getAltura() <= k){
                                        if(obstaculos[a].getObstaculo().emiteCalor()){
                                            this.obstaculosTermicos++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return this.obstaculosTermicos;
    } // metodo para monitorar objetos ao redor de um robo a partir de seu raio
}
