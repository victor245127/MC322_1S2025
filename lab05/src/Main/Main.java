import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
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
import Exceptions.ErroDestinatarioException;
import Exceptions.EscolhaInvalidaException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import InterfacesRobos.Autonomo;
import Missao.MissaoExplorar;
import Missao.MissaoLigarRobos;
import RobosBase.AgenteInteligente;
import RobosBase.EstadoRobo;
import RobosBase.Robo;
import RoboVariacoes.RoboAereoFalcao;
import RoboVariacoes.RoboAereoObservador;
import RoboVariacoes.RoboTerrestreDestruidor;
import RoboVariacoes.RoboTerrestreExplorador;

public class Main {

private static final String LOGFILE = "log_missoes.txt";                       
private static final PrintStream ORIGINAL_OUT = System.out;                    
private static FileOutputStream LOG_FOS;                                       

// Duplica a saída: console + arquivo 
private static class Tee extends OutputStream {
    private final OutputStream a, b;
    Tee(OutputStream a, OutputStream b) { this.a = a; this.b = b; }
    public void write(int i) throws IOException { a.write(i); b.write(i); }
    public void flush() throws IOException { a.flush(); b.flush(); }
}

// Começa a missão: redireciona System.out 
private static void iniciarLog(String robo, String missao) throws IOException {
    if (LOG_FOS == null) LOG_FOS = new FileOutputStream(LOGFILE, true);        // append
    System.setOut(new PrintStream(new Tee(ORIGINAL_OUT, LOG_FOS), true));      // duplica
    System.out.println(" INICIO | " + robo + " | " + missao);
}

// Termina a missão: volta ao console normal 
private static void encerrarLog(String robo, String missao) throws IOException {
    System.out.println(" FIM | " + robo + " | " + missao);
    System.out.flush();                                                        // garante gravação
    System.setOut(ORIGINAL_OUT);                                               // restaura console
}

    public static void main(String[] args) throws EscolhaInvalidaException, EntidadeImovelException, RoboDesligadoException, ColisaoException, ForaDosLimitesException, ErroComunicacaoException, ErroDestinatarioException, IOException {

        Scanner scanner = new Scanner(System.in);
        // Variável usada para leitura de dados

        // Criação do ambiente, inicializa seu mapa e cria a central de comunicação, com raio de comunicação 5
        Ambiente ambiente = new Ambiente(20, 20, 20);
        ambiente.inicializarMapa();
        CentralComunicacao central = new CentralComunicacao(5);

        // Criação de robôs
        RoboAereoFalcao        drone      = new RoboAereoFalcao("A1", 5, 5, 3, 10, 2);
        // Criação do robô aereo falcâo, direcionável verticalmente

        RoboTerrestreDestruidor tanque     = new RoboTerrestreDestruidor("T1", 2, 2, 0, 5, 7);
        tanque.add_sensores(2);
        // Criação do robo terrestre destruidor, e a adição de seus sensores

        RoboAereoObservador    vigia      = new RoboAereoObservador("A2", 7, 7, 10, 15, 3);
        // Criação do robô aéreo observador, autônomo

        RoboTerrestreExplorador explorador = new RoboTerrestreExplorador("T2", 12, 4, 0, 10);
        // Criação do robô terrestre explorador, direcionável horizontalmente

        // Adiciona robôs ao ambiente
        ambiente.adicionarEntidade(drone);
        ambiente.adicionarEntidade(tanque);
        ambiente.adicionarEntidade(vigia);
        ambiente.adicionarEntidade(explorador);

        // Criação e adição de obstáculos
        ambiente.adicionarEntidade(new Obstaculos(3, 2, 3, 2, TiposObstaculo.PAREDE));
        ambiente.adicionarEntidade(new Obstaculos(18, 6, 18, 6, TiposObstaculo.PREDIO));

        // Variável boleana usada para continuar ou não a interação
        boolean executando = true;

        // Menu interativo com ações diferentes a serem escolhidas, para escolher basta digitar
        // o número da ação
        while (executando) {
            System.out.println("\n--- MENU INTERATIVO ---");
            System.out.println("1. Visualizar status dos robos e obstaculos");
            System.out.println("2. Visualizar ambiente no plano XY");
            System.out.println("3. Ligar/desligar robo");
            System.out.println("4. Mover entidade");
            System.out.println("5. Acionar sensores");
            System.out.println("6. Verificar colisoes");
            System.out.println("7. Ativar habilidades");
            System.out.println("8. Executar missao");
            System.out.println("9. Enviar mensagem");
            System.out.println("10. Exibir mensagens");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            // Escolhe a ação a ser feita
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Mostra as dimensões do ambiente e o status dos robôs e dos obstáculos
                    System.out.printf("\nDimensoes do ambiente:\nLargura: %d\nProfundidade: %d\nAltura: %d\n", ambiente.getLargura(), ambiente.getProfundidade(), ambiente.getAltura());

                    System.out.println("\nObstaculos no ambiente:");
                    // Loop para mostrar os obstáculos
                    for (Entidade obs : (ambiente.getObstaculos())) {
                        System.out.printf("Tipo: %s\n", ((Obstaculos) obs).getTipoObstaculo());
                        System.out.printf("Posicao: (%d, %d, %d), ate (%d, %d, %d)\n", ((Obstaculos) obs).getX()[0], ((Obstaculos) obs).getY()[0], 0, ((Obstaculos) obs).getX()[1], ((Obstaculos) obs).getY()[1], ((Obstaculos) obs).getTipoObstaculo().getAltura());
                        System.out.printf("Resistencia: %d\n",((Obstaculos) obs).getResistencia());
                    }
                    // Descreve os obstáculos em um geral
                    System.out.println(((Obstaculos)ambiente.getObstaculos().get(0)).getDescricao());

                    System.out.println("\nRobos no ambiente:");
                    int index = 1;
                    for (Entidade r : ambiente.getRobos()) {
                        // Loop para mostrar os robôs
                        System.out.printf("%d. %s - %s\n", index, ((Robo) r).getId(), ((Robo)r).getEstado());
                        ((Robo) r).exibirPosicao();
                        System.out.println(r.getDescricao());
                        index++;
                    }
                    break;

                case 2:
                    // Mostra o ambiente em um plano XY
                    ambiente.visualizarAmbiente();
                    break;

                case 3:
                    // Caso o robô esteja ligado, ele é desligado, e vice versa
                    // Única ação de robôs com a interface Autônomo que pode ser controlada
                    try {
                        ambiente.exibirRobos();
                        System.out.println("Escolha o robo a ser ligado/desligado: ");
                        int esc = scanner.nextInt();

                        // Caso a escolha seja inválida
                        if (esc < 1 || esc > ambiente.getRobos().size()){
                            throw new EscolhaInvalidaException();
                        }

                        // Condicionais para ligar/desligar o robô
                        if (((Robo) ambiente.getRobos().get(esc-1)).getEstado() == EstadoRobo.desligado){
                            ((Robo)ambiente.getEntidades().get(esc-1)).ligar();
                        }
                        else {
                            ((Robo)ambiente.getEntidades().get(esc-1)).desligar();
                        }
                        System.out.printf("Robo %s %s\n", ((Robo )ambiente.getRobos().get(esc-1)).getId(), ((Robo) ambiente.getRobos().get(esc-1)).getEstado());
                    } catch (EscolhaInvalidaException ei){
                        System.out.println("ERRO: " + ei.getMessage());
                    }
                    
                    break;

                    case 4: 
                    // Move uma entidade e escolhe qual mover
                    try {
                        System.out.println("\nEscolha a entidade:");
                        for (int a = 0; a < ambiente.getEntidades().size(); a++) {
                            Entidade e = ambiente.getEntidades().get(a);
                            if (e.getTipo() == TipoEntidade.ROBO){
                                System.out.printf("%d. %s\n", (a+1), ((Robo) e).getId());
                            }
                            else {
                                System.out.printf("%d. %c\n", (a+1), e.getTipo().getRepresentacao(e.getTipo()));
                            }
                        }

                        int escolha = scanner.nextInt();
                        scanner.nextLine();

                        // Caso o número do robô seja menor que 1 ou maior que a quantidade no ambiente,
                        // vira uma opção inválida
                        if (escolha < 1 || escolha > ambiente.getEntidades().size()) {
                            throw new EscolhaInvalidaException();
                        }

                        // Se a entidade implementa a interface Autônomo, não é possível controlar sua ação
                        Entidade escolhido = ambiente.getEntidades().get(escolha - 1);
                        if (escolhido instanceof Autonomo){
                            System.out.println("Ativando autonomia...");
                            ((Autonomo) escolhido).Autonomia(ambiente);
                            break;
                        }

                        // Define possível nova velocidade e o ponto no qual a entidade se destinará
                        System.out.print("Novo X: ");
                        int x = scanner.nextInt();
                        System.out.print("Novo Y: ");
                        int y = scanner.nextInt();
                        System.out.print("Novo Z: ");
                        int z = scanner.nextInt();
                        // Nova velocidade também é usada para caso a entidade seja um robô terrestre
                        System.out.print("Nova velocidade: ");
                        int vel = scanner.nextInt();

                        ambiente.moverEntidade(escolhido, x, y, z, vel);
                    } catch (EscolhaInvalidaException ei){
                        System.out.println("ERRO: " + ei.getMessage());
                    }
                    break;

                case 5:
                    // Usa os sensores dos robôs que implementam sensoreavel
                    System.out.println("Ativando sensores de todos os robos...");
                    ambiente.executarSensores(ambiente);
                    break;

                case 6:
                    // Verifica se há colisões dos robôs com os obstáculos no ambiente
                    System.out.println("Verificando colisoes...");
                    ambiente.verificaColisoes();
                    break;

                case 7: 
                    // Escolhe o robô para usar sua habilidade específica
                    try {
                        System.out.println("Escolha o robo para ativar seu metodo especifico:");
                        ambiente.exibirRobos();

                        int e = scanner.nextInt();
                        scanner.nextLine();

                        // Mesma condicional presente no caso 2
                        if (e < 1 || e > ambiente.getRobos().size()) {
                            throw new EscolhaInvalidaException();
                        }

                        e -=1;
                        // Condicionais para qual robô escolheu
                        if (ambiente.getRobos().get(e) instanceof RoboAereoFalcao){
                            ((RoboAereoFalcao)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                        }
                        else if (ambiente.getRobos().get(e) instanceof RoboAereoObservador){
                            ((Autonomo) ambiente.getRobos().get(e)).Autonomia(ambiente);
                            // Para o robô autônomo, ele escolhe a ação a ser feita, podendo se autoligar/desligar,
                            // mover para uma posição aleatória, ou executar sua tarefa
                        }
                        else if (ambiente.getRobos().get(e) instanceof RoboTerrestreDestruidor){
                            ((RoboTerrestreDestruidor)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                        }
                        else if (ambiente.getRobos().get(e) instanceof RoboTerrestreExplorador){
                            ((RoboTerrestreExplorador)ambiente.getRobos().get(e)).executarTarefa(ambiente);
                        }
                    } catch (EscolhaInvalidaException ei){
                        System.out.println("ERRO: " + ei.getMessage());
                    }
                    break;

                case 8:
                    try {
                        System.out.println("Escolha o robo para executar a missao:");
                        ambiente.exibirRobos();
                        int esc_robo = scanner.nextInt();
                        scanner.nextLine();
                        if (esc_robo < 1 || esc_robo > ambiente.getRobos().size()) throw new EscolhaInvalidaException();
                        esc_robo -= 1;

                        System.out.println("Escolha a missao: \n1. Explorar\n2. Ligar robos");
                        int esc_missao = scanner.nextInt();
                        scanner.nextLine();
                        if (esc_missao > 2 || esc_missao < 1) throw new EscolhaInvalidaException();

                        String nomeMissao = esc_missao == 1 ? "Explorar" : "LigarRobos";
                        Robo roboSelecionado = (Robo) ambiente.getRobos().get(esc_robo);

                        // Abre/duplica o log
                        iniciarLog(roboSelecionado.getId(), nomeMissao);
                        if (esc_missao == 1) ((AgenteInteligente) roboSelecionado).definir_missao(new MissaoExplorar());
                        else ((AgenteInteligente) roboSelecionado).definir_missao(new MissaoLigarRobos());
                        ((AgenteInteligente) roboSelecionado).executarMissao(ambiente, central);
                        encerrarLog(roboSelecionado.getId(), nomeMissao);

                    } catch (EscolhaInvalidaException e) {
                        System.out.println("ERRO: " + e.getMessage());
                    }
                    break;

                    case 9: 
                    // Envia mensagem de um robô para outro
                    try {
                        ambiente.exibirRobos();
                        System.out.println("Escolha o robo remetente: ");
                        int r1 = scanner.nextInt();
                        System.out.println("Escolha o robo destinatario: ");
                        int r2 = scanner.nextInt();
                        // Excessão de destinatário acionada caso o índice seja igual ao do remetente
                        if (r1 == r2){
                            throw new ErroDestinatarioException();
                        }
                        if (r1 < 1 || r2 < 1 || r1 > ambiente.getRobos().size() || r2 > ambiente.getRobos().size()){
                            throw new EscolhaInvalidaException();
                        }
                        System.out.println("Digite a mensagem a ser enviada: ");
                        scanner.nextLine();
                        String msg = scanner.nextLine();
                        // Verifica se ambos os robôs implementam a interface comunicável
                        if (ambiente.getRobos().get(r1-1) instanceof Comunicavel && ambiente.getRobos().get(r2-1) instanceof Comunicavel){
                            ((Comunicavel) ambiente.getRobos().get(r1-1)).enviarMensagem(((Comunicavel) ambiente.getRobos().get(r2-1)), (Comunicavel) ambiente.getRobos().get(r1-1), msg, central);
                        }
                        else {
                            throw new ErroComunicacaoException();
                        }
                    // Condicionais para o caso de achar erro de comunicação ou destinatário
                    } catch (ErroComunicacaoException c){
                        System.out.println("ERRO: " + c.getMessage());
                    } catch (ErroDestinatarioException d){
                        System.out.println("ERRO: " + d.getMessage());
                    } catch (EscolhaInvalidaException ei){
                        System.out.println("ERRO: " + ei.getMessage());
                    }
                    break;

                case 10: // Exibe as mensagens enviadas até o momento
                    central.exibirMensagens();
                    break;
                
                case 0:
                    // Finaliza o programa com a variável booleana sendo falsa
                    executando = false;
                    System.out.println("Encerrando o simulador...");
                    break;

                default:
                    // Caso seja uma opção inválida
                    System.out.println("Opcao invalida...");
                    break;
            }
        }

        scanner.close();
        if (LOG_FOS != null) LOG_FOS.close();
    }
}
