package lab03.src.Main;

import java.util.Scanner;

import lab02.src.RoboBase.RoboAereo;
import lab02.src.RoboBase.RoboTerrestre;

import java.util.ArrayList;

import lab03.src.Ambiente.Ambiente;
import lab03.src.Ambiente.Obstaculos;
import lab03.src.Ambiente.TiposObstaculo;
import lab03.src.RoboBase.Robo;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorColisao;
import lab03.src.Sensor.SensorIdentificacao;
import lab03.src.RoboVariacoes.RoboAereoDistorcao;
import lab03.src.RoboVariacoes.RoboAereoObservador;
import lab03.src.RoboVariacoes.RoboTerrestreExplorador;
import lab03.src.RoboVariacoes.RoboTerrestreRemocao;



// classe principal
public class Main {
    public void visualizarRobos(Robo robos[]){

    }

    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int largura, altura;

        String nome, tipo, direcao;
        int posX, posY, posZ, raio_sensor;
        int velAtual, velMax, altMax;
        boolean colisao = false;

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
            System.out.println("Qual o proximo passo: \n[1] Criar robo\n[2] Criar obstaculo\n[3] Andar com robo\n[4] Voar com robo aereo\n[5] Finalizar o programa\n");
            escolha = ler.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome do robo e seu tipo: ");
                    nome = ler.next();
                    tipo = ler.next();
                    tipo.toLowerCase();
                    if (tipo == "terrestre"){
                        System.out.println("Digite a posicao X e Y, sua velocidade atual e maxima: ");
                        posX = ler.nextInt();
                        posY = ler.nextInt();
                        velAtual = ler.nextInt();
                        velMax = ler.nextInt();
                        RoboTerrestre roboT = new RoboTerrestre(nome, "", posX, posY, posZ, velAtual, sensor, sensor_colisao);
                        roboT.setDirecao(ambiente);
                        // ajustar essa porra do caralho de arquivo
                    }
                    else if (tipo == "aereo"){
                        System.out.println("Digite as posicoes X, Y e Z e sua altitude maxima: ");
                        posX = ler.nextInt();
                        posY = ler.nextInt();
                        posZ = ler.nextInt();
                        altMax = ler.nextInt();
                        RoboAereo roboA = new RoboAereo(nome, "", posX, posY, posZ, altMax, sensor, sensor_colisao, sensor_identificacao);
                        roboA.setDirecao(ambiente);
                        //ARQUIVO FILHO DA PUTA
                    }
                    System.out.println("Robo adicionado ao ambiente!\n");
                    break;
                case 2:

            


                case 5:
                    continuar = false;
                    break;
                default:
                    break;
            }
        }
        
        ler.close();
    }
}

