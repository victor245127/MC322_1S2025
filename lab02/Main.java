package lab02;
import java.util.Scanner;

public class Main {
    Scanner ler = new Scanner(System.in);
    int i, largura, altura, obstaculos[][];
    Robo robosAtivos[];
    System.out.println("Digite a largura e altura do ambiente: ");
    largura = ler.nextInt();
    altura = ler.nextInt();
    Ambiente ambiente = new Ambiente(altura, largura, robosAtivos, obstaculos);
    String nome, direcao;
    int X, Y, vMax, vAtual, novoX, novoY, altitude, altitudeMax;
    System.out.println("Digite o nome, direcao,  do robo: ");
    nome = ler.next();

    ler.close();
}
