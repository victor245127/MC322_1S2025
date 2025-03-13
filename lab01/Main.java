package lab01;
import java.util.Scanner;

//classe principal Main
public class Main 
{
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        int posX, posY, altura, largura;
        String nome;

        System.out.println("Digite as posicoes X e Y do robo e seu nome: ");
        posX = entrada.nextInt();
        posY = entrada.nextInt();
        nome = entrada.next();
        //constroi o robo e define seus atributos
        Robo robo = new Robo(nome, posX, posY);
        robo.exibirPosicao();

        System.out.println("\nDigite as dimensoes de altura e largura do ambiente: ");
        altura = entrada.nextInt();
        largura = entrada.nextInt();
        //constroi o ambiente e define seus atributos
        Ambiente ambiente = new Ambiente(altura, largura);
        ambiente.getDimensoes();

        //loop para mover o robo e ao mesmo tempo verificar se eh possivel ou nao 
        //(de acordo com as dimensoes do ambiente)
        do {
            System.out.printf("\nMova %s para outras posicoes X e Y: ", robo.exibirNome());
            posX = entrada.nextInt();
            posY = entrada.nextInt();         
            if (!ambiente.dentroDosLimites(posX, posY)){
                //caso nao seja possivel, tentar mudar para uma outra posicao
                System.out.printf("Seu robo foi para (%d, %d) fora dos limites do ambiente, tente novamente.", posX, posY);
            } 
            else {
                //caso seja possivel, muda o robo de posicao e finaliza o loop
                robo.mover(posX, posY);
                System.out.printf("O robo %s mudou para a posicao (%d, %d).", robo.exibirNome(), posX, posY);
                break;
            }
        } while (true);
        entrada.close();
        //fim do codigo              
    }
}