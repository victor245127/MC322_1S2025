package lab03.src.Ambiente;

import java.util.ArrayList;

import lab03.src.RoboBase.Robo;
import lab03.src.RoboBase.RoboAereo;
import lab03.src.RoboBase.RoboTerrestre;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorColisao;
import lab03.src.Sensor.SensorIdentificacao;

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
    
    public void setRoboTerrestre(RoboTerrestre r, String nome, int posX, int posY, int posZ, int velMax, int velAtual, Sensor sensor, SensorColisao sensor_colisao){
        r.setNome(nome);
        r.setX(posX);
        r.setY(posY);
        r.setZ(posZ);
        r.setSensores(sensor, sensor_colisao);
        r.setVelocidade(velAtual);
        r.setVelocidadeMax(velMax);
        this.RobosAtivos.add(r);
    } // metodo para adicionar robos ao ambiente

    public void setRoboAereo(RoboAereo r, String nome, int posX, int posY, int posZ, int altitudeMax, Sensor sensor, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident){
        r.setNome(nome);
        r.setX(posX);
        r.setY(posY);
        r.setZ(posZ);
        r.setSensores(sensor, sensor_ident, sensor_colisao);
        r.setAltitudeMax(altitudeMax);
        this.RobosAtivos.add(r);
    } // metodo para adicionar robos ao ambiente

    public void removeRobo(Robo r){
        this.RobosAtivos.remove(r);
    } 

    public void setObstaculo(Obstaculos obstaculo, int x1, int x2, int y1, int y2, TiposObstaculo tipo){
        obstaculo.setX1(x1);
        obstaculo.setX2(x2);
        obstaculo.setY1(y1);
        obstaculo.setY2(y2);
        obstaculo.setTipoObstaculo(tipo);
        this.obstaculos.add(obstaculo);
    }

    public void removeObstaculo(Obstaculos obstaculo){
        this.obstaculos.remove(obstaculo);
    }
}
