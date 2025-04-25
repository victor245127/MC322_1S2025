package RobosBase;

import java.util.ArrayList;

import Ambiente.Ambiente;
import Sensor.Sensor;

// superclasse robo, a qual as demais herdam
public class Robo {
    private String nome;
    private String direcao;
    private int posicaoX;
    private int posicaoY;
    private ArrayList<Sensor> sensores;
    // Atributos gerais dos robôs

    public Robo(String nome, String direcao, int posicaoX, int posicaoY){ // Construtor geral dos robôs
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.direcao = direcao;
        this.sensores = new ArrayList<>();
    }
    
    public void mover(int deltaX, int deltaY, Ambiente ambiente) { // Método que move o robô
        int novaX = this.posicaoX + deltaX;
        int novaY = this.posicaoY + deltaY;
        
        // Verifica se a nova posição está dentro dos limites e, se estiver, move o robô
        if (ambiente.dentroDosLimites(novaX, novaY)) {
            
            this.posicaoX = novaX;
            this.posicaoY = novaY;
        } else {
            System.out.println("Movimento inválido: fora dos limites do ambiente.\n");
        }
    }
    
    public void exibirPosicao(){ // Exibe a posição do robô
        System.out.printf("Posição: X = %d, Y = %d\n", this.posicaoX, this.posicaoY);
    }

    public void identificarObstaculo(Ambiente ambiente) { // Identifica se há obstáculo próximo ao robô
        int x = this.getPosX();
        int y = this.getPosY();
    
        boolean encontrou = false;
    
        // Cima
        if (ambiente.temObstaculoEm(x, y + 1)) {
            System.out.println("Obstáculo acima.");
            encontrou = true;
        }
    
        // Baixo
        if (ambiente.temObstaculoEm(x, y - 1)) {
            System.out.println("Obstáculo abaixo.");
            encontrou = true;
        }
    
        // Direita
        if (ambiente.temObstaculoEm(x + 1, y)) {
            System.out.println("Obstáculo à direita.");
            encontrou = true;
        }
    
        // Esquerda
        if (ambiente.temObstaculoEm(x - 1, y)) {
            System.out.println("Obstáculo à esquerda.");
            encontrou = true;
        }
    
        if (!encontrou) {
            System.out.println("Nenhum obstáculo ao redor.");
        }
    }
    
    // Métodos get que retornam os atributos do robô
    public int getPosX(){
        return this.posicaoX;
    }

    public int getPosY(){
        return this.posicaoY;
    }

    public String getDirecao() {
        return this.direcao;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void adicionarSensor(Sensor s) { // Adiciona sensor ao robô
        sensores.add(s);
    }
    
    public void ativarSensores(Ambiente ambiente) { // Utiliza as funções dos sensores
        int x = getPosX();
        int y = getPosY();
        int z = 0;
    
        if (this instanceof RoboAereo) { // Pega a posição Z do robô aéreo
            z = ((RoboAereo) this).getAltitude();
        }
    
        for (Sensor s : sensores) {
            s.monitorar(ambiente, x, y, z);
        }
    }
    
}