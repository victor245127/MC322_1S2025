package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboTerrestre;

public class RoboTerrestrePulo extends RoboTerrestre {
    private int novoX;
    private int novoY;

    public RoboTerrestrePulo(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int novoX, int novoY){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.novoX = novoX;
        this.novoY = novoY;
    }

    public void Desvio(int obstaculo[][]){
        int i = 0;
        do {
            if (identificarObstaculo(obstaculo)){
                this.novoX += 1;
                this.novoY += 1;
                System.out.printf("Obstaculo na posicao (%d, %d) desviado com sucesso", novoX, novoY);
                return;
            }
            i++;
        } while (i < obstaculo.length);
        System.out.println("Obstaculo nao encontrado no caminho");
    }
}
