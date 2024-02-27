
package View;

import java.util.Scanner;


public class TelaPrincipal {
    
      Scanner scanner = new Scanner(System.in);

    public int telaMenuPrincipal() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU PRINCIPAL ============");
        builder.append("\n0 - Para sair do programa: ");
        builder.append("\n1 - Para ver o menu de opcoes: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    
}
