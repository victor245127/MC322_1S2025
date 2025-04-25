package RobosBase;

import Ambiente.Ambiente;

// subclasse de robos que alteram sua altitude
public class RoboAereo extends Robo{
    private int altitude;
    private int altitudeMaxima;
    // Atributos do robô aereo

    public RoboAereo(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima){
        super(nome,direcao,posicaoX,posicaoY);
        this.altitude = altitude;
        this.altitudeMaxima = altitudeMaxima;
    } // Construtor do robô

    public void subir(int metros, Ambiente ambiente){ // Método para elevar a altitude do robô
        // Verifica se a nova altitude é menor ou igual à altitude máxima do robô e do ambiente
        if (this.altitude + metros <= this.altitudeMaxima && (this.altitude + metros) <= ambiente.getAltura()){
            this.altitude += metros;
        }
        else{
            System.out.println("Altura acima da permitida!\n");
        }
    }

    public void descer(int metros){ // Método para diminuir a altitude do robô
        if (this.altitude - metros >= 0){
            this.altitude -= metros;
        }
        else{
            System.out.println("Altura abaixo da permitida!\n");
        }
    }

    public void exibirPosicaoAereo(){ // Exibe a posição do robô aereo
        System.out.printf("Posição: X = %d, Y = %d, Z = %d\n", getPosX(), getPosY(), getAltitude());
    }

    public int getAltitude() { // Método get que retorna altitude
        return this.altitude;
    }    
}