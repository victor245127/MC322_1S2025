package lab03.src.Sensor;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;

public class SensorIdentificacao extends Sensor {
    private String tipoObstaculo;

    public SensorIdentificacao(int raio, String tipo){
        super(raio);
        this.tipoObstaculo = tipo;
    }

    public String identificacao(Obstaculos obstaculos[], int index){
        switch (obstaculos[index].getObstaculo().getAltura()) {
            case 0:
                if (obstaculos[index].getObstaculo().isBloqueador()){
                    return "PEDRA";
                }
                else {
                    return "BURACO";
                }
            case 1:
                return "ROBO";
            case 5:
                if (obstaculos[index].getObstaculo().emiteCalor()){
                    return "GIRAFA";
                }
                else {
                    return "PAREDE";
                }
            case 30:
                return "PREDIO";
            default:
                return "NULL";
        }
    }

    public String getIdentificacao(){
        return this.tipoObstaculo;
    }

    public void monitorar_identificar(Ambiente ambiente, Obstaculos obstaculos[], int posX, int posY, int posZ){
        int a, i, j, k;
        for (a = 0; a < obstaculos.length; a++){
            for (i = posX - raio; i <= posX+raio; i++){
                for (j = posY-raio; j <= posY+raio; j++){
                    for (k = posZ-raio; k <= posZ+raio; k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos[a].getX1() <= i && i <= obstaculos[a].getX2()){
                                if (obstaculos[a].getY1() <= j && j <= obstaculos[a].getY2()){
                                    if (obstaculos[a].getObstaculo().getAltura() <= k){
                                        this.tipoObstaculo = identificacao(obstaculos, a);
                                        System.out.printf("%s identificado entre (%d, %d, %d) e (%d, %d, %d).\n", getIdentificacao(), obstaculos[a].getX1(), obstaculos[a].getY1(), obstaculos[a].getObstaculo().getAltura(), obstaculos[a].getX2(), obstaculos[a].getY2(), obstaculos[a].getObstaculo().getAltura());;
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
        System.out.println("Obstaculo nao encontrado dentro do raio.\n");;
    } // metodo para monitorar objetos ao redor de um robo a partir de seu raio

}