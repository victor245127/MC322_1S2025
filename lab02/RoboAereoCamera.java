package lab02;

public class RoboAereoCamera extends RoboAereo {
    private int posX;
    private int posY;

    public RoboAereoCamera(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int posX, int posY){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.posX = posX;
        this.posY = posY;
    }

    public void Foto(int obstaculos[][]){
        int i = 0; 
        do {
            if (identificarObstaculo(obstaculos, posX, posY)){
                System.out.printf("Foto tirada de obstaculo em (%d, %d).", posX, posY);
            }
        } while (i < obstaculos.length); 
    }
}
