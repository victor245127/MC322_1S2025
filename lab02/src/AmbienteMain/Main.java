package lab02.src.AmbienteMain;
import lab02.src.RoboBase.Robo;
import lab02.src.RoboVariacoes.RoboAereoDistorcao;
import lab02.src.RoboVariacoes.RoboAereoObservador;
import lab02.src.RoboVariacoes.RoboTerrestreExplorador;
import lab02.src.RoboVariacoes.RoboTerrestreRemocao;

public class Main {
    public static void main(String[] args){
        int largura = 50, altura = 50;
        int obstaculos[][] = new int[5][2];
        //vetor para os obstaculos contendo suas posicoes x e y
        Robo robosAtivos[] = new Robo[4];
        //vetor para os robos

        Ambiente ambiente = new Ambiente(altura, largura, robosAtivos, obstaculos);
        //criando ambiente com todos atributos ja definidos
        System.out.printf("Ambiente de %d m x %d m x %d m\n", ambiente.getLargura(), ambiente.getLargura(), ambiente.getAltura());
        
        ambiente.setObstaculo(21, 40, 0);
        ambiente.setObstaculo(30, 10, 1);
        ambiente.setObstaculo(1, 0, 2);
        ambiente.setObstaculo(15, 32, 3);
        ambiente.setObstaculo(25, 20, 4);
        //posicionando os obstaculos

        RoboTerrestreExplorador roboTE = new RoboTerrestreExplorador("Robo1", "Norte", 25, 45, 30, 10);
        RoboTerrestreRemocao roboTR = new RoboTerrestreRemocao("Robo2", "Sul", 30, 5, 40, 30, 20, 40);
        RoboAereoObservador roboAO = new RoboAereoObservador("Robo3", "Leste", 40, 23, 45, 50, 50);
        RoboAereoDistorcao roboAD = new RoboAereoDistorcao("Robo4", "Oeste", 3, 32, 0, 100, 50, 50);
        //construindo os 4 tipos de robos
        
        ambiente.setRobo(roboTE, 0);
        ambiente.setRobo(roboTR, 1);
        ambiente.setRobo(roboAO, 2);
        ambiente.setRobo(roboAD, 3);
        //adicionando os robos ao vetor de robos

        roboTE.exibirPosicao();
        roboTR.exibirPosicao();
        roboAO.exibirPosicao();
        roboAD.exibirPosicao();
        //exibindo as posicoes iniciais dos robos

        roboAO.descer(60);
        roboAO.descer(5);
        roboAD.subir(80, ambiente);

        if (ambiente.dentroDosLimites(55, 40)){
            roboTE.mover(55, 40, 15);
        }
        else {
            System.out.println("Posicao nao permitida, se encontra alem dos limites do ambiente.\n");
        }

        roboAD.Distorcer(ambiente);
        roboAD.subir(80, ambiente);

        roboTE.mover(55, 40, 15);

        roboTR.removeObstaculo(obstaculos, 45);
        
        roboTE.explorar(ambiente, obstaculos);
        roboAO.observar(ambiente, obstaculos);
    }
}
