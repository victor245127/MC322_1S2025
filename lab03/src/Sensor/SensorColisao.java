package Sensor;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;

public class SensorColisao extends Sensor {
    private boolean colisao;

    public SensorColisao(double raio, boolean colisao){
        super(raio);
        this.colisao = colisao;
    }

    public boolean detectarColisoes(ArrayList<Obstaculos> obstaculos, Ambiente ambiente, int novoX, int novoY, int novoZ){
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = novoX - r_int; i <= (novoX+r_int); i++){
                for (j = novoY-r_int; j <= (novoY+r_int); j++){
                    for (k = novoZ-r_int; k <= (novoZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos.get(a).getX1() <= i && i <= obstaculos.get(a).getX2()){
                                if (obstaculos.get(a).getY1() <= j && j <= obstaculos.get(a).getY2()){
                                    if (obstaculos.get(a).getObstaculo().getAltura() <= k){
                                        this.colisao = true;
                                        return colisao;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return colisao;
    }
}
