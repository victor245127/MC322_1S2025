package lab02;

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
            if (identificarObstaculo(obstaculos, removeX, removeY)){
                obstaculos[i][0] = -1;
                obstaculos[i][1] = -1;
                System.out.printf("Obstaculo na posicao (%d, %d) removido com sucesso", removeX, removeY);
                return;
            }
            i++;
        } while (i < obstaculos.length);
        System.out.printf("Obstaculo nao encontrado na posicao (%d, %d).", removeX, removeY);
    }
}
