package RobosBase;

import Comunicacao.CentralComunicacao;
import Comunicacao.Comunicavel;
import Exceptions.RoboDesligadoException;

// subclasse de robos que alteram sua altitude
public abstract class RoboAereo extends Robo implements Comunicavel {
    private int altitudeMaxima;
    // Atributos do robô aereo

    public RoboAereo(String id, int posicaoX, int posicaoY, int posicaoZ, int altitudeMaxima){
        super(id, posicaoX, posicaoY, posicaoZ);
        this.altitudeMaxima = altitudeMaxima;
    } // Construtor do robô

    // Método override de robô
    public void moverPara(int x, int y, int z) throws RoboDesligadoException {
        if (z > altitudeMaxima){
            System.out.println("Altitude não permitida!");
            this.x = x;
            this.y = y;
            // Não altera o z nesse caso
        }        
        else {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }
    }

    // Método que envia mensagem para um destinatário
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) throws RoboDesligadoException{
        try {
            if (((Robo)destinatario).estado == EstadoRobo.desligado || getEstado() == EstadoRobo.desligado){
                throw new RoboDesligadoException();
            } // Erro caso um dos robôs esteja desligado
            receberMensagem(destinatario, mensagem, central);
        } catch (RoboDesligadoException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        
    }

    // Método que determina se o destinatário recebe ou não a mensagem
    public void receberMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central){
        if (((Robo)destinatario).getX()[0] >= (x - central.getRaio()) && ((Robo)destinatario).getX()[0] <= (x + central.getRaio()) && ((Robo)destinatario).getY()[0] >= (y - central.getRaio()) && ((Robo)destinatario).getY()[0] <= (y + central.getRaio())){
            central.registrarMensagem(((Robo) destinatario).getId(), getId(), mensagem);
        } // Caso o destinatário esteja dentro do raio de comunicação, recebe a mensagem e a registra
        else {
            System.out.println("Robô a uma distância maior que o alcance do raio, não foi possível receber a mensagem.");
        }
    }
}