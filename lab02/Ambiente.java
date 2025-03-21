package lab02;

public class Ambiente {
    private int largura;
    private int altura;
    private Robo RobosAtivos[];
    private int obstaculos[][];
    
    //construtor do ambiente
    public Ambiente(int a, int l, Robo RobosAtivos[], int obstaculos[][]){
        this.altura = a;
        this.largura = l;
        this.RobosAtivos = RobosAtivos;
        this.obstaculos = obstaculos;
    }

    //metodo para mostrar as dimensoes do ambiente
    public void getDimensoes(){
        System.out.printf("O ambiente tem dimensao %d x %d x %d", largura, largura, altura);
    }

    //metodo que verifica se a nova posicao do robo esta dentro dos limites
    public boolean dentroDosLimites(int largura, int altura){
        if ((0 <= largura && largura <= this.largura) && (0<= altura && altura <= this.altura)){
            return true;
        }
        else {  
            return false;
        }
    }

    public void adicionarObstaculo(int X, int Y){
        this.obstaculos[obstaculos.length][0] = X;
        this.obstaculos[obstaculos.length][1] = Y;
    }

    public void adicionarRobo(Robo r){
        this.RobosAtivos[RobosAtivos.length] = r;
    }
}
