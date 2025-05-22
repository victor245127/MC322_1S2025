package InterfacesRobos;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;

// Interface de autonomia do robô, executa uma tarefa aleatória do robô
public interface Autonomo {
    public void Autonomia(Ambiente ambiente) throws RoboDesligadoException, ColisaoException;
}
