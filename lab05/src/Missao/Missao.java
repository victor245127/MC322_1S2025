package Missao;

import Ambiente.Ambiente;
import Comunicacao.CentralComunicacao;
import Exceptions.ColisaoException;
import Exceptions.ForaDosLimitesException;
import Exceptions.RoboDesligadoException;
import RobosBase.Robo;

public interface Missao {
    void executar(Robo r, Ambiente ambiente, CentralComunicacao central) throws RoboDesligadoException, ColisaoException, ForaDosLimitesException;
}
