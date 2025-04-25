package Sensor;

import Ambiente.Ambiente;

// Sensor que identifica um obstáculo dentro do raio
public class SensorProximidade extends Sensor {

    public SensorProximidade(double raio) {
        super(raio);
    } // Construtor

    public void monitorar(Ambiente ambiente, int x, int y, int z) {
        // Monitora o ambiente ao redor de uma posição específica dentro do raio
        boolean encontrou = false;
        int alcance = (int) Math.ceil(raio);

        System.out.printf("Sensor de proximidade: escaneando área em torno de (%d, %d)\n", x, y);

        for (int i = x - alcance; i <= x + alcance; i++) {
            for (int j = y - alcance; j <= y + alcance; j++) {
                if (ambiente.dentroDosLimites(i, j) && ambiente.temObstaculoEm(i, j)) {
                    System.out.printf("Obstáculo detectado em (%d, %d)\n", i, j);
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo nas proximidades.");
        }
    }
}