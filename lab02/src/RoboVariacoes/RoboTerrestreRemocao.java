package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboTerrestre;
import lab02.src.AmbienteMain.Ambiente;

// subclasse de robo terrestre que remove um possivel obstaculo em uma certa posicao
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
    public void removeObstaculo(Ambiente ambiente, int obstaculos[][], int velNova){
        int i = 0;
        mover(this.removeX, this.removeY, ambiente, velNova);
        // vai para a posicao dado nos atributos
        do {
            if (identificarObstaculo(obstaculos, removeX, removeY)){
                obstaculos[i][0] = -1;
                obstaculos[i][1] = -1;
                System.out.println("Obstaculo removido.\n");
                return;
                // muda as posicoes do obstaculo para -1, pois assim nao serao mais acessiveis
            }
            i++;
        } while (i < obstaculos.length);
        // loop do while para verificar se ha obstaculos a sua volta e, caso haja, o(s) remove
        System.out.println("Obstaculo nao encontrado.\n");
        // caso loop finalize, significa que nao ha obstaculos em sua volta
    }
}
