package lab02.src.RoboVariacoes;

import lab02.src.AmbienteMain.Ambiente;
import lab02.src.RoboBase.RoboAereo;

public class RoboAereoDistorcao extends RoboAereo {
    private int deltaAltura;
    private int deltaLargura;
    // atributos de robo aereo de distorcao

    public RoboAereoDistorcao(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int deltaAltura, int deltaLargura){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.deltaAltura = deltaAltura;
        this.deltaLargura = deltaLargura;
    } // construtor desse robo

    // metodo em que o robo muda as dimensoes do ambiente, distorcendo-o
    public void Distorcer(Ambiente ambiente){
        ambiente.setAltura(ambiente.getAltura()+this.deltaAltura);
        ambiente.setLargura(ambiente.getLargura()+this.deltaLargura);
        System.out.printf("Ambiente distorcido para %d m x %d m %d m\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
    }
}
