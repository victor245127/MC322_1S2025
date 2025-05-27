import java.util.Scanner;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Ambiente.TiposObstaculo;
import Entidade.TipoEntidade;
import RobosBase.Robo;
import RobosBase.RoboAereo;
import RobosBase.RoboTerrestre;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboAereoObservador;
import RoboVariacoes.RoboTerrestreDestruidor;
import RoboVariacoes.RoboTerrestreExplorador;
import Sensor.SensorIdentificacao;
import Sensor.SensorProximidade;

// Classe main, para rodar o código em conjunto
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Variável usada para leitura de dados

        // Criação do ambiente
        Ambiente ambiente = new Ambiente(10, 10, 10);
        // Ambiente virtual dos robôs

        // Criação de robôs
        RoboAereoFalcao drone = new RoboAereoFalcao("Drone", "norte", 5, 5, 3, 10,2);
        drone.adicionarSensor(new SensorProximidade(3));
        drone.adicionarSensor(new SensorIdentificacao(10, ""));
        // Criação do robô aereo falcâo, com a adição de seus sensores

        RoboTerrestreDestruidor tanque = new RoboTerrestreDestruidor("Tanque", "leste", 2, 2, 5, 7);
        tanque.adicionarSensor(new SensorProximidade(2));
        // Criação do robo terrestre destruidor, e a adição de seu sensor de proximidade

        // Adiciona robôs ao ambiente
        ambiente.adicionarRobo(drone);
        ambiente.adicionarRobo(tanque);

        // Criação de obstáculos
        ambiente.adicionarObstaculo(new Obstaculos(3, 2, 3, 2, TiposObstaculo.PAREDE));
        ambiente.adicionarObstaculo(new Obstaculos(5, 6, 5, 6, TiposObstaculo.PREDIO));

        // Variável boleana usada para continuar ou não a interação
        boolean executando = true;
        int opcao;

        // Menu interativo com ações diferentes a serem escolhidas, para escolher basta digitar
        // o número da ação
        while (executando) {
            System.out.println("\n--- MENU INTERATIVO ---");
            System.out.println("1. Visualizar status dos robôs e do ambiente");
            System.out.println("2. Mover robô");
            System.out.println("3. Ativar sensores");
            System.out.println("4. Verificar colisões");
            System.out.println("5. Ativar habilidades");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            // Escolhe a ação a ser feita
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Mostra as dimensões do ambiente e o status dos robôs e dos obstáculos
                    System.out.printf("\nDimensoes do ambiente:\nLargura: %d\nAltura: %d\nProfundidade: %d", ambiente.getLargura(), ambiente.getAltura(), ambiente.getProfundidade());

                    System.out.println("\nObstaculos no ambiente:");
                    // Loop para mostrar os obstáculos
                    for (Obstaculos obs : ambiente.getObstaculos()) {
                        System.out.printf("Tipo: %s\n", obs.getTipo());
                        System.out.printf("Posição: (%d, %d, %d), até (%d, %d, %d)\n", obs.getPosicaoX1(), obs.getPosicaoY1(), 0, obs.getPosicaoX2(), obs.getPosicaoY2(), obs.getTipo().getAltura());
                        System.out.printf("Resistência: %d\n",obs.getResistencia());
                    }

                    System.out.println("\nRobôs no ambiente:");
                    int index = 1;
                    for (Robo r : ambiente.getRobosAtivos()) {
                        // Loop para mostrar os robôs, com condicionais relacionadas aos seus tipos
                        if (r instanceof RoboAereo){
                            System.out.printf("%d. %s\n", index, r.getNome());
                            System.out.printf("Tipo do robo: %s\n", r.getClass().getSimpleName());
                            ((RoboAereo)r).exibirPosicaoAereo();
                        }
                        else{
                            System.out.printf("%d. %s\n", index, r.getNome());
                            System.out.printf("Tipo do robo: %s\n", r.getClass().getSimpleName());
                            r.exibirPosicao(); 
                        }
                        index++;
                    }
                    break;

                case 2:
                    // Mover um robô, escolhe qual mover
                    System.out.println("\nEscolha o robô:");
                    for (int i = 0; i < ambiente.getRobosAtivos().size(); i++) {
                        Robo r = ambiente.getRobosAtivos().get(i);
                        System.out.printf("%d. %s\n", (i+1), r.getNome());
                    }

                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    // Caso o número do robô seja menor que 1 ou maior que a quantidade no ambiente,
                    // vira uma opção inválida
                    if (escolha < 1 || escolha > ambiente.getRobosAtivos().size()) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    Robo escolhido = ambiente.getRobosAtivos().get(escolha - 1);

                    // Define a distância a ser andada nas coordenadas X e Y
                    System.out.print("Delta X: ");
                    int dx = scanner.nextInt();
                    System.out.print("Delta Y: ");
                    int dy = scanner.nextInt();

                    // Condicionais para verificar qual tipo de robô é o escolhido
                    if (escolhido instanceof RoboTerrestre) {
                        // Caso seja do tipo terrestre, escolher sua nova velocidade também
                        System.out.print("Velocidade: ");
                        int vel = scanner.nextInt();
                        ((RoboTerrestre) escolhido).mover(dx, dy, ambiente, vel);
                    } else { // instanceof RoboAereo
                        // Caso seja do tipo aéreo, escolher a distância em Z também
                        System.out.print("Delta Z: ");
                        int dz = scanner.nextInt();
                        escolhido.mover(dx, dy, ambiente);
                        if (dz < 0){
                            ((RoboAereo)escolhido).descer(dz*(-1));
                        }
                        else{
                            ((RoboAereo)escolhido).subir(dz, ambiente);
                        }
                    }
                    break;

                case 3:
                    // Usa os sensores dos robôs
                    System.out.println("Ativando sensores de todos os robôs:\n");
                    for (Robo r : ambiente.getRobosAtivos()) {
                        System.out.printf("Sensores de %s:", r.getNome());
                        r.ativarSensores(ambiente);
                    }
                    break;

                case 4:
                    // Verifica se há colisões dos robôs com os obstáculos no ambiente
                    System.out.println("\nVerificando colisões...");
                    ambiente.detectarColisoes();
                    break;

                case 5:
                    // Escolhe o robô para usar sua habilidade específica
                    System.out.println("\nEscolha o robô para ativar seu metodo especifico:");
                    for (int i = 0; i < ambiente.getRobosAtivos().size(); i++) {
                        Robo r = ambiente.getRobosAtivos().get(i);
                        System.out.printf("%d. %s", (i+1), r.getNome());
                    }

                    int e = scanner.nextInt();
                    scanner.nextLine();

                    // Mesma condicional presente no caso 2
                    if (e < 1 || e > ambiente.getRobosAtivos().size()) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    Robo es = ambiente.getRobosAtivos().get(e - 1);

                    // Uso da habilidade do robô terrestre destruidor (Descrição no seu arquivo)
                    if (es instanceof RoboTerrestreDestruidor){
                        System.out.println("Metodos especificos:\n 1 - DestruirObstaculo (destroi um obstaculo que está ao redor do robo) ");
                        System.out.print("Escolha o numero do metodo: ");
                        int num = scanner.nextInt();
                        if (num == 1){
                            ((RoboTerrestreDestruidor)es).destruirObstaculo(ambiente);
                        }
                    }

                    // Uso da habilidade do robô terrestre exploração (Descrição no seu arquivo)
                    else if (es instanceof RoboTerrestreExplorador){
                    System.out.println("Metodos especificos:\n 1 - Explorar (robo avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente) ");
                        System.out.print("Escolha o numero do metodo: ");
                        int num = scanner.nextInt();
                        if (num == 1){
                            ((RoboTerrestreExplorador)es).explorar(ambiente);
                        }
                    }

                    // Uso da habilidade do robô aéreo falcão (Descrição no seu arquivo)
                    else if (es instanceof RoboAereoFalcao){
                        System.out.println("Metodos especificos:\n 1 - Visao (Varre o ambiente na direção atual até um certo alcance visual, detectando obstáculos à frente) ");
                        System.out.print("Escolha o numero do metodo: ");
                        int num = scanner.nextInt();
                        if (num == 1){
                            ((RoboAereoFalcao)es).visao(ambiente);
                        }
                    }

                    // Uso da habilidade do robô aéreo observador (Descrição no seu arquivo)
                    else if (es instanceof RoboAereoObservador){
                        System.out.println("Metodos especificos:\n 1 - Observar (Varre uma area de raio determinado, em busca de obstaculos) ");
                        System.out.print("Escolha o numero do metodo: ");
                        int num = scanner.nextInt();
                        if (num == 1){
                            ((RoboAereoObservador)es).observar(ambiente);
                        }
                    }
                    
                    break;
                
                case 0:
                    // Finaliza o programa com a variável booleana sendo falsa
                    executando = false;
                    System.out.println("Encerrando o simulador...");
                    break;

                default:
                    // Caso seja uma opção inválida
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}