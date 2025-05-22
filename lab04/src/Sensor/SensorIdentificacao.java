package Sensor;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;

// Subclasse de sensor que identifica o tipo do obstáculo
public class SensorIdentificacao extends Sensor {
    private String tipoObstaculo; // Atributo

    public SensorIdentificacao(double raio){
        super(raio);
        this.tipoObstaculo = "NULL";
    } // Construtor

    public String identificacao(ArrayList<Obstaculos> obstaculos, int index){
        // Método usado em monitorar que identifica qual obstáculo é de acordo com sua altura,
        // se bloqueia e se emite calor
        switch (obstaculos.get(index).getTipo().getAltura()) {
            case 0:
                if (obstaculos.get(index).getTipo().isBloqueador()){
                    return "PEDRA";
                }
                else {
                    return "BURACO";
                }
            case 3:
                if (obstaculos.get(index).getTipo().emiteCalor()){
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

    // Get que retorna o tipo de obstáculo
    public String getIdentificacao(){
        return this.tipoObstaculo;
    }

    public void monitorar(Ambiente ambiente, int posX, int posY, int posZ){
        // Método "monitorar" desse sensor que procura um obstáculo dentro do raio a partir de uma posição
        // e, caso ache, identifica seu tipo
        ArrayList <Obstaculos> obstaculos = ambiente.getObstaculos();
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = posX - r_int; i <= (posX+r_int); i++){
                for (j = posY-r_int; j <= (posY+r_int); j++){
                    for (k = posZ-r_int; k <= (posZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (obstaculos.get(a).getPosicaoX1() <= i && i <= obstaculos.get(a).getPosicaoX2()){
                                if (obstaculos.get(a).getPosicaoY1() <= j && j <= obstaculos.get(a).getPosicaoY2()){
                                    if (obstaculos.get(a).getTipo().getAltura() <= k){
                                        this.tipoObstaculo = identificacao(obstaculos, a);
                                        System.out.printf("%s de resistência %d identificado entre (%d, %d, %d) e (%d, %d, %d).\n", getIdentificacao(), obstaculos.get(a).getResistencia(), obstaculos.get(a).getPosicaoX1(), obstaculos.get(a).getPosicaoY1(), obstaculos.get(a).getTipo().getAltura(), obstaculos.get(a).getPosicaoX2(), obstaculos.get(a).getPosicaoY2(), obstaculos.get(a).getTipo().getAltura());;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Caso não ache, define o tipo como "NULL"
        this.tipoObstaculo = "NULL";
        System.out.println("Obstaculo nao encontrado dentro do raio.\n");
    } // metodo para monitorar objetos ao redor de um robo a partir de seu raio

}