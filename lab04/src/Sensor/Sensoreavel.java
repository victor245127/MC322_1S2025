package Sensor;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;
import Exceptions.RoboDesligadoException;

// Interface dos sensores
public interface Sensoreavel {
    public void acionarSensores(Ambiente ambiente) throws ColisaoException, RoboDesligadoException;
}
