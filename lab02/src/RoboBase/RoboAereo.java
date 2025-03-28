package lab02.src.RoboBase;

public class RoboAereo extends Robo {
    protected int altitude;
    protected int altitudeMax;
    //atributos padrao dos robos aereos

    public RoboAereo (String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax){
        super(nome, direcao, posicaoX, posicaoY);
        this.altitude = altitude;
        this.altitudeMax = altitudeMax;
    } // construtor do robo aereo

    public int getAltititude(){
        return this.altitude;
    } // retorna altitude atual

    // metodos para mudar altitudes atual e maxima
    public void setAltitude(int alt){
        this.altitude = alt;
    }

    public void setAltitudeMax(int altM){
        this.altitudeMax = altM;
    }

    //metodos para aumentar ou diminuir a altitude do robo, e checam se eh possivel altera-la
    public void subir(int metros){
        if ((altitude + metros) <= altitudeMax){
            setAltitude(this.altitude+metros);
            System.out.printf("Altitude nova de %d m.\n", getAltititude());
        }
        else {
            System.out.println("Altitude nao permitida.\n");
        }
    } 
    
    public void descer(int metros){
        if ((altitude - metros) >= 0){
            setAltitude(this.altitude-metros);
            System.out.printf("Altitude nova de %d m.\n", altitude);
        }
        else {
            System.out.println("Altitude nao permitida.\n");
        }
    } 
}