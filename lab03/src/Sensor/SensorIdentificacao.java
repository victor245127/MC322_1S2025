package Sensor;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;

public class SensorIdentificacao extends Sensor {
    private String tipoObstaculo;

    public SensorIdentificacao(double raio, String tipo){
        super(raio);
        this.tipoObstaculo = tipo;
    }

    public String identificacao(ArrayList<Obstaculos> obstaculos, int index){
        switch (obstaculos.get(index).getObstaculo().getAltura()) {
            case 0:
                if (obstaculos.get(index).getObstaculo().isBloqueador()){
                    return "PEDRA";
                }
                else {
                    return "BURACO";
                }
            case 3:
                if (obstaculos.get(index).getObstaculo().emiteCalor()){
                    return "GIRAFA";
                }
                else {
                    return "PAREDE";
                }
            case 8:
                return "PREDIO";
            default:
                return "NULL";
        }
    }

    public String getIdentificacao(){
        return this.tipoObstaculo;
    }

    public void monitorar(Ambiente ambiente, int posX, int posY, int posZ){
        ArrayList <Obstaculos> obstaculos = ambiente.getObstaculos();
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = posX - r_int; i <= (posX+r_int); i++){
                for (j = posY-r_int; j <= (posY+r_int); j++){
                    for (k = posZ-r_int; k <= (posZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos.get(a).getX1() <= i && i <= obstaculos.get(a).getX2()){
                                if (obstaculos.get(a).getY1() <= j && j <= obstaculos.get(a).getY2()){
                                    if (obstaculos.get(a).getObstaculo().getAltura() <= k){
                                        this.tipoObstaculo = identificacao(obstaculos, a);
                                        System.out.printf("%s identificado entre (%d, %d, %d) e (%d, %d, %d).\n", getIdentificacao(), obstaculos.get(a).getX1(), obstaculos.get(a).getY1(), obstaculos.get(a).getObstaculo().getAltura(), obstaculos.get(a).getX2(), obstaculos.get(a).getY2(), obstaculos.get(a).getObstaculo().getAltura());;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        this.tipoObstaculo = "NULL";
        System.out.println("Obstaculo nao encontrado dentro do raio.\n");
    } // metodo para monitorar objetos ao redor de um robo a partir de seu raio

}