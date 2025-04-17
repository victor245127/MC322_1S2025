package lab03.src.Sensor;

import java.util.ArrayList;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;

public class SensorColisao extends Sensor {
    private boolean colisao;

    public SensorColisao(int raio, boolean colisao){
        super(raio);
        this.colisao = colisao;
    }

    public boolean detectarColisoes(ArrayList<Obstaculos> obstaculos, Ambiente ambiente, int novoX, int novoY, int novoZ){
        int a, i, j, k;
        for (a = 0; a < obstaculos.size(); a++){
            for (i = novoX - raio; i <= novoX+raio; i++){
                for (j = novoY-raio; j <= novoY+raio; j++){
                    for (k = novoZ-raio; k <= novoZ+raio; k++){
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
