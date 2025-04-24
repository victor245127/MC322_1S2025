package Sensor;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;

public class SensorColisao extends Sensor {

    public SensorColisao(double raio){
        super(raio);
    }

    public void monitorar(Ambiente ambiente, int posX, int posY, int posZ){
        ArrayList<Obstaculos> obstaculos = ambiente.getObstaculos();
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = posX - r_int; i <= (posX+r_int); i++){
                for (j = posY-r_int; j <= (posY+r_int); j++){
                    for (k = posZ-r_int; k <= (posZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos.get(a).getX1() <= i && i <= obstaculos.get(a).getX2()){
                                if (obstaculos.get(a).getY1() <= j && j <= obstaculos.get(a).getY2()){
                                    if (obstaculos.get(a).getObstaculo().getAltura() <= k){
                                        System.out.printf("Obstaculo identificado entre (%d, %d, %d) e (%d, %d, %d).\n", obstaculos.get(a).getX1(), obstaculos.get(a).getY1(), obstaculos.get(a).getObstaculo().getAltura(), obstaculos.get(a).getX2(), obstaculos.get(a).getY2(), obstaculos.get(a).getObstaculo().getAltura());
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Obstaculo nao encontrado dentro do alcance do raio.\n");
    }

    public boolean detectarColisoes(Ambiente ambiente, int novoX, int novoY, int novoZ){
        ArrayList<Obstaculos> obstaculos = ambiente.getObstaculos();
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = novoX - r_int; i <= (novoX+r_int); i++){
                for (j = novoY-r_int; j <= (novoY+r_int); j++){
                    for (k = novoZ-r_int; k <= (novoZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos.get(a).getX1() <= i && i <= obstaculos.get(a).getX2()){
                                if (obstaculos.get(a).getY1() <= j && j <= obstaculos.get(a).getY2()){
                                    if (obstaculos.get(a).getObstaculo().getAltura() <= k){
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
    }
}