package RoboVariacoes;

import java.util.ArrayList;

// Robo que consegue destruir um obstáculo ao redor dele 

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;
import Sensor.Sensor;
import Sensor.SensorIdentificacao;
import Sensor.SensorProximidade;
import Sensor.Sensoreavel;

public class RoboTerrestreDestruidor extends RoboTerrestre implements Sensoreavel{
    private int forcaDestruicao; // Atributos do robô
    private ArrayList<Sensor> sensores;

    public RoboTerrestreDestruidor(String id, int x, int y, int z, int velocidadeMaxima, int forcaDestruicao) {
        super(id, x, y, z, velocidadeMaxima);
        this.forcaDestruicao = forcaDestruicao;
        this.sensores = new ArrayList<>();
    } // Construtor 

    public void executarTarefa(Ambiente ambiente) {
        // Habilidade do robô que procura um obstáculo ao seu redor e, caso ache,
        // tenta destruí-lo, que funcionará de acordo com sua força de destruição
        // e a resistência do obstáculo, ou seja, caso a resistência seja menor/igual que a força,
        // o obstáculo é destruído, e vice versa
        int x = this.getX();
        int y = this.getY();
        
        // Direita
        if (ambiente.temObstaculoEm(x + 1, y)) {
            int resistencia = ambiente.getResistenciaEm(x + 1, y);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo à direita. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x + 1, y);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Esquerda
        } else if (ambiente.temObstaculoEm(x - 1, y)) {
            int resistencia = ambiente.getResistenciaEm(x - 1, y);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo à esquerda. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x - 1, y);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Frente
        } else if (ambiente.temObstaculoEm(x, y + 1)) {
            int resistencia = ambiente.getResistenciaEm(x, y + 1);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo em frente. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x, y + 1);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Trás
        } else if (ambiente.temObstaculoEm(x, y - 1)) {
            int resistencia = ambiente.getResistenciaEm(x, y - 1);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo atrás. Destruindo com forca %d", forcaDestruicao);
                ambiente.removerObstaculoEm(x, y - 1);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }
        } else {
            System.out.println("Nenhum obstáculo ao redor para destruir.\n");
        }
    }   

    public void add_sensores(double raio){
        SensorIdentificacao sensor_ident = new SensorIdentificacao(raio);
        SensorProximidade sensor_prox = new SensorProximidade(raio);
        sensores.add(sensor_ident);
        sensores.add(sensor_prox);
    }

    public void acionarSensores(){
        sensores.get(0).monitorar(null, x, y, z);
        sensores.get(1).monitorar(null, x, y, z);
    }

    public String getDescricao(){
        return "Robô do tipo terrestre que possui sensores e pode destruir um obstáculo próximo a ele.\n";
    }
}