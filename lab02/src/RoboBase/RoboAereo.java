package lab02.src.RoboBase;

public class RoboAereo extends Robo {
    protected int altitude;
    protected int altitudeMax;

    public RoboAereo (String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax){
        super(nome, direcao, posicaoX, posicaoY);
        this.altitude = altitude;
        this.altitudeMax = altitudeMax;
    }

    public int getAltititude(){
        return this.altitude;
    }

    public void setAltitude(int alt){
        this.altitude = alt;
    }

    public void subir(int metros){
        if ((altitude + metros) <= altitudeMax){
            setAltitude(this.altitude+metros);
            System.out.printf("Altitude nova de %d.", getAltititude());
        }
        else {
            System.out.println("Altitude nova seria maior que a maxima. Voltando a original.");
        }
    } // usado
    
    public void descer(int metros){
        if ((altitude - metros) >= 0){
            setAltitude(this.altitude-metros);
            System.out.printf("Altitude nova de %d.", altitude);
        }
        else {
            System.out.println("Altitude nova seria menor que 0. Voltando a original.");
        }
    } // usado
}