package lab03.src.RoboVariacoes;

import lab03.src.RoboBase.RoboAereo;
import lab03.src.Sensor.Sensor;
import lab03.src.Sensor.SensorColisao;
import lab03.src.Sensor.SensorIdentificacao;
import lab03.src.Ambiente.Ambiente;

// subclasse de robo aereo que observa o ambiente e identifica os obstaculos em um dado raio
public class RoboAereoObservador extends RoboAereo {
    private int raioObservacao;
    // atributo do robo

    public RoboAereoObservador(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Sensor sensor, SensorColisao sensor_colisao, SensorIdentificacao sensor_ident, int raioObservacao) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima, sensor, sensor_ident, sensor_colisao);
        this.raioObservacao = raioObservacao;
    } // construtor do robo

    public void setRaio(int raio){
        this.raioObservacao = raio;
    } // altera o raio para um dado valor

    // metodo em que o robo observa o ambiente
    public void observar(Ambiente ambiente, int obstaculos[][]) {
        int x = getX();
        int y = getY();
        boolean encontrou = false;

        for (int i = x - this.raioObservacao; i <= x + this.raioObservacao; i++) {
            for (int j = y - raioObservacao; j <= y + raioObservacao; j++) {
                if (ambiente.dentroDosLimites(i, j)){
                    for (int k = 0; k < obstaculos.length; k++){
                        if (obstaculos[k][0] == i && obstaculos[k][1] == j){
                            System.out.printf("Obstáculo detectado em (%d, %d)\n", i, j);
                            encontrou = true;
                            break;
                            // caso encontre um obstaculo, mostra sua posicao e torna o booleano de
                            // encontro verdadeiro
                        }
                    }
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum obstáculo encontrado na área.\n");
            // caso o booleando permaneca falso ate o fim, significa que nao foram encontrados 
            // obstaculos dentro de seu raio
        }
    }
}