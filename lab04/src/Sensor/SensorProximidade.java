package Sensor;

import Ambiente.Ambiente;
import Exceptions.ColisaoException;

// Sensor que identifica um obstáculo dentro do raio
public class SensorProximidade extends Sensor {

    public SensorProximidade(double raio) {
        super(raio);
    } // Construtor

    public void monitorar(Ambiente ambiente, int x, int y, int z) throws ColisaoException {
        // Monitora o ambiente ao redor de uma posição específica dentro do raio
        boolean encontrou = false;
        int alcance = (int) Math.ceil(raio);

        System.out.printf("Sensor de proximidade: escaneando área em torno de (%d, %d)\n", x, y);

        for (int i = x - alcance; i <= x + alcance; i++) {
            for (int j = y - alcance; j <= y + alcance; j++) {
                for (int k = z - alcance; k <= z+alcance; k++){
                    if (ambiente.dentroDosLimites(i, j, k) && ambiente.temObstaculoEm(i, j, k)) {
                        System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", i, j, k);
                        encontrou = true;
                    }
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo nas proximidades.");
        }
    }
}