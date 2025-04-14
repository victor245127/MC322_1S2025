package lab03.src.Ambiente;

import lab03.src.RoboBase.Robo;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int altura;
    private Robo RobosAtivos[];
    private Obstaculos obstaculos[];
    //atributos do ambiente
    
    //construtor do ambiente
    public Ambiente(int a, int l, Robo RobosAtivos[], Obstaculos obstaculos[]){
        this.altura = a;
        this.largura = l;
        this.RobosAtivos = RobosAtivos;
        this.obstaculos = obstaculos;
    }

    public void setLargura(int l){
        this.largura = l;
    } //muda a largura

    public void setAltura(int a){
        this.altura = a;
    } // muda a altura

    //metodos para mostrar as dimensoes do ambiente
    public int getLargura(){
        return largura;
    } 

    public int getAltura(){
        return altura;
    } 

    public Obstaculos[] getObstaculos(){
        return this.obstaculos;
    }

    //metodo que verifica se a nova posicao do robo esta dentro dos limites das 3 dimensoes
    public boolean dentroDosLimites(int X, int Y, int Z){
        if ((0 <= X && X <= this.largura) && (0 <= Y && Y <= this.largura) && (0 <= Z && Z <= this.altura)){
            return true;
        }
        else {  
            return false;
        }
    } 

    public void detectarColisoes(){

    }

    public void setRobo(Robo r){
        // fazer um loop para verificar que posicao esta vazia no vetor e ver se o robo esta no vetor
        this.RobosAtivos[] = r;
    } // metodo para adicionar robos ao ambiente

    public void removeRobo(Robo r){
        
    } // remover robo, realocar o resto e verificar se ele ta na lista ou nao
}
