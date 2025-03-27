package lab02.src.RoboVariacoes;

import lab02.src.AmbienteMain.Ambiente;
import lab02.src.RoboBase.RoboAereo;

public class RoboAereoDistorcao extends RoboAereo {
    private int deltaAltura;
    private int deltaLargura;

    public RoboAereoDistorcao(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMax, int deltaAltura, int deltaLargura){
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMax);
        this.deltaAltura = deltaAltura;
        this.deltaLargura = deltaLargura;
    }

    public void Distorcer(Ambiente ambiente){
        ambiente.setAltura(ambiente.getAltura()+this.deltaAltura);
        ambiente.setAltura(ambiente.getLargura()+this.deltaLargura);
    } // distorce o espaco do ambiente e aumenta/diminui suas dimensoes
}
