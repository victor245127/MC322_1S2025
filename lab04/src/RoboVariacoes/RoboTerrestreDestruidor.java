package RoboVariacoes;

// Robo que consegue destruir um obstáculo ao redor dele 

import Ambiente.Ambiente;
import RobosBase.RoboTerrestre;

public class RoboTerrestreDestruidor extends RoboTerrestre {
    private int forcaDestruicao; // Atributo do robô

    public RoboTerrestreDestruidor(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, int forcaDestruicao) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima);
        this.forcaDestruicao = forcaDestruicao;
    } // Construtor 

    public void destruirObstaculo(Ambiente ambiente) {
        // Habilidade do robô que procura um obstáculo ao seu redor e, caso ache,
        // tenta destruí-lo, que funcionará de acordo com sua força de destruição
        // e a resistência do obstáculo, ou seja, caso a resistência seja menor/igual que a força,
        // o obstáculo é destruído, e vice versa
        int x = this.getPosX();
        int y = this.getPosY();
        
        // Direita
        if (ambiente.temObstaculoEm(x + 1, y)) {
            int resistencia = ambiente.getResistenciaEm(x + 1, y);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo à direita. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x + 1, y);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Esquerda
        } else if (ambiente.temObstaculoEm(x - 1, y)) {
            int resistencia = ambiente.getResistenciaEm(x - 1, y);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo à esquerda. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x - 1, y);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Frente
        } else if (ambiente.temObstaculoEm(x, y + 1)) {
            int resistencia = ambiente.getResistenciaEm(x, y + 1);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo em frente. Destruindo com forca %d\n", forcaDestruicao);
                ambiente.removerObstaculoEm(x, y + 1);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }

        // Trás
        } else if (ambiente.temObstaculoEm(x, y - 1)) {
            int resistencia = ambiente.getResistenciaEm(x, y - 1);
            if (this.forcaDestruicao >= resistencia){
                System.out.printf("Obstáculo atrás. Destruindo com forca %d", forcaDestruicao);
                ambiente.removerObstaculoEm(x, y - 1);
            }
            else {
                System.out.println("O robo nao tem forca suficiente para a destruicao desse obstaculo.\n");
            }
        } else {
            System.out.println("Nenhum obstáculo ao redor para destruir.\n");
        }
    }   
}