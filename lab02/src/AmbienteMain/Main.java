package lab02.src.AmbienteMain;
import lab02.src.RoboBase.Robo;
import lab02.src.RoboVariacoes.RoboAereoDistorcao;
import lab02.src.RoboVariacoes.RoboAereoObservador;
import lab02.src.RoboVariacoes.RoboTerrestreExplorador;
import lab02.src.RoboVariacoes.RoboTerrestreRemocao;

// classe principal
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
        ambiente.setObstaculo(4, 100, 1);
        ambiente.setObstaculo(1, 0, 2);
        ambiente.setObstaculo(45, 27, 3);
        ambiente.setObstaculo(30, 20, 4);
        //posicionando os obstaculos

        RoboTerrestreExplorador roboTE = new RoboTerrestreExplorador("Robo1", "Norte", 25, 45, 30, 10);
        RoboTerrestreRemocao roboTR = new RoboTerrestreRemocao("Robo2", "Sul", 30, 5, 40, 30, 20, 40);
        RoboAereoObservador roboAO = new RoboAereoObservador("Robo3", "Leste", 40, 23, 45, 50, 10);
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
        // robo3 desceria alem do limite, impossibilitado
        roboAO.descer(5);
        // robo3 desce a uma altitude existente no amibente
        roboAD.subir(80, ambiente);
        // robo4 tenta subir mas eh impossibilitado pelo limite do ambiente

        if (ambiente.dentroDosLimites(55, 40)){
            roboTE.mover(55, 40, 15);
        }
        else {
            System.out.println("Posicao nao permitida, se encontra alem dos limites do ambiente.\n");
        } // robo1 tenta mover a uma posicao

        roboAD.Distorcer(ambiente);
        // robo4 distorce o ambiente, alterando suas dimensoes

        roboAD.subir(80, ambiente);
        // robo4 agora pode subir para a altitude desejada

        roboTE.mover(55, 40, 15);
        //robo1 agora pode mover-se a posicao desejada

        roboTR.removeObstaculo(obstaculos, 45);
        // robo2 vai a uma posicao e remove um obstaculo proximo a ela
        
        roboTE.explorar(ambiente, obstaculos);
        // robo1 explora o ambiente em direcao a norte

        roboTE.ZeraPassos();
        roboTE.setDirecao("Oeste");
        roboTE.explorar(ambiente, obstaculos);

        roboAO.observar(ambiente, obstaculos);
        // robo3 observa o ambiente e identifica os obstaculos dentro de seu raio

        roboAO.mover(10, 10);
        roboAO.setRaio(3);
        roboAO.observar(ambiente, obstaculos);
        // robo3 move-se a uma nova posicao, diminui seu raio de visao e observa novamente

        roboTE.exibirPosicao();
        roboTR.exibirPosicao();
        roboAO.exibirPosicao();
        roboAD.exibirPosicao();
        //exibindo as posicoes finais dos robos        
    }
}
