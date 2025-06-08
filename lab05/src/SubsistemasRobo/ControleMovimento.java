package SubsistemasRobo;

public class ControleMovimento {
    private boolean ligado;

    public void ligar(){
        ligado = true;
        System.out.println("Controle de movimento ligado.");
    }  

    public void desligar(){
        ligado = false;
        System.out.println("Controle de movimento desligado.");
    }

    public boolean getControle(){
        return ligado;
    }
}
