package Ambiente;

// Enum dos obstáculos com seus tipos e características
public enum TiposObstaculo {
    PAREDE(3, 5, true, false),
    PEDRA(0, 1, true, false),
    GIRAFA(3, 5, true, true),
    PREDIO(10, 9, true, false),
    BURACO(0, 1, false, false);
    // Tipos dos obstáculos
    
    private final int altura;
    private final int resistencia;
    private final boolean bloqueia;
    private final boolean calor;
    // Atributos dos tipos

    TiposObstaculo(int h, int res, boolean block, boolean calor) { // Construtor dos tipos
        this.altura = h;
        this.resistencia = res;
        this.bloqueia = block;
        this.calor = calor;
    }

    // Métodos get que retornam atributos dos tipos
    public int getAltura(){
        return this.altura;
    }

    public int getResistenciaTipo(){
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
