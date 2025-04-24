package RobosBase;

import Ambiente.Ambiente;
import Sensor.SensorColisao;

// superclasse robo, a qual as demais herdam
public class Robo {
    protected String nome;
    protected String direcao;
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;
    protected SensorColisao sensor_colisao;
    //atributos padrao dos robos

    //construtor padrao de robos
    public Robo (String nome, int posicaoX, int posicaoY, int posicaoZ, SensorColisao sensor_colisao){
        this.nome = nome;
        this.direcao = "";
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
        this.sensor_colisao = sensor_colisao;
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

    public int getZ(){
        return this.posicaoZ;
    }

    public String getDirecao(){
        return this.direcao;
    }
    
    public void setX(int posX){
        this.posicaoX = posX;
    } 

    public void setY(int posY){
        this.posicaoY = posY;
    } 

    public void setZ(int posZ){
        this.posicaoZ = posZ;
    }

    //move o robo para uma posicao desejada caso seja possivel
    public void mover(int posX, int posY, Ambiente ambiente){
        if (ambiente.dentroDosLimites(posX, posY, this.posicaoZ) && !sensor_colisao.detectarColisoes(ambiente, posX, posY, getZ())){
            this.posicaoX = posX;
            this.posicaoY = posY;
            setDirecao(ambiente);
            System.out.printf("Indo para posicao (%d, %d, %d)\n", posicaoX, posicaoY, posicaoZ);
        }
        else {
            System.out.printf("Posicao (%d, %d, %d) nao pode ser alcancada\n", posX, posY, posicaoZ);
        }
    }

    public void setDirecao(Ambiente ambiente){
        if (this.posicaoY >= ambiente.getComprimento()*3/4){
            this.direcao = "NORTE";
        }
        else if (this.posicaoY <= ambiente.getComprimento()*1/4){
            this.direcao = "SUL";
        }
        else {
            if (this.posicaoX >= ambiente.getLargura()/2){
                this.direcao = "LESTE";
            }
            else {
                this.direcao = "OESTE";
            }
        }
    }

    // metodo para identificar se ha um obstaculo a no maximo 1m de distancia do robo
    public void identificarObstaculo(Ambiente ambiente){
        sensor_colisao.monitorar(ambiente, posicaoX, posicaoY, posicaoZ);
    } 

    //metodo para mostrar os atributos do robo
    public void exibirPosicao(){
        System.out.printf("Posicao de %s: (%d, %d) na direcao %s.\n", getNome(), getX(), getY(), getDirecao());
    } 
}
