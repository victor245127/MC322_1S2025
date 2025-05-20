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
    
    public int getPosicaoX1() {
        return this.posicaoX1;
    }

    public int getPosicaoX2() {
        return this.posicaoX2;
    }

    public int getPosicaoY1() {
        return this.posicaoY1;
    }

    public int getPosicaoY2() {
        return this.posicaoY2;
    }

    public int getX() {
        throw new UnsupportedOperationException("Unimplemented method 'getX'");
    }

    public int getY() {
        throw new UnsupportedOperationException("Unimplemented method 'getY'");
    }

    public int getZ() {
        throw new UnsupportedOperationException("Unimplemented method 'getZ'");
    }

    public TipoEntidade getTipo() {
        return this.tipo;
    }

    public String getDescricao() {
        return "Objeto imóvel presente no mapa que bloqueia a passagem de robôs em determinadas posições. Possui um tamanho definido.\n";
    }

    public char getRepresentacao() {
        return 'O';
    }
}