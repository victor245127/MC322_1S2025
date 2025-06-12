package SubsistemasRobo;

import java.util.ArrayList;

import Sensor.Sensor;
import Sensor.SensorIdentificacao;
import Sensor.SensorProximidade;

// Módulo que gerencia os sensores de um robô
public class GerenciadorSensores {
    private ArrayList<Sensor> sensores;

    public void adicionarSensores(double raio){
        sensores = new ArrayList<>();
        sensores.add(new SensorIdentificacao(raio));
        sensores.add(new SensorProximidade(raio));
    }

    public ArrayList<Sensor> getSensores(){
        return sensores;
    }
}
