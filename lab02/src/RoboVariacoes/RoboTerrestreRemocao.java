package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboTerrestre;

public class RoboTerrestreRemocao extends RoboTerrestre {
    private int removeX;
    private int removeY;
    //atributos do robo terrestre de remocao

    public RoboTerrestreRemocao(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int removeX, int removeY){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.removeX = removeX;
        this.removeY = removeY;
    } // construtor do robo 

    // metodo que remove um obstaculo proximo a nova posicao do robo
    public void removeObstaculo(int obstaculos[][], int velNova){
        int i = 0;
        mover(this.removeX, this.removeY, velNova);
        do {
            if (identificarObstaculo(obstaculos, removeX, removeY)){
                obstaculos[i][0] = -1;
                obstaculos[i][1] = -1;
                System.out.println("Obstaculo removido.\n");
                return;
            }
            i++;
        } while (i < obstaculos.length);
        System.out.println("Obstaculo nao encontrado.\n");
    }
}
