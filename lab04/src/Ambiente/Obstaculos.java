package Ambiente;

import Entidade.Entidade;
import Entidade.TipoEntidade;

// Classe de obstáculos
public class Obstaculos implements Entidade{
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private TipoEntidade tipo;
    private TiposObstaculo tipo_obs;
    // Atributos dos obstáculos
    
    public Obstaculos(int x1, int y1, int x2, int y2, TiposObstaculo tipo){ // Construtor de obstáculo
        this.posicaoX1 = x1;
        this.posicaoY1 = y1;
        this.posicaoX2 = x2;
        this.posicaoY2 = y2;
        this.tipo_obs = tipo;
        this.tipo = TipoEntidade.OBSTACULO;
    }

    // Métodos get que retornam os atributos do obstáculo
    public TiposObstaculo getTipoObstaculo(){
        return this.tipo_obs;
    }
    public int getResistencia(){
        return tipo_obs.getResistenciaTipo();
    }
    
    // gets de posição retornam um array contendo a posição x1 no índice 0 e x2 no índice 1
    public int[] getX() {
        return new int[] {this.posicaoX1, this.posicaoX2};
    }

    public int[] getY() {
        return new int[] {this.posicaoY1, this.posicaoY2};
    }

    public int[] getZ() {
        return new int[] {0, this.getTipoObstaculo().getAltura()};
    }

    public TipoEntidade getTipo() {
        return this.tipo;
    }

    // Descrição geral de obstáculos
    public String getDescricao() {
        return "Objeto imóvel presente no mapa que bloqueia a passagem de robôs em determinadas posições. Possui um tamanho definido.";
    }
}