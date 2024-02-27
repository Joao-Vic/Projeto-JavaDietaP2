
package Model;

import java.util.LinkedList;
import java.util.List;


public class MensagemDAO {
    
    private List<Mensagem>listaDeMensagem = new LinkedList();
    
    //private  Mensagem vetorDeMensagem[] = new Mensagem[50];
    
    /*public boolean inserirMensagem(Mensagem mensagem) {
        
        for (int i = 0; i < vetorDeMensagem.length; i++) {

            if (vetorDeMensagem[i] == null) {
                vetorDeMensagem[i] = mensagem;
                return true;
            }

        }
        return false;
    }
    
    
    public void verMensagensDeUmaPessoa (Pessoa pessoa)
    {
        for (Mensagem mensagem : vetorDeMensagem) {
            
            if(mensagem != null && mensagem.getContaDeOrigem().equals(pessoa)
                    || mensagem != null && mensagem.getContaDeDestino().equals(pessoa))
            {
                System.out.println(mensagem + "\n");
            }
        }
    }*/
    
}
