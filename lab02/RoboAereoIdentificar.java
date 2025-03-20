package lab02;

public class RoboAereoIdentificar extends RoboAereo {
    private int posX;
    private int posY;

    public RoboAereoIdentificar(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int posX, int posY){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.posX = posX;
        this.posY = posY;
    }

    public void identificar(int vidaNovaEscudo, int posX, int posY){
        
    }//mesclar com ambiente, dar vida ao escudo do robo em x, y
}
