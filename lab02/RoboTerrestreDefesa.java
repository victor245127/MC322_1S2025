package lab02;

public class RoboTerrestreDefesa extends RoboTerrestre {
    private int vidaEscudo;

    public RoboTerrestreDefesa(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual, int vidaEscudo){
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.vidaEscudo = vidaEscudo;
    }

    public int getVidaEscudo(){
        return vidaEscudo;
    }

    public void setEscudo(int novaVida){
        this.vidaEscudo = novaVida;
    }

    public void danoEscudo(int danoAtaque){
        if (danoAtaque >= vidaEscudo){
            this.vidaEscudo = 0;
        }
        else {
            this.vidaEscudo -= danoAtaque;
        }
    }
}
