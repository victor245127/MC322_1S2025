package lab01;
import java.util.Scanner;

public class Main 
{
    //perguntar se deixo static e se declaro antes ou depois
    static Robo robo = new Robo("", 0, 0);
    static Ambiente ambiente = new Ambiente(0, 0);

        public static void main(String[] args){
            //ver se precisa do try
            try (Scanner entrada = new Scanner(System.in)) {
                System.out.println("Digite as posicoes X e Y do robo e seu nome: ");
                robo.posicaoX = entrada.nextInt();
                robo.posicaoY = entrada.nextInt();
                robo.nome = entrada.next();
                System.out.printf("O robo %s esta na posicao (%d, %d).", robo.exibirNome(), robo.posicaoX, robo.posicaoY);

                System.out.println("\nDigite as dimensoes de altura e largura do ambiente: ");
                ambiente.altura = entrada.nextInt();
                ambiente.largura = entrada.nextInt();
                System.out.printf("O ambiente tem dimensao %d x %d", ambiente.altura, ambiente.largura);

                do {
                    System.out.printf("\nMova %s para outras posicoes X e Y: ", robo.exibirNome());
                    robo.mover(entrada.nextInt(), entrada.nextInt()); 
                    if (!ambiente.dentroDosLimites(robo.posicaoX, robo.posicaoY)){
                        System.out.printf("Seu robo foi para (%d, %d) fora dos limites do ambiente, tente novamente.", robo.posicaoX, robo.posicaoY);
                    } 
                    else {
                        System.out.printf("O robo %s mudou para a posicao (%d, %d).", robo.exibirNome(), robo.posicaoX, robo.posicaoY);
                        break;
                    }
                } while (true);              
        }
    }
}