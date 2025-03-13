package lab01;

//classe Robo
public class Robo {
    //atributos da classe robo
    private String nome;
    private int posicaoX;
    private int posicaoY;
    
    //construtor do robo
    public Robo(String name, int valorX, int valorY){
        this.posicaoX = valorX;
        this.posicaoY = valorY;
        this.nome = name;
    }

    //metodo para mover o robo de posicao
    public void mover(int deltaX, int deltaY){
        posicaoX = deltaX;
        posicaoY = deltaY;
    }

    //metodo para exibir o nome do robo e sua posicao
    public void exibirPosicao(){
        System.out.printf("O robo %s esta na posicao (%d, %d).", nome, posicaoX, posicaoY);
    }

    //metodo que retorna o nome do robo
    public String exibirNome(){
        return nome;
    }
}
