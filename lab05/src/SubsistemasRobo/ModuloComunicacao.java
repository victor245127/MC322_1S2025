package SubsistemasRobo;

public class ModuloComunicacao {
    private boolean ligado;

    public void ligar(){
        ligado = true;
        System.out.println("Modulo de comunicacao ligado.");
    }

    public void desligar(){
        ligado = false;
        System.out.println("Modulo de comunicacao desligado.");
    }

    public boolean getModulo(){
        return ligado;
    }
}
