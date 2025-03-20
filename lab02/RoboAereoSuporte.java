package lab02;

public class RoboAereoSuporte extends RoboAereo{
    private int vidaNovaEscudo;
    private int roboPosX;
    private int roboPosY;

    public RoboAereoSuporte(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int vidaNovaEscudo, int roboPosX, int roboPosY){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.vidaNovaEscudo = vidaNovaEscudo;
    }

    public void darEscudo(int vidaNovaEscudo, int posX, int posY){
        
    }//mesclar com ambiente, dar vida ao escudo do robo em x, y
}