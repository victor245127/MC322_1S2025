package Ambiente;

import java.util.ArrayList;

import RobosBase.Robo;

// classe do ambiente, que possui suas dimensoes e vetores com os robos e obstaculos presentes 
public class Ambiente {
    private int largura;
    private int comprimento;
    private int altura;
    private ArrayList<Robo> RobosAtivos;
    private ArrayList<Obstaculos> obstaculos;
    //atributos do ambiente
    
    //construtor do ambiente
    public Ambiente(int a, int l, int c, ArrayList<Robo> RobosAtivos, ArrayList<Obstaculos> obstaculos){
        this.altura = a;
        this.largura = l;
        this.comprimento = c;
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
        return this.largura;
    } 

    public int getAltura(){
        return this.altura;
    } 

    public int getComprimento(){
        return this.comprimento;
    }

    public ArrayList<Obstaculos> getObstaculos(){
        return this.obstaculos;
    }

    //metodo que verifica se a nova posicao do robo esta dentro dos limites das 3 dimensoes
    public boolean dentroDosLimites(int X, int Y, int Z){
        if ((0 <= X && X <= this.largura) && (0 <= Y && Y <= this.comprimento) && (0 <= Z && Z <= this.altura)){
            return true;
        }
        else {  
            return false;
        }
    }

    public void detectar_obstaculos(){
        for (int i = 0; i < RobosAtivos.size(); i++){
            
        }
    }

    public void setRobo(Robo r){
        this.RobosAtivos.add(r);
    } // metodo para adicionar robos ao ambiente

    public void setObstaculo(Obstaculos obstaculo){
        this.obstaculos.add(obstaculo);
    }

    public void removeObstaculo(Obstaculos obstaculo){
        this.obstaculos.remove(obstaculo);
    }
}
