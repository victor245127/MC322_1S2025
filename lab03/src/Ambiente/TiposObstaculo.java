package Ambiente;

public enum TiposObstaculo {
    PAREDE(5, true, false),
    PEDRA(0, true, false),
    ROBO(1, true, true), 
    GIRAFA(5, true, true),
    PREDIO(30, true, false),
    BURACO(0, false, false);
    
    private final int altura;
    private final boolean bloqueia;
    private final boolean calor;

    TiposObstaculo(int h, boolean block, boolean calor) {
        this.altura = h;
        this.bloqueia = block;
        this.calor = calor;
    }

    public int getAltura(){
        return this.altura;
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
