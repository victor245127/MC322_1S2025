package Missao;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RobosBase.Robo;

public interface Missao {
    void executar(Robo r, Ambiente ambiente) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException;
}
