package RobosBase;

import Ambiente.Ambiente;
import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;

// Superclasse robô, a qual os demais robôs herdam
public abstract class Robo implements Entidade {
    protected String id;
    protected EstadoRobo estado;
    protected TipoEntidade tipoEntidade;
    protected int x;
    protected int y;
    protected int z;
    // Atributos gerais dos robôs

    public Robo(String id, int x, int y, int z){ // Construtor geral dos robôs
        this.id = id;
        this.estado = EstadoRobo.desligado;
        this.tipoEntidade = TipoEntidade.ROBO;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void moverPara(int x, int y, int z) { // Move o robô para uma certa posição
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void exibirPosicao(){ // Exibe a posição do robô
        System.out.printf("Posição: (%d, %d, %d)\n", this.x, this.y, this.z);
    }

    public void ligar(){ // Liga o robô
        this.estado = EstadoRobo.ligado;
    }

    public void desligar(){ // Desliga o robô
        this.estado = EstadoRobo.desligado;
    }

    public abstract void executarTarefa(Ambiente ambiente) throws ColisaoException, RoboDesligadoException; // Método abstrato 
    
    // Métodos get que retornam os atributos do robô
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getZ(){
        return this.z;
    }

    public TipoEntidade getTipo(){
        return this.tipoEntidade;
    }

    public String getId(){
        return this.id;
    }

    public EstadoRobo getEstado(){
        return this.estado;
    }

    public abstract String getDescricao(); // Descreve o robô

    public char getRepresentacao(){ // Retorna a representação do robô por um caractere (R)
        return 'R';
    }
}