package lab01;

public class Robo {
    //perguntar se deixo elas privadas ou publicas
    public String nome;
    public int posicaoX;
    public int posicaoY;
    
    public Robo(String name, int deltaX, int deltaY){
        //perguntar se devo usar deltaX e deltaY
        this.posicaoX = deltaX;
        this.posicaoY = deltaY;
        this.nome = name;
    }

    public void mover(int novoX, int novoY){
        posicaoX = novoX;
        posicaoY = novoY;
    }
    public int exibirPosicao(){
        return posicaoX & posicaoY;
    }
    public String exibirNome(){
        return nome;
    }
}
