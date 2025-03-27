package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.Robo;
import lab02.src.RoboBase.RoboAereo;

public class RoboAereoGPS extends RoboAereo{
    private int pontoX;
    private int pontoY;

    public RoboAereoGPS(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int pontoX, int pontoY){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.pontoX = pontoX;
        this.pontoY = pontoY;
    }

    public int getDeltaX(){
        return (this.pontoX - getX());
    }

    public int getDeltaY(){
        return (this.pontoY - getY());
    }

    public void distancia(Robo r){
        System.out.printf("Para o robo %s chegar na posicao desejada, precisa andar %d em X e %d em Y.", r.getNome(), getDeltaX(), getDeltaY());
    }// identificar distancia 
}