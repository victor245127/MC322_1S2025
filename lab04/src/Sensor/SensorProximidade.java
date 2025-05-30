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

        System.out.printf("Sensor de proximidade: escaneando área em torno de (%d, %d, %d)\n", x, y, z);

        for (int i = x - alcance; i <= x + alcance; i++) {
            for (int j = y - alcance; j <= y + alcance; j++) {
                for (int k = z - alcance; k <= z+alcance; k++){
                    if (i >= 0 && i < ambiente.getLargura() && j >= 0 && j < ambiente.getProfundidade() && k >= 0 && k < ambiente.getAltura() && ambiente.temObstaculoEm(i, j, k)) {
                        System.out.printf("Obstáculo detectado em (%d, %d, %d)\n", i, j, k);
                        j++;
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