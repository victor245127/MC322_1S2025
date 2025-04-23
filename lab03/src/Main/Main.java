import java.util.Scanner;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Ambiente.TiposObstaculo;
import RobosBase.Robo;
import RobosBase.RoboAereo;
import RobosBase.RoboTerrestre;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboAereoObservador;
import RoboVariacoes.RoboTerrestreExplorador;
import RoboVariacoes.RoboTerrestreRemocao;
import Sensor.Sensor;
import Sensor.SensorColisao;
import Sensor.SensorIdentificacao;

import java.util.ArrayList;

// classe principal
public class Main {
    public static void visualizarRobos(ArrayList<Robo> robos){
        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) != null){
                System.out.printf("Robo %s: (%d, %d, %d), direcao %s\n", robos.get(i).getNome(), robos.get(i).getX(), robos.get(i).getY(), robos.get(i).getZ(), robos.get(i).getDirecao());
            }
        }
    }

    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);

        Obstaculos predio = new Obstaculos(10, 10, 30, 30, TiposObstaculo.PREDIO);
        Obstaculos buraco = new Obstaculos(5, 50, 5, 50, TiposObstaculo.BURACO);
        Obstaculos parede = new Obstaculos(90, 50, 90, 60, TiposObstaculo.PAREDE);
        Obstaculos girafa = new Obstaculos(40,5, 42, 5, TiposObstaculo.GIRAFA);
        Obstaculos pedra = new Obstaculos(60, 80, 60, 80, TiposObstaculo.PEDRA);

        ArrayList<Robo> robosAtivos = new ArrayList<>();
        ArrayList<Obstaculos> obstaculos = new ArrayList<>();

        Sensor sensor = new Sensor(9.7);
        SensorColisao sensor_colisao = new SensorColisao(9.7, false);
        SensorIdentificacao sensor_identificacao = new SensorIdentificacao(9.7, "NULL");

        RoboTerrestreExplorador robo1 = new RoboTerrestreExplorador("robo1", null, 35, 55, 15, 10, null, null);
        RoboTerrestreRemocao robo2 = new RoboTerrestreRemocao("robo2", null, 70, 2, 30, 20, 0, 0, null, null);
        RoboAereoObservador robo3 = new RoboAereoObservador("robo3", null, 1, 1, 50, 120, null, null, null, 0);
        RoboAereoFalcao robo4 = new RoboAereoFalcao("robo4", null, 95, 100, 80, 90, 0);

        Ambiente ambiente = new Ambiente(100, 100, 100, robosAtivos, obstaculos);
        System.out.printf("\nAmbiente definido, dimensoes: %dm x %dm x %dm\n", ambiente.getLargura(), ambiente.getComprimento(), ambiente.getAltura());
        
        String nome;
        int escolha, i, posX, posY;
        boolean continuar = true;

        while (continuar){
            System.out.println("Qual o proximo passo: \n[1] Monitorar ao redor com robo\n[2] Identificar obstaculos\n[3] Mover robo\n[4] Visualizar status dos robos\n[5] Finalizar o programa\n");
            escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Escolha o robo: ");
                    nome = ler.next();
                    nome = nome.toLowerCase();
                    
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    System.out.println("Escolha o robo: ");
                    nome = ler.next();
                    nome = nome.toLowerCase();
                    for (i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome().equals(nome)){
                            if (robosAtivos.get(i) instanceof RoboTerrestre){
                                System.out.println("Digite as novas posicoes X e Y e sua nova velocidade: ");
                                posX = ler.nextInt();
                                posY = ler.nextInt();
                                velAtual = ler.nextInt();
                                ((RoboTerrestre) robosAtivos.get(i)).mover(posX, posY, ambiente, velAtual, obstaculos);
                            } 
                            else if (robosAtivos.get(i) instanceof RoboAereo){
                                System.out.println("Digite as novas posicoes X e Y");
                                posX = ler.nextInt();
                                posY = ler.nextInt();
                                robosAtivos.get(i).mover(posX, posY, ambiente);
                            }
                            break;
                        }
                    }
                    if (i == robosAtivos.size()){
                        System.out.println("Robo nao encontrado, retornando ao menu...\n");
                    }
                    break;
                case 4:
                    System.out.println("Digite o nome do robo: ");
                    nome = ler.next();
                    nome = nome.toLowerCase();
                    for (i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome().equals(nome)){
                            if (robosAtivos.get(i) instanceof RoboAereo){
                                System.out.println("Digite a diferença de altura do robo: ");
                                deltaAlt = ler.nextInt();
                                if (deltaAlt < 0){
                                    ((RoboAereo) robosAtivos.get(i)).descer(deltaAlt, ambiente, obstaculos);
                                    break;
                                }
                                else {
                                    ((RoboAereo)robosAtivos.get(i)).subir(deltaAlt, ambiente, obstaculos);
                                    break;
                                }
                            }
                            else {
                                System.out.println("Nao eh do tipo aereo, retornando ao menu...\n");
                            }
                        }
                    }
                    if (i == robosAtivos.size()){
                        System.out.println("Robo nao encontrado, retornando ao menu...\n");
                    }
                    break;
                case 5:
                    visualizarRobos(robosAtivos);
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Finalizando o programa...\nEND");
                    break;
                default:
                    break;
            }
        }
        ler.close();
    }
}

