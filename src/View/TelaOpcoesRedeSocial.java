
package View;

import java.util.Scanner;


public class TelaOpcoesRedeSocial {
    
     Scanner scanner = new Scanner(System.in);
    
     public int telaMenuOpcoesRedeSocial() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU OPCOES REDE SOCIAL ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para solicitar permissao para seguir pessoa: ");
        builder.append("\n2 - Para ver minhas solicitacoes para seguir pessoa: ");
        builder.append("\n3 - Para responder solicitacoes sobre seguir pessoa: ");
        builder.append("\n4 - Para ver as pessoas que eu sigo: ");
        builder.append("\n5 - Para ver minhas mensagens: ");
        builder.append("\n6 - Para enviar uma mensagem: ");
        builder.append("\n7 - Para ver meus posts: ");
        builder.append("\n8 - Para fazer um post: ");
        builder.append("\n9 - Para ver minha timeline: ");
        builder.append("\n10 - Para ver os posts de uma pessoa especifica: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
     
      public int telaMenuOpcoesRespostaSolicitacao() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ SOLICITACAO PARA SEGUIR ============");
        builder.append("\n1 - Para aceitar solicitacao: ");
        builder.append("\n0 - Para recusar solicitacao: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
}
