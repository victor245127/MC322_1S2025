package RobosBase;

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import Missao.Missao;
import SubsistemasRobo.ControleMovimento;
import SubsistemasRobo.GerenciadorSensores;
import SubsistemasRobo.ModuloComunicacao;

// Classe abstrata de robô que possui missões pré-definidas, assim como módulos em sua estrutura
public abstract class AgenteInteligente extends Robo {
    protected Missao missao;
    protected ControleMovimento controle_mov;
    protected GerenciadorSensores gerenciadorSensor;
    protected ModuloComunicacao modulo_comm;
    // Atributos

    public AgenteInteligente(String id, int x, int y, int z){
        super(id, x, y, z);
        this.missao = null;
        this.gerenciadorSensor = new GerenciadorSensores();
        this.controle_mov = new ControleMovimento();
        this.modulo_comm = new ModuloComunicacao();
    } // Construtor

    // Define uma missão
    public void definir_missao(Missao m){
        this.missao = m;
    }

    public boolean tem_missao(){ // Verifica se há missão
        return missao != null;
    }

    public abstract void executarMissao(Ambiente ambiente, CentralComunicacao central) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException;
}