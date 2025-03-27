package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboTerrestre;

public class RoboTerrestreRemocao extends RoboTerrestre {
    private int removeX;
    private int removeY;

    public RoboTerrestreRemocao(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int removeX, int removeY){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.removeX = removeX;
        this.removeY = removeY;
    }

    public void removeObstaculo(int obstaculos[][]){
        int i = 0;
        do {
            if (identificarObstaculo(obstaculos)){
                obstaculos[i][0] = -1;
                obstaculos[i][1] = -1;
                System.out.println("Obstaculo removido com sucesso!");
                return;
            }
            i++;
        } while (i < obstaculos.length);
        System.out.println("Obstaculo nao encontrado.");
    }
}
