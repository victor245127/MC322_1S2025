package lab01;

//classe Ambiente
public class Ambiente 
{
    //atributos do ambiente
    private int largura;
    private int altura;
    
    //construtor do ambiente
    public Ambiente(int a, int l){
        this.altura = a;
        this.largura = l;
    }

    //metodo para mostrar as dimensoes do ambiente
    public void getDimensoes(){
        System.out.printf("O ambiente tem dimensao %d x %d", altura, largura);
    }

    //metodo que verifica se a nova posicao do robo esta dentro dos limites
    public boolean dentroDosLimites(int x, int y){
        if ((0 <= x && x <= largura) && (0<= y && y <= altura)){
            return true;
        }
        else {
            return false;
        }
    }
}
