package lab03.src.Ambiente;

import java.util.ArrayList;

import lab03.src.RoboBase.Robo;

//PERGUNTAR SE MONITORAR E DETECTAR COLISAO PODE OU NAO TER PARAMETRO

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int altura;
    private ArrayList<Robo> RobosAtivos;
    private ArrayList<Obstaculos> obstaculos;
    //atributos do ambiente
    
    //construtor do ambiente
    public Ambiente(int a, int l, ArrayList<Robo> RobosAtivos, ArrayList<Obstaculos> obstaculos){
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

    public ArrayList<Obstaculos> getObstaculos(){
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
    
    public void setRobo(Robo r){
        this.RobosAtivos.add(r);
    } // metodo para adicionar robos ao ambiente

    public void removeRobo(Robo r){
        this.RobosAtivos.remove(r);
    } 

    public void setObstaculo(Obstaculos obstaculo){
        this.obstaculos.add(obstaculo);
    }

    public void removeObstaculo(Obstaculos obstaculo){
        this.obstaculos.remove(obstaculo);
    }
}
