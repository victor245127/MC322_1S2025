import java.util.Scanner;

import Ambiente.Ambiente;
import Ambiente.Obstaculos;
import Ambiente.TiposObstaculo;
import Comunicacao.CentralComunicacao;
import Comunicacao.Comunicavel;
import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.EntidadeImovelException;
import Exceptions.ErroComunicacaoException;
import Exceptions.ErroSensorException;
import Exceptions.RoboAutonomoException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Autonomo;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboAereoObservador;
import RoboVariacoes.RoboTerrestreDestruidor;
import RoboVariacoes.RoboTerrestreExplorador;

// Classe main, para rodar o código em conjunto
public class Main {
    public static void main(String[] args) throws EntidadeImovelException, RoboDesligadoException, ColisaoException, ErroSensorException, RoboAutonomoException, ErroComunicacaoException {
        Scanner scanner = new Scanner(System.in);
        // Variável usada para leitura de dados

        // Criação do ambiente
        Ambiente ambiente = new Ambiente(20, 20, 20);
        // Ambiente virtual dos robôs

        // Criação de robôs
        RoboAereoFalcao drone = new RoboAereoFalcao("D1", 5, 5, 3, 10,2);
        // Criação do robô aereo falcâo

        RoboTerrestreDestruidor tanque = new RoboTerrestreDestruidor("T1", 2, 2, 0, 5, 7);
        tanque.add_sensores(2);
        // Criação do robo terrestre destruidor, e a adição de seus sensores

        RoboAereoObservador vigia = new RoboAereoObservador("V1", 7, 7, 10, 15, 3);
        // Criação do robô aéreo observador

        RoboTerrestreExplorador explorador = new RoboTerrestreExplorador("E1", 12, 4, 0, 10);
        // Criação do robô terrestre explorador

        // Adiciona robôs ao ambiente
        ambiente.adicionarEntidade(drone);
        ambiente.adicionarEntidade(tanque);
        ambiente.adicionarEntidade(explorador);
        ambiente.adicionarEntidade(vigia);

        // Criação e adição de obstáculos
        ambiente.adicionarEntidade(new Obstaculos(3, 2, 3, 2, TiposObstaculo.PAREDE));
        ambiente.adicionarEntidade(new Obstaculos(18, 6, 18, 6, TiposObstaculo.PREDIO));

        // Variável boleana usada para continuar ou não a interação
        boolean executando = true;
        int opcao;

        // Inicializa o mapa do ambiente e cria a central de comunicação
        ambiente.inicializarMapa();
        CentralComunicacao central = new CentralComunicacao(5);

        // Menu interativo com ações diferentes a serem escolhidas, para escolher basta digitar
        // o número da ação
        while (executando) {
            System.out.println("\n--- MENU INTERATIVO ---");
            System.out.println("1. Visualizar status dos robôs e obstáculos");
            System.out.println("2. Visualizar ambiente no plano XY");
            System.out.println("3. Ligar/desligar robô");
            System.out.println("4. Mover entidade");
            System.out.println("5. Acionar sensores");
            System.out.println("6. Verificar colisões");
            System.out.println("7. Ativar habilidades");
            System.out.println("8. Enviar mensagem");
            System.out.println("9. Exibir mensagens");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            // Escolhe a ação a ser feita
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Mostra as dimensões do ambiente e o status dos robôs e dos obstáculos
                    System.out.printf("\nDimensoes do ambiente:\nLargura: %d\nProfundidade: %d\nAltura: %d", ambiente.getLargura(), ambiente.getProfundidade(), ambiente.getAltura());

                    System.out.println("Obstaculos no ambiente:");
                    // Loop para mostrar os obstáculos
                    for (Entidade obs : (ambiente.getObstaculos())) {
                        System.out.printf("Tipo: %s\n", ((Obstaculos) obs).getTipoObstaculo());
                        System.out.printf("Posição: (%d, %d, %d), até (%d, %d, %d)\n", ((Obstaculos) obs).getX()[0], ((Obstaculos) obs).getY()[0], 0, ((Obstaculos) obs).getX()[1], ((Obstaculos) obs).getY()[1], ((Obstaculos) obs).getTipoObstaculo().getAltura());
                        System.out.printf("Resistência: %d\n",((Obstaculos) obs).getResistencia());
                    }
                    // Descreve os obstáculos em um geral
                    ((Obstaculos)ambiente.getObstaculos().get(0)).getDescricao();

                    System.out.println("\nRobôs no ambiente:");
                    int index = 1;
                    for (Entidade r : ambiente.getRobos()) {
                        // Loop para mostrar os robôs
                        System.out.printf("%d. %s - %s\n", index, ((Robo) r).getId(), ((Robo)r).getEstado());
                        ((Robo) r).exibirPosicao();
                        r.getDescricao();
                        index++;
                    }
                    break;

                case 2:
                    // Mostra o ambiente em um plano XY
                    ambiente.visualizarAmbiente();
                    break;

                case 3:
                    // Caso o robô esteja ligado, ele é desligado, e vice versa
                    ambiente.exibirRobos();
                    System.out.println("Escolha o robô a ser ligado/desligado: ");
                    int esc = scanner.nextInt();
                    Robo r = ((Robo) ambiente.getRobos().get(esc));
                    if (r.getEstado() == EstadoRobo.desligado){
                        // r.ligar()
                        ((Robo)ambiente.getEntidades().get(esc)).ligar();
                    }
                    else {
                        ((Robo)ambiente.getEntidades().get(esc)).desligar();
                    }
                    break;

                case 4:
                    // Mover uma entidade, escolhe qual mover
                    System.out.println("\nEscolha a entidade:");
                    for (int a = 0; a < ambiente.getEntidades().size(); a++) {
                        Entidade e = ambiente.getEntidades().get(a);
                        if (e.getTipo() == TipoEntidade.ROBO){
                            System.out.printf("%d. %s\n", (a+1), ((Robo) e).getId());
                        }
                        else {
                            System.out.printf("%d. %c\n", (a+1), e.getRepresentacao());
                        }
                    }

                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    // Caso o número do robô seja menor que 1 ou maior que a quantidade no ambiente,
                    // vira uma opção inválida
                    if (escolha < 1 || escolha > ambiente.getEntidades().size()) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    Entidade escolhido = ambiente.getEntidades().get(escolha - 1);
                    if (escolhido instanceof Autonomo){
                        throw new RoboAutonomoException();
                    }

                    // Define possível nova velocidade e o ponto no qual a entidade se destinará
                    System.out.print("Novo X: ");
                    int x = scanner.nextInt();
                    System.out.print("Novo Y: ");
                    int y = scanner.nextInt();
                    System.out.print("Novo Z: ");
                    int z = scanner.nextInt();
                    System.out.print("Nova velocidade: ");
                    int vel = scanner.nextInt();

                    ambiente.moverEntidade(escolhido, x, y, z, vel);
                    break;

                case 5:
                    // Usa os sensores dos robôs
                    System.out.println("Ativando sensores de todos os robôs...");
                    ambiente.executarSensores(ambiente);
                    break;

                case 6:
                    // Verifica se há colisões dos robôs com os obstáculos no ambiente
                    System.out.println("Verificando colisões...");
                    ambiente.verificaColisoes();
                    // FAZER
                    break;

                case 7:
                    // Escolhe o robô para usar sua habilidade específica
                    System.out.println("Escolha o robô para ativar seu metodo especifico:");
                    ambiente.exibirRobos();

                    int e = scanner.nextInt();
                    scanner.nextLine();

                    // Mesma condicional presente no caso 2
                    if (e < 1 || e > ambiente.getRobos().size()) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    if (ambiente.getRobos().get(e) instanceof RoboAereoFalcao){
                        ((RoboAereoFalcao)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                    }
                    else if (ambiente.getRobos().get(e) instanceof RoboAereoObservador){
                        throw new RoboAutonomoException();
                    }
                    else if (ambiente.getRobos().get(e) instanceof RoboTerrestreDestruidor){
                        ((RoboTerrestreDestruidor)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                    }
                    else if (ambiente.getRobos().get(e) instanceof RoboTerrestreExplorador){
                        ((RoboTerrestreExplorador)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                    }
                    
                    break;

                case 8:
                    ambiente.exibirRobos();
                    System.out.println("Escolha o robo remetente: ");
                    int r1 = scanner.nextInt();
                    System.out.println("Escolha o robo destinatario: ");
                    int r2 = scanner.nextInt();
                    System.out.println("Digite a mensagem a ser enviada: ");
                    String msg = scanner.next();
                    if (ambiente.getRobos().get(r1) instanceof Comunicavel && ambiente.getRobos().get(r2) instanceof Comunicavel){
                        ((Comunicavel) ambiente.getRobos().get(r1)).enviarMensagem(((Comunicavel) ambiente.getRobos().get(r2)), msg, central);
                    }
                    else {
                        throw new ErroComunicacaoException();
                    }
                    break;

                case 9:
                    central.exibirMensagens();
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