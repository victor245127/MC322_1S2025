package Entidade;

public enum TipoEntidade { // Enumeração dos tipos de entidade
    VAZIO(),
    ROBO(),
    OBSTACULO(),
    DESCONHECIDO();

    // Retorna a representação no mapa de uma entidade
    public char getRepresentacao(TipoEntidade ent){
        switch (ent) {
            case VAZIO:
                return '.';
            case OBSTACULO:
                return 'O';
            case ROBO:
                return 'R';
            default:
                return '?';
        }
    }
}
