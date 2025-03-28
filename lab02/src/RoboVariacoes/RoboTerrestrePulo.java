package lab02.src.RoboVariacoes;

import lab02.src.RoboBase.RoboTerrestre;

public class RoboTerrestrePulo extends RoboTerrestre {
    private int novoX;
    private int novoY;
    //atributos do robo terrestre de pulo

    public RoboTerrestrePulo(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int novoX, int novoY){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.novoX = novoX;
        this.novoY = novoY;
    } // construtor desse robo

    // metodo em que o robo identifica se ha um obstaculo proximo a sua nova posicao, 
    //e caso tenha, desvia dele
    public void Pulo(int obstaculo[][], int velNova){
        int i = 0;
        mover(this.novoX, this.novoY);
        do {
            if (identificarObstaculo(obstaculo)){
                this.novoX += 2;
                this.novoY += 2;
                System.out.printf("Obstaculo em (%d, %d) desviado\n", novoX-2, novoY-2);
                mover(this.novoX, this.novoY, velNova);
                return;
            }
            i++;
        } while (i < obstaculo.length);
        System.out.println("Obstaculo nao encontrado\n");
    } 
}
