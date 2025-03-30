package lab02.src.RoboBase;

public class Robo {
    protected String nome;
    protected String direcao;
    protected int posicaoX;
    protected int posicaoY;
    //atributos padrao dos robos

    //construtor padrao de robos
    public Robo (String nome, String direcao, int posicaoX, int posicaoY){
        this.nome = nome;
        this.direcao = direcao;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    //metodos para retornar nome, posicao X e Y e direcao do robo
    public String getNome(){
        return this.nome;
    }
    
    public int getX(){
        return this.posicaoX;
    } 

    public int getY(){
        return this.posicaoY;
    } 

    public String getDirecao(){
        return this.direcao;
    }

    //move o robo para uma posicao desejada
    public void mover(int posX, int posY){
        this.posicaoX = posX;
        this.posicaoY = posY;
    }

    // metodo para identificar se ha um obstaculo a no maximo 1m de distancia do robo
    public boolean identificarObstaculo(int obstaculos[][], int x, int y){
        int j, k, i = 0;
        do {
            for (j = -1; j < 2; j++){
                for (k = -1; k < 2; k++){
                    if (obstaculos[i][0] == (x + j) && obstaculos[i][1] == (y + k)){
                        return true;
                    }
                }
            }
            i++;
        } while (i < obstaculos.length);
        return false;
    } 

    //metodo para mostrar os atributos do robo
    public void exibirPosicao(){
        System.out.printf("Posicao de %s: (%d, %d) na direcao %s.\n", getNome(), getX(), getY(), getDirecao());
    } 
}
