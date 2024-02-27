package View;

import java.util.Scanner;

public class TelaPrescricaoDieta {

    Scanner scanner = new Scanner(System.in);

    public int telaMenuPrescricaoDieta() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU PRESCRICAO DE DIETA ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para ver as opcoes de alimentos: ");
        builder.append("\n2 - Para gerar opcoes de dieta recomendada: ");
        builder.append("\n3 - Para ver as opcoes de avalicao fisica: ");
        builder.append("\n4 - Para ver as opcoes de dietas: ");
        builder.append("\n5 - Para ver as opcoes de preferencias: ");
        builder.append("\n6 - Para ver as opcoes de refeicoes: ");
        builder.append("\n7 - Para ver as opcoes de tipo de dieta: ");
        builder.append("\n8 - Para ver as minhas dietas personalizadas: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public int telaMenuOpcoesAlimentos() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES ALIMENTOS ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar um novo alimento: ");
        builder.append("\n2 - Para ver os alimentos cadastrados: ");
        builder.append("\n3 - Para atualizar o nome de um alimento: ");
        builder.append("\n4 - Para buscar um alimento pelo nome: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public int telaMenuOpcoesAvaliacaoFisica() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES AVALIACAO FISICA ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar uma nova avaliacao fisica: ");
        builder.append("\n2 - Para ver a minha avaliacao fisica: ");
        builder.append("\n3 - Para buscar uma avaliacao fisica pelo nome da pessoa: ");
        builder.append("\n4 - Para ver todas as avaliacoes fisicas cadastradas: ");
        builder.append("\n5 - Para ver meu relatorio: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public int telaMenuOpcoesTipoDeDieta() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES TIPO DE DIETA ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar um novo tipo de dieta: ");
        builder.append("\n2 - Para atualizar a porcentagem de carboidratos de um tipo de dieta: ");
        builder.append("\n3 - Para buscar um tipo de dieta pelo nome: ");
        builder.append("\n4 - Para ver todos os tipos de dietas cadastrados: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());

    }
    
    public int telaMenuOpcoesPreferencias() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES PREFERENCIAS ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar uma nova preferencia: ");
        builder.append("\n2 - Para atualizar o alimento de uma preferencia: ");
        builder.append("\n3 - Para ver minhas preferencias: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());

    }
    
    public int telaMenuOpcoesDieta() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES DIETAS ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar uma nova dieta: ");
        builder.append("\n2 - Para atualizar o id de uma dieta: ");
        builder.append("\n3 - Para ver minha dieta: ");
        builder.append("\n4 - Para ver todas as dieta: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());

    }
    
    public int telaMenuOpcoesObjetivosDieta() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES OBJETIVOS DIETAS ============");
        builder.append("\n0 - Para sair ");
        builder.append("\n1 - Diminuir o peso ");
        builder.append("\n2 - Manter o peso ");
        builder.append("\n3 - Melhorar composição corporal ");
        builder.append("\n4 - Aumentar o peso ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());

    }
    
    public int telaMenuOpcoesRefeicoes() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ OPCOES REFEICOES ============");
        builder.append("\n0 - Para sair: ");
        builder.append("\n1 - Para cadastrar uma nova refeicao: ");
        builder.append("\n2 - Para atualizar o nome de uma refeicao: ");
        builder.append("\n3 - Para ver minha refeicao: ");
        builder.append("\n4 - Para ver todas as refeicoes: ");
        builder.append("\n\nInforme aqui a sua opcao: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());

    }

}
