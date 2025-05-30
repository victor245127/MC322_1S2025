package Comunicacao;

import java.util.ArrayList;

// Classe que armazena e permite troca de mensagens entre robôs
public class CentralComunicacao {
    private ArrayList<String> mensagens;
    private int raio_comunicacao;
    // Atributos
    
    public CentralComunicacao(int raio){
        this.mensagens = new ArrayList<>();
        this.raio_comunicacao = raio;
    } // Construtor

    // Registra uma nova mensagem
    public void registrarMensagem (String destinatario, String remetente, String msg){
        mensagens.add(remetente);
        mensagens.add(destinatario);
        mensagens.add(msg);
    }

    // Método que exibe todas as mensagens enviadas até o momento
    public void exibirMensagens(){
        for (int i = 0; i < mensagens.size(); i+=3){
            System.out.printf("De: %s\nPara: %s\n%s\n", mensagens.get(i), mensagens.get(i+1), mensagens.get(i+2));
        }
    }

    // Retorna o raio de comunicação
    public int getRaio(){
        return raio_comunicacao;
    }
}
