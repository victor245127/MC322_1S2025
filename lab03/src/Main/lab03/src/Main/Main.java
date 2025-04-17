package lab03.src.Main;

import java.util.Scanner;

import java.util.ArrayList;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;
import lab03.src.Ambiente.TiposObstaculo;
import lab03.src.RoboBase.Robo;
import lab03.src.RoboBase.RoboAereo;
import lab03.src.RoboBase.RoboTerrestre;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorColisao;
import lab03.src.Sensor.SensorIdentificacao;
import lab03.src.RoboVariacoes.RoboAereoDistorcao;
import lab03.src.RoboVariacoes.RoboAereoObservador;
import lab03.src.RoboVariacoes.RoboTerrestreExplorador;
import lab03.src.RoboVariacoes.RoboTerrestreRemocao;



// classe principal
public class Main {
    public static void visualizarRobos(ArrayList<Robo> robos){
        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) != null){
                System.out.printf("Robo %s: (%d, %d, %d), direcao %s\n", robos.get(i).getNome(), robos.get(i).getX(), robos.get(i).getY(), robos.get(i).getZ());
            }
        }
    }

    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int largura, altura;

        String nome, tipo, nome_obstaculo;
        int posX = 0, posY = 0, posZ = 0, raio_sensor;
        int velAtual, velMax, altMax;
        boolean colisao = false;
        int X1, Y1, X2, Y2;
        Obstaculos obstaculo = new Obstaculos(0, 0, 0, 0, null);

        RoboTerrestre roboT = new RoboTerrestre("", "", posX, posY, posZ, 0, 0, null, null);
        RoboAereo roboA = new RoboAereo("", "", posX, posY, posZ, 0, null, null, null);

        System.out.println("Ola, bem vindo ao ambiente virtual de robos, insira as seguintes informacoes: ");
        largura = ler.nextInt();
        altura = ler.nextInt();
        ArrayList<Robo> robosAtivos = new ArrayList<>();


        ArrayList<Obstaculos> obstaculos = new ArrayList<>();
        Ambiente ambiente = new Ambiente(altura, largura, robosAtivos, obstaculos);
        System.out.printf("\nAmbiente definido, dimensoes: %dm x %dm x %dm\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
        System.out.println("Digite o raio dos sensores: ");
        raio_sensor = ler.nextInt();
        Sensor sensor = new Sensor(raio_sensor);
        SensorColisao sensor_colisao = new SensorColisao(raio_sensor, colisao);
        SensorIdentificacao sensor_identificacao = new SensorIdentificacao(raio_sensor, "NULL");
        
        int escolha;
        boolean continuar = true;

        while (continuar){
            System.out.println("Qual o proximo passo: \n[1] Criar robo\n[2] Criar obstaculo\n[3] Andar com robo\n[4] Voar com robo aereo\n[5] Remover robo\n[6] Visualizar status dos robos\n[7] Finalizar o programa\n");
            escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do robo e seu tipo: ");
                    nome = ler.next();
                    tipo = ler.next();
                    nome.toLowerCase();
                    tipo.toLowerCase();
                    if (tipo == "terrestre"){
                        System.out.println("Digite a posicao X e Y, sua velocidade atual e maxima: ");
                        posX = ler.nextInt();
                        posY = ler.nextInt();
                        posZ = 0;
                        velAtual = ler.nextInt();
                        velMax = ler.nextInt();
                        if (!ambiente.dentroDosLimites(posX, posY, posZ)){
                            System.out.println("Posicao nao disponivel, retornando ao menu...\n");
                            break;
                        }
                        roboT.setDirecao(ambiente);
                        ambiente.setRoboTerrestre(roboT, nome, posX, posY, posZ, velMax, velAtual, sensor_identificacao, sensor_colisao);
                    }
                    else if (tipo == "aereo"){
                        System.out.println("Digite as posicoes X, Y e Z e sua altitude maxima: ");
                        posX = ler.nextInt();
                        posY = ler.nextInt();
                        posZ = ler.nextInt();
                        altMax = ler.nextInt();
                        if (!ambiente.dentroDosLimites(posX, posY, posZ)){
                            System.out.println("Posicao nao disponivel, retornando ao menu...\n");
                            break;
                        }
                        
                        roboA.setDirecao(ambiente);
                        ambiente.setRoboAereo(roboA, nome, posX, posY, posZ, altMax, sensor, sensor_colisao, sensor_identificacao);
                    }
                    ambiente.setObstaculo(obstaculo, posX, posX, posY, posY, TiposObstaculo.ROBO);
                    System.out.println("Robo adicionado ao ambiente!\n");
                    break;
                case 2:
                    System.out.println("Digite o tipo de obstaculo, e sua posicao X1, Y1, X2, Y2: ");
                    nome_obstaculo = ler.next();
                    X1 = ler.nextInt();
                    Y1 = ler.nextInt();
                    X2 = ler.nextInt();
                    Y2 = ler.nextInt();
                    if (!ambiente.dentroDosLimites(X1, Y1, 0) && !ambiente.dentroDosLimites(X2, Y2, 0)){
                        System.out.println("Posicao nao disponivel, retornando ao menu...\n");
                        break;
                    }
                    nome_obstaculo.toUpperCase();
                    switch (nome_obstaculo) {
                        case "PAREDE":
                            if (ambiente.getAltura() < 5){
                                System.out.println("Altura nao suficiente, retornando ao menu...\n");
                                break;
                            }
                            ambiente.setObstaculo(obstaculo, X1, X2, Y1, Y2, TiposObstaculo.PAREDE);
                            break;
                        case "PEDRA":
                            ambiente.setObstaculo(obstaculo, X1, X2, Y1, Y2, TiposObstaculo.PEDRA);
                            break;
                        case "ROBO":
                            System.out.println("Para adicionar um robo como obstaculo, o adicione ao ambiente primeiro, retornando ao menu...\n");
                            break;
                        case "GIRAFA":
                            if (ambiente.getAltura() < 5){
                                System.out.println("Altura nao suficiente, retornando ao menu...\n");
                                break;
                            }
                            ambiente.setObstaculo(obstaculo, X1, X2, Y1, Y2, TiposObstaculo.GIRAFA);
                            break;
                        case "PREDIO":
                            if (ambiente.getAltura() < 30){
                                System.out.println("Altura nao suficiente, retornando ao menu...\n");
                                break;
                            }
                            ambiente.setObstaculo(obstaculo, X1, X2, Y1, Y2, TiposObstaculo.PREDIO);
                            break;
                        case "BURACO":
                            ambiente.setObstaculo(obstaculo, X1, X2, Y1, Y2, TiposObstaculo.BURACO);
                            break;
                        default:
                            System.out.println("Obstaculo nao identificado, retornando ao menu...\n");
                            break;
                    }
                case 3:
                    System.out.println("Escolha o robo: ");
                    nome = ler.next();
                    nome.toLowerCase();
                    for (int i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome() == nome){
                            if (robosAtivos.get(i) instanceof RoboTerrestre){
                                System.out.println("Digite as novas posicoes X e Y e sua nova velocidade: ");
                                posX = ler.nextInt();
                                posY = ler.nextInt();
                                velAtual = ler.nextInt();
                                robosAtivos.get(i).mover(posX, posY, ambiente, velAtual, obstaculos);
                                //VERIFICAR ESSE PROBLEMA
                            } 
                            else if (robosAtivos.get(i) instanceof RoboAereo){
                                robosAtivos.get(i).mover(posX, posY, ambiente);
                            }
                            break;
                        }
                    }
                    System.out.println("Robo nao encontrado, retornando ao menu...\n");
                case 4:
                    

                case 5:
                    System.out.println("Digite o nome do robo: ");
                    nome = ler.next();
                    nome.toLowerCase();
                    for (int i = 0; i < robosAtivos.size(); i++){
                        if (robosAtivos.get(i).getNome() == nome){
                            ambiente.removeRobo(roboA);
                            System.out.printf("Robo %s removido!", nome);
                            break;
                        }
                    }
                    System.out.println("Robo nao encontrado, retornando ao menu...\n");
                    break;


                case 6:
                    visualizarRobos(robosAtivos);
                    break;
                case 7:
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

