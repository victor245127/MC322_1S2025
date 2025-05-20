package Comunicacao;

import java.util.ArrayList;

// Classe que armazena as mensagens entre robôs
public class CentralComunicacao {
    private ArrayList<String> mensagens;
    
    public CentralComunicacao(){
        this.mensagens = new ArrayList<>();
    }

    // Registra uma nova mensagem com o remetente em um índice par e a mensagem em um ímpar
    public void registrarMensagem (String remetente, String msg){
        mensagens.add(remetente);
        mensagens.add(msg);
    }

    // Método que exibe todas as mensagens enviadas até o momento
    public void exibirMensagens(){
        for (int i = 0; i < mensagens.size(); i+=2){
            System.out.printf("De %s: %s\n", mensagens.get(i), mensagens.get(i+1));
        }
    }
}
