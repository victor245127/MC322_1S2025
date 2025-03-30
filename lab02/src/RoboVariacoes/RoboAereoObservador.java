package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboAereo;
import lab02.src.AmbienteMain.Ambiente;

public class RoboAereoObservador extends RoboAereo {
    private int raioObservacao;

    public RoboAereoObservador(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, int raioObservacao) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.raioObservacao = raioObservacao;
    }

    public void observar(Ambiente ambiente, int obstaculos[][]) {
        int x = getX();
        int y = getY();
        boolean encontrou = false;

        for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
            for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                if (ambiente.dentroDosLimites(i, j) && identificarObstaculo(obstaculos, i, j)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)", i, j);
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na área.\n");
        }
    }
}