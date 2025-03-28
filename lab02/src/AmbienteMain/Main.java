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
        //vetor para os obstaculos contendo suas posicoes x e y
        Robo robosAtivos[] = new Robo[4];
        //vetor para os robos

        Ambiente ambiente = new Ambiente(altura, largura, robosAtivos, obstaculos);
        //criando ambiente com todos atributos ja definidos
        System.out.printf("Ambiente de %d m x %d m x %d m\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
        
        ambiente.setObstaculo(21, 40, 0);
        ambiente.setObstaculo(40, 25, 1);
        //posicionando os obstaculos

        RoboTerrestrePulo roboTP = new RoboTerrestrePulo("Robo1", "Norte", 25, 45, 30, 10, 40, 25);
        RoboTerrestreRemocao roboTR = new RoboTerrestreRemocao("Robo2", "Sul", 30, 5, 40, 30, 20, 40);
        RoboAereoGPS roboAG = new RoboAereoGPS("Robo3", "Leste", 40, 23, 45, 50, 50, 50);
        RoboAereoDistorcao roboAD = new RoboAereoDistorcao("Robo4", "Oeste", 3, 32, 0, 100, 50, 50);
        //construindo os 4 tipos de robos
        
        ambiente.setRobo(roboTP, 0);
        ambiente.setRobo(roboTR, 1);
        ambiente.setRobo(roboAG, 2);
        ambiente.setRobo(roboAD, 3);
        //adicionando os robos ao vetor de robos

        roboTP.exibirPosicao();
        //exibe a posicao do robo1
        roboTP.Pulo(obstaculos, 45);
        //robo1 pula um obstaculo em seu caminho

        if (ambiente.dentroDosLimites(55, 40)){
            roboTP.mover((55 - roboTP.getX()), (40 - roboTP.getY()));
            System.out.printf("Robo %s movido para a posicao (55, 40).\n", roboTP.getNome());
        }
        else {
            System.out.printf("Nao foi possivel mover o robo %s para a posicao desejada.\n", roboTP.getNome());
        } // condicional para testar se o robo1 pode se mover para a posicao desejada


        roboAG.descer(50);
        roboAD.subir(50);
        //mudando as altitudes dos robos aereos

        roboAD.Distorcer(ambiente);
        //aumentando as dimensoes do ambiente
        roboAD.subir(45);
        //agora robo4 pode ir para uma altitude maior do que a maxima anteriormente
        if (ambiente.dentroDosLimites(55, 40)){
            roboTP.mover(55, 40);
            System.out.printf("Robo %s movido para a posicao (%d, %d).\n", roboTP.getNome(), roboTP.getX(), roboTP.getY());
        }
        else {
            System.out.printf("Nao foi possivel mover o robo %s para a posicao desejada.\n", roboTP.getNome());
        }// agora robo1 pode se mover para uma posicao que antes era impossivel de chegar


        roboTR.removeObstaculo(obstaculos, 40);
        //robo de remocao remove um obstaculo em seu caminho

        roboAG.distancia(roboTP);
        // robo3 mostra a distancia em X e Y que robo1 precisa percorrer para chegar em uma certa posicao
    }
}
