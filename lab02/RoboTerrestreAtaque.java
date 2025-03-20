package lab02;

public class RoboTerrestreAtaque extends RoboTerrestre {
    private int ataqueX;
    private int ataqueY;

    public RoboTerrestreAtaque(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int ataqueX, int ataqueY){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.ataqueX = ataqueX;
        this.ataqueY = ataqueY;
    }

    public void Ataque(int ataqueX, int ataqueY){

    } // mesclar com ambiente, verificar se ha obstaculo e caso haja, destrua ele
}
