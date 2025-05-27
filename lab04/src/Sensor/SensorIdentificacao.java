package Sensor;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Entidade.Entidade;
import Exceptions.ColisaoException;

// Subclasse de sensor que identifica o tipo do obstáculo
public class SensorIdentificacao extends Sensor {
    private String tipoObstaculo; // Atributo

    public SensorIdentificacao(double raio){
        super(raio);
        this.tipoObstaculo = "NULL";
    } // Construtor

    public String identificacao(Obstaculos obstaculo){
        // Método usado em monitorar que identifica qual obstáculo é de acordo com sua altura,
        // se bloqueia e se emite calor
        switch (obstaculo.getTipoObstaculo().getAltura()) {
            case 0:
                if (obstaculo.getTipoObstaculo().isBloqueador()){
                    return "PEDRA";
                }
                else {
                    return "BURACO";
                }
            case 3:
                if (obstaculo.getTipoObstaculo().emiteCalor()){
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

    public void monitorar(Ambiente ambiente, int posX, int posY, int posZ) throws ColisaoException{
        // Método "monitorar" desse sensor que procura um obstáculo dentro do raio a partir de uma posição
        // e, caso ache, identifica seu tipo
        ArrayList <Entidade> obstaculos = ambiente.getObstaculos();
        int a, i, j, k, r_int = (int) Math.round(this.raio);
        for (a = 0; a < obstaculos.size(); a++){
            for (i = posX - r_int; i <= (posX+r_int); i++){
                for (j = posY-r_int; j <= (posY+r_int); j++){
                    for (k = posZ-r_int; k <= (posZ+r_int); k++){
                        if (ambiente.dentroDosLimites(i, j, k)){
                            if (((Obstaculos) obstaculos.get(a)).getX()[0] <= i && i <= ((Obstaculos) obstaculos.get(a)).getX()[1]){
                                if (((Obstaculos) obstaculos.get(a)).getY()[0] <= j && j <= ((Obstaculos) obstaculos.get(a)).getY()[1]){
                                    if (((Obstaculos) obstaculos.get(a)).getTipoObstaculo().getAltura() <= k){
                                        this.tipoObstaculo = identificacao(((Obstaculos) obstaculos.get(a)));
                                        System.out.printf("%s de resistência %d identificado entre (%d, %d, %d) e (%d, %d, %d).\n", getIdentificacao(), ((Obstaculos) obstaculos.get(a)).getResistencia(), ((Obstaculos) obstaculos.get(a)).getX()[0], ((Obstaculos) obstaculos.get(a)).getY()[0], ((Obstaculos) obstaculos.get(a)).getTipoObstaculo().getAltura(), ((Obstaculos) obstaculos.get(a)).getX()[1], ((Obstaculos) obstaculos.get(a)).getY()[1], ((Obstaculos) obstaculos.get(a)).getTipoObstaculo().getAltura());;
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