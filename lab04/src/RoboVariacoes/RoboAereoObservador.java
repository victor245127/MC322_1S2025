package RoboVariacoes;

import Ambiente.Ambiente;
import RobosBase.RoboAereo;

// subclasse de robo aereo que observa o ambiente e identifica os obstaculos em um dado raio
public class RoboAereoObservador extends RoboAereo {
    private int raioObservacao;
    // Atributo do robô

    public RoboAereoObservador(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, int raioObservacao) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.raioObservacao = raioObservacao;
    } // Construtor

    public void observar(Ambiente ambiente) { // Habilidade do observador que procura obstáculos dentro do seu raio de visão
        int x = getPosX();
        int y = getPosY();
        boolean encontrou = false;

        for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
            for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                if (ambiente.dentroDosLimites(i, j) && ambiente.temObstaculoEm(i, j)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", i, j);
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na área.\n");
        }
    }
}