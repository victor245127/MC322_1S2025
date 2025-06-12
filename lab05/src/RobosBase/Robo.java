package RobosBase;

import Ambiente.Ambiente;
import Entidade.Entidade;
import Entidade.TipoEntidade;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;

// Superclasse abstrata robô, a qual os demais robôs herdam, implementa a interface entidade
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
    
    public void moverPara(int x, int y, int z) throws RoboDesligadoException { // Move o robô para uma certa posição
        this.x = x;
        this.y = y;
        this.z = z;
        // Verifica se o robô está desligado
        if (getEstado() == EstadoRobo.desligado){
            throw new RoboDesligadoException();
        }
    }
    
    public void exibirPosicao(){ // Exibe a posição do robô
        System.out.printf("Posicao: (%d, %d, %d)\n", this.x, this.y, this.z);
    }

    public void ligar(){ // Liga o robô
        this.estado = EstadoRobo.ligado;
    }

    public void desligar(){ // Desliga o robô
        this.estado = EstadoRobo.desligado;
    }

    // Método abstrato que é implementado por suas subclasses
    public abstract void executarTarefa(Ambiente ambiente) throws ColisaoException, RoboDesligadoException, ForaDosLimitesException; // Método abstrato 
    
    // Métodos get que retornam os atributos do robô
    public int [] getX(){
        return new int[] {this.x};
    }

    public int [] getY(){
        return new int[] {this.y};
    }

    public int [] getZ(){
        return new int[] {this.z};
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

    public abstract String getDescricao(); // Descreve o robô, método abstrato implementado em suas subclasses
}