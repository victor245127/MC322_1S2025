package RoboVariacoes;

import java.util.ArrayList;

// Robo que consegue destruir um obstáculo ao redor dele 

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;
import RobosBase.EstadoRobo;
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
        try {
            int x = this.getX()[0];
            int y = this.getY()[0];
            int z = this.getZ()[0];
            
            // Verifica se o robô está desligado
            if (getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }

            // Em sua posição
            if (ambiente.temObstaculoEm(x, y, z)){
                int resistencia = ambiente.getResistenciaEm(x, y, z);
                if (this.forcaDestruicao >= resistencia){
                    System.out.printf("Obstaculo em posicao. Destruindo com forca %d\n", forcaDestruicao);
                    ambiente.removerObstaculoEm(x + 1, y, z);
                }
                else {
                    System.out.println("O robo nao tem forca o suficiente para a destruicao desse obstaculo.");
                }
            }

            // Direita
            if (ambiente.temObstaculoEm(x + 1, y, z)) {
                int resistencia = ambiente.getResistenciaEm(x + 1, y, z);
                if (this.forcaDestruicao >= resistencia){
                    System.out.printf("Obstaculo a direita. Destruindo com forca %d\n", forcaDestruicao);
                    ambiente.removerObstaculoEm(x + 1, y, z);
                }
                else {
                    System.out.println("O robo nao tem forca o suficiente para a destruicao desse obstaculo.");
                }

            // Esquerda
            } else if (ambiente.temObstaculoEm(x - 1, y, z)) {
                int resistencia = ambiente.getResistenciaEm(x - 1, y, z);
                if (this.forcaDestruicao >= resistencia){
                    System.out.printf("Obstaculo a esquerda. Destruindo com forca %d\n", forcaDestruicao);
                    ambiente.removerObstaculoEm(x - 1, y, z);
                }
                else {
                    System.out.println("O robo nao tem forca o suficiente para a destruicao desse obstaculo.");
                }

            // Frente
            } else if (ambiente.temObstaculoEm(x, y + 1, z)) {
                int resistencia = ambiente.getResistenciaEm(x, y + 1, z);
                if (this.forcaDestruicao >= resistencia){
                    System.out.printf("Obstaculo em frente. Destruindo com forca %d\n", forcaDestruicao);
                    ambiente.removerObstaculoEm(x, y + 1, z);
                }
                else {
                    System.out.println("O robo nao tem forca o suficiente para a destruicao desse obstaculo.");
                }

            // Trás
            } else if (ambiente.temObstaculoEm(x, y - 1, z)) {
                int resistencia = ambiente.getResistenciaEm(x, y - 1, z);
                if (this.forcaDestruicao >= resistencia){
                    System.out.printf("Obstaculo atras. Destruindo com forca %d\n", forcaDestruicao);
                    ambiente.removerObstaculoEm(x, y - 1, z);
                }
                else {
                    System.out.println("O robo nao tem forca o suficiente para a destruicao desse obstaculo.");
                }
            } else {
                System.out.println("Nenhum obstaculo ao redor para destruir.");
            }
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }   

    public void add_sensores(double raio){
        SensorIdentificacao sensor_ident = new SensorIdentificacao(raio);
        SensorProximidade sensor_prox = new SensorProximidade(raio);
        sensores.add(sensor_ident);
        sensores.add(sensor_prox);
    } // Adiciona os sensores ao seu conjunto de sensores

    public void acionarSensores(Ambiente ambiente) throws ColisaoException, RoboDesligadoException{
        // Aciona os sensores
        try {
            // Verifica se o robô está desligado
            if (getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }
            sensores.get(0).monitorar(ambiente, getX()[0], getY()[0], getZ()[0]);
            sensores.get(1).monitorar(ambiente, getX()[0], getY()[0], getZ()[0]);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public String getDescricao(){
        return "Robo do tipo terrestre que possui sensores e pode destruir um obstaculo proximo a ele.";
    }
}