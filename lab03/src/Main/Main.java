import java.util.Scanner;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Ambiente.TiposObstaculo;
import RobosBase.Robo;
import RobosBase.RoboAereo;
import RobosBase.RoboTerrestre;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboTerrestreDestruidor;
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

    public static void visualizarObstaculos(ArrayList<Obstaculos> obstaculos){
        for (Obstaculos obs : obstaculos){
            if (obs != null){
                System.out.printf("Obstaculo %s: (%d, %d, %d) a (%d, %d, %d)\n", obs.getObstaculo().getTipo(), obs.getX1(), obs.getY1(), obs.getObstaculo().getAltura(), obs.getX2(), obs.getY2(), obs.getObstaculo().getAltura());
            }
        }
    }


    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        System.out.println("Ola, seja bem-vindo(a) ao ambiente virtual de robos!\n");

        Obstaculos predio = new Obstaculos(1, 1, 1, 1, TiposObstaculo.PREDIO);
        Obstaculos buraco = new Obstaculos(5, 6, 5, 6, TiposObstaculo.BURACO);
        Obstaculos parede = new Obstaculos(9, 9, 9, 9, TiposObstaculo.PAREDE);

        ArrayList<Robo> robosAtivos = new ArrayList<>();
        ArrayList<Obstaculos> obstaculos = new ArrayList<>();

        SensorColisao sensor_prox = new SensorColisao(9.7);
        SensorIdentificacao sensor_identificacao = new SensorIdentificacao(9.7, "NULL");

        Ambiente ambiente = new Ambiente(10, 10, 10, robosAtivos, obstaculos);
        System.out.printf("Ambiente definido, dimensoes: %dm x %dm x %dm\n", ambiente.getLargura(), ambiente.getComprimento(), ambiente.getAltura());
        
        ambiente.setObstaculo(predio);
        ambiente.setObstaculo(buraco);
        ambiente.setObstaculo(parede);
        //ambiente.setObstaculo(girafa);
        //ambiente.setObstaculo(pedra);

        RoboAereoFalcao drone = new RoboAereoFalcao("Drone", 5, 5, 3, 10, 2, sensor_prox, sensor_identificacao);
        RoboTerrestreDestruidor tanque = new RoboTerrestreDestruidor("Tanque", 2, 2, 5, 1, 7, sensor_prox);
        ambiente.setRobo(drone);
        ambiente.setRobo(tanque);

        String nome;
        int escolha, i, posX, posY, velAtual;
        boolean continuar = true;

        while (continuar){
            System.out.println("----------MENU INTERATIVO----------\n");
            System.out.println("Qual o proximo passo: \n[1] Visualizar status dos robôs \n[2] Visualizar obstaculos no ambiente\n[3] Mover robo\n[4] Monitorar ambiente ao redor\n[5] Ativar habilidades\n[6] Finalizar o programa\n");
            escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    visualizarRobos(robosAtivos);
                    break;
                case 2:
                    visualizarObstaculos(obstaculos);
                    break;
                case 3:
                    System.out.println("Escolha o robo pelo nome: \n");
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
                    System.out.println("Escolha o robo pelo nome: \n");
                    nome = ler.next();
                    nome = nome.toLowerCase();
                    for (i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome().equals(nome)){
                            if (robosAtivos.get(i) instanceof RoboTerrestreDestruidor){
                                ((RoboTerrestreDestruidor) robosAtivos.get(i)).identificarObstaculo(ambiente);
                            } 
                            else if (robosAtivos.get(i) instanceof RoboAereo){
                                ((RoboAereoFalcao) robosAtivos.get(i)).identificar(ambiente);
                            }
                            break;
                        }
                    }
                    if (i == robosAtivos.size()){
                        System.out.println("Robo nao encontrado, retornando ao menu...\n");
                    }
                    break;
                case 5:
                    System.out.println("Escolha o robo pelo nome: \n");
                    nome = ler.next();
                    nome = nome.toLowerCase();
                    for (i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome().equals(nome)){
                            if (robosAtivos.get(i) instanceof RoboTerrestreDestruidor){
                                System.out.printf("Habilidade: destruir obstaculo\nO %s procura um obstaculo ao seu redor e tenta destruir com uma força x, e caso a resistencia do obstaculo seja igual ou menor que sua forca, o destroi\n", robosAtivos.get(i).getNome());
                                ((RoboTerrestreDestruidor) robosAtivos.get(i)).destruirObstaculo(ambiente);
                            } 
                            else if (robosAtivos.get(i) instanceof RoboAereo){
                                System.out.printf("Habilidade: Visao\n O %s varre o ambiente na direcao atual ate um certo alcance, detectando obstaculos a frente\n", robosAtivos.get(i).getNome());
                                ((RoboAereoFalcao) robosAtivos.get(i)).visao(ambiente);
                            }
                            break;
                        }
                    }

                    if (i == robosAtivos.size()){
                        System.out.println("Robo nao encontrado, retornando ao menu...\n");
                    }
                    
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

