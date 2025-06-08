package RobosBase;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import Missao.Missao;

public abstract class AgenteInteligente extends Robo {
    protected Missao missao;

    public AgenteInteligente(String id, int x, int y, int z){
        super(id, x, y, z);
        this.missao = null;
    }

    public void definir_missao(Missao m){
        this.missao = m;
    }

    public boolean tem_missao(){
        return missao != null;
    }

    public abstract void executarMissao(Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException;
}