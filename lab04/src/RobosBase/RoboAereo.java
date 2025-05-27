package RobosBase;

import Ambiente.Ambiente;
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

    public void subir(int metros, Ambiente ambiente){ // Método para elevar a altitude do robô
        // Verifica se a nova altitude é menor ou igual à altitude máxima do robô e do ambiente
        if (getZ()[0] + metros <= this.altitudeMaxima && (getZ()[0] + metros) <= ambiente.getAltura()){
            moverPara(x, y, z+metros);
        }
        else{
            System.out.println("Altura acima da permitida!\n");
        }
    }

    public void descer(int metros){ // Método para diminuir a altitude do robô
        if (getZ()[0] - metros >= 0){
            moverPara(x, y, z-metros);
        }
        else{
            System.out.println("Altura abaixo da permitida!\n");
        }
    }

    // Método que envia mensagem para um destinatário
    public void enviarMensagem(Comunicavel destinatario, String mensagem, CentralComunicacao central) throws RoboDesligadoException{
        if (((Robo)destinatario).estado == EstadoRobo.desligado || getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }
        central.registrarMensagem(getId(), mensagem);
    }

    public void receberMensagem(String mensagem) throws RoboDesligadoException{
        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }
    }
}