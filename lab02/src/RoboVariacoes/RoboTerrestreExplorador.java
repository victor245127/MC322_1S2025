// Robô terrestre que avança em linha reta até encontrar um obstáculo ou chegar ao fim do ambiente.
// Conta quantos passos conseguiu dar durante a exploração.

package lab02.src.RoboVariacoes;

import lab02.src.AmbienteMain.Ambiente;
import lab02.src.RoboBase.RoboTerrestre;

// subclasse de robo terrestre que explora o ambiente em uma direcao ate chegar ao limite ou ser barrado
// por um obstaculo
public class RoboTerrestreExplorador extends RoboTerrestre {
    private int passosDados;
    // atributo do robo

    public RoboTerrestreExplorador(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMax, int velocidadeAtual) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMax, velocidadeAtual);
        this.passosDados = 0;
    } // construtor do robo explorador

    public void ZeraPassos(){
        this.passosDados = 0;
    } // metodo para zerar os passos do robo para uma nova exploracao

    // metodo de exploracao do robo, mostra a direcao que ele esta indo e onde ele parou
    public void explorar(Ambiente ambiente, int obstaculos[][]) {
        int x = getX();
        int y = getY();
        String dir = getDirecao().toLowerCase();
        // ajusta a string para poder ser comparavel

        System.out.printf("Iniciando exploração para %d", dir);

        while (true) {
            int novoX = x;
            int novoY = y;

            if (dir.equals("norte")) novoY++;
            else if (dir.equals("sul")) novoY--;
            else if (dir.equals("leste")) novoX++;
            else if (dir.equals("oeste")) novoX--;
            // compara a string direcao e move o robo com base nessa comparacao
            else {
                System.out.println("Direção inválida.");
                break;
                // caso esteja digitado errado, nao funciona
            }

            // condicional para verificar se ha uma barreira a frente do robo que o impeca de prosseguir
            if (!ambiente.dentroDosLimites(novoX, novoY) || identificarObstaculo(obstaculos, novoX, novoY)) {
                if (novoX > ambiente.getLargura()){
                    novoX--;
                }
                else if (novoY > ambiente.getLargura()){
                    novoY--;
                }
                else if (novoX < 0){
                    novoX = 0;
                }
                else if (novoY < 0){
                    novoY = 0;
                } // condicionais para ajustar corretamente a posicao final do robo
                System.out.printf("Exploração interrompida em (%d, %d)\n", novoX, novoY);
                mover(novoX, novoY);
                break;
            }

            if (ambiente.dentroDosLimites(novoX - x, novoY - y)){
                mover(novoX - x, novoY - y);
            } // muda a posicao do robo
            passosDados++;
            x = novoX;
            y = novoY;
        }

        System.out.printf("Exploração finalizada. Passos dados: %d\n", passosDados);
    }

    // metodo que retorna os passos
    public int getPassosDados() {
        return passosDados;
    } 
}