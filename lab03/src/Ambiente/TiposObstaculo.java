package Ambiente;

public enum TiposObstaculo {
    PAREDE(3, 5, true, false),
    PEDRA(0, 2, true, false),
    GIRAFA(3, 5, true, true),
    PREDIO(8, 10, true, false),
    BURACO(0, 1, false, false);
    
    private final int altura;
    private final int resistencia;
    private final boolean bloqueia;
    private final boolean calor;

    TiposObstaculo(int h, int res, boolean block, boolean calor) {
        this.altura = h;
        this.resistencia = res;
        this.bloqueia = block;
        this.calor = calor;
    }

    public int getAltura(){
        return this.altura;
    }

    public int getResistencia(){
        return this.resistencia;
    }

    public boolean isBloqueador(){
        return this.bloqueia;
    }

    public boolean emiteCalor(){
        return this.calor;
    }

    public String getTipo(){
        return this.name();
    }
}
