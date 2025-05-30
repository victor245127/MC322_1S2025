package Entidade;

// Interface das entidades
public interface Entidade {
    public int [] getX();
    public int [] getY();
    public int [] getZ();
    public TipoEntidade getTipo();
    public String getDescricao();
    // getRepresentação está em TipoEntidade
}
