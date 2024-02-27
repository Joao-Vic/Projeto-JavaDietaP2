
package View;

import java.util.Scanner;


public class TelaMenuCadastro {
    
    Scanner scanner = new Scanner(System.in);

    public int telaMenuOpcoesCadastro() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU OPCOES ============");
        builder.append("\n0 - Para sair do programa: ");
        builder.append("\n1 - Para ver o meu cadastro: ");
        builder.append("\n2 - Para ver as pessoas cadastradas: ");
        builder.append("\n3 - Para atualizar o meu nome: ");
        builder.append("\n4 - Para ver as opções de rede social: ");
        builder.append("\n5 - Para ver as opções de prescricao de dietas: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
}
