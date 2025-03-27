package lab02.src.AmbienteMain;
import lab02.src.RoboBase.Robo;
import lab02.src.RoboVariacoes.RoboAereoDistorcao;
import lab02.src.RoboVariacoes.RoboAereoGPS;
import lab02.src.RoboVariacoes.RoboTerrestrePulo;
import lab02.src.RoboVariacoes.RoboTerrestreRemocao;

public class Main {
    public static void main(String[] args){
        int largura = 50, altura = 50;
        int obstaculos[][] = new int[2][2];
        Robo robosAtivos[] = new Robo[4];

        Ambiente ambiente = new Ambiente(altura, largura, robosAtivos, obstaculos);
        System.out.printf("O ambiente tem dimensao %d m x %d m x %d m\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
        ambiente.setObstaculo(21, 40, 0);
        ambiente.setObstaculo(40, 25, 1);

        RoboTerrestrePulo roboTP = new RoboTerrestrePulo("Robo1", "Norte", 25, 45, 30, 10, 40, 25);
        RoboTerrestreRemocao roboTR = new RoboTerrestreRemocao("Robo2", "Sul", 30, 5, 40, 30, 20, 40);
        RoboAereoGPS roboAG = new RoboAereoGPS("Robo3", "Leste", 40, 23, 45, 50, 50, 50);
        RoboAereoDistorcao roboAD = new RoboAereoDistorcao("Robo4", "Oeste", 3, 32, 0, 50, 50, 50);
        ambiente.setRobo(roboTP, 0);
        ambiente.setRobo(roboTR, 1);
        ambiente.setRobo(roboAG, 2);
        ambiente.setRobo(roboAD, 3);

        roboTP.exibirPosicao();
        roboTP.Desvio(obstaculos, 45);

        if (ambiente.dentroDosLimites(55, 40)){
            roboTP.mover((55 - roboTP.getX()), (40 - roboTP.getY()));
            System.out.printf("Robo %s movido para a posicao (55, 40).\n", roboTP.getNome());
        }
        else {
            System.out.println("Nao foi possivel mover o robo para a posicao desejada.\n");
        }
        roboAG.descer(50);
        roboAD.subir(50);

        roboAD.Distorcer(ambiente);
        roboAD.subir(45);
        // apos distorcer o ambiente, os limites aumentam
        if (ambiente.dentroDosLimites(55, 40)){
            roboTP.mover(55, 40);
            System.out.printf("Robo movido para a posicao (%d, %d).\n", roboTP.getX(), roboTP.getY());
        }
        else {
            System.out.println("Nao foi possivel mover o robo para a posicao desejada.\n");
        }
        roboTR.removeObstaculo(obstaculos, 40);
        roboAG.distancia(roboTP);
    }
}
