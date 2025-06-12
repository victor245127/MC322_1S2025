package RobosBase;

import Comunicacao.CentralComunicacao;
import Comunicacao.Comunicavel;
import Exceptions.RoboDesligadoException;

// subclasse de robos que alteram sua altitude
public abstract class RoboAereo extends AgenteInteligente implements Comunicavel {
    private int altitudeMaxima;
    // Atributos do robô aereo

    public RoboAereo(String id, int posicaoX, int posicaoY, int posicaoZ, int altitudeMaxima){
        super(id, posicaoX, posicaoY, posicaoZ);
        this.altitudeMaxima = altitudeMaxima;
    } // Construtor do robô

    // Método override de robô
    public void moverPara(int x, int y, int z) throws RoboDesligadoException {
        // Caso o controle de movimento esteja desligado, o liga
        if (!controle_mov.getControle()){
            controle_mov.ligar();
        }
        if (z > altitudeMaxima){
            System.out.println("Altitude nao permitida!");
            this.x = x;
            this.y = y;
            // Não altera o z nesse caso
        }        
        else {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        // Após se movimentar, desliga o controle
        controle_mov.desligar();
        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }
    }

    // Utiliza seu módulo de comunicação para enviar mensagem a um destinatário
    public void enviarMensagem(Comunicavel destinatario, Comunicavel remetente, String mensagem, CentralComunicacao central){
        try {
            if (((Robo) destinatario).getEstado() == EstadoRobo.desligado || ((Robo) remetente).getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            }
            modulo_comm.enviarMensagem(destinatario, remetente, mensagem, central);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // Recebe a mensagem
    public void receberMensagem(Comunicavel destinatario, Comunicavel remetente, String mensagem, CentralComunicacao central){
        modulo_comm.receberMensagem(destinatario, remetente, mensagem, central);
    }
}