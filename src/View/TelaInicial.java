package View;

import java.util.Scanner;

public class TelaInicial {

    Scanner scanner = new Scanner(System.in);

    public int telaMenuInicial() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU INICIAL ============");
        builder.append("\n0 - Para sair do programa: ");
        builder.append("\n1 - Para fazer o login: ");
        builder.append("\n2 - Para cadastrar uma pessoa: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
