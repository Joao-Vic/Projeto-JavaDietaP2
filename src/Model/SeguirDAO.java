package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SeguirDAO {

    private List<Seguir> listaDeSeguir = new LinkedList();

    public boolean insereSeguirNoBancoDeDados(Pessoa pessoaOrigem, Pessoa pessoaSeguindo) {
        boolean adicionado = true;

        String insereSeguirNoBanco = "insert into seguir (pessoaOrigem, pessoaSeguindo, seguindo, dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);

            try (PreparedStatement pstminsereSeguirNoBancoDeDados = connection.prepareStatement(insereSeguirNoBanco)) {
                pstminsereSeguirNoBancoDeDados.setLong(1, pessoaOrigem.getId());
                pstminsereSeguirNoBancoDeDados.setLong(2, pessoaSeguindo.getId());
                pstminsereSeguirNoBancoDeDados.setBoolean(3, false);
                pstminsereSeguirNoBancoDeDados.setDate(4, Date.valueOf(LocalDate.now()));
                pstminsereSeguirNoBancoDeDados.setDate(5, Date.valueOf(LocalDate.now()));

                pstminsereSeguirNoBancoDeDados.execute();
                connection.commit();

            } catch (SQLException e) {

                connection.rollback();
                adicionado = false;
                System.out.println("Erro: " + e);
            }

        } catch (SQLException e) {

        }

        return adicionado != false;
    }

    public void buscaSeguirNoBancoDeDados(PessoaDAO pessoaDAO) {

        listaDeSeguir.clear();
        String buscaSeguirNoBancoDeDados = "select * from seguir;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaSeguirNoBancoDeDados = connection.prepareStatement(buscaSeguirNoBancoDeDados);
                ResultSet rs = pstmBuscaSeguirNoBancoDeDados.executeQuery(buscaSeguirNoBancoDeDados)) {

            while (rs.next()) {

                Pessoa pessoaOrigem = pessoaDAO.buscaPessoaPorId(rs.getInt("pessoaOrigem"));
                Pessoa pessoaSeguindo = pessoaDAO.buscaPessoaPorId(rs.getInt("pessoaSeguindo"));

                Seguir seguir = new Seguir();

                seguir.setId(rs.getInt("id"));
                seguir.setDonoDaConta(pessoaOrigem);
                seguir.setPessoaSeguidaPeloDonoDaConta(pessoaSeguindo);
                seguir.setSeguindo(rs.getBoolean("seguindo"));
                seguir.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                seguir.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());

                listaDeSeguir.add(seguir);
            }

        } catch (SQLException e) {

            System.out.println("\nErro: Não foi possível buscar seguidores!" + e);
        }

    }
    
    public boolean autorizarSeguirNoBancoDeDados(Seguir seguir) {

        boolean atualizado = true;

        String autorizarSeguirNoBancoDeDados = "update seguir set seguindo = ? where id = ?;";

        String atualizaDataAlteracaoSeguir = "update seguir set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAutorizarSeguirNoBancoDeDados = connection.prepareStatement(autorizarSeguirNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoSeguir = connection.prepareStatement(atualizaDataAlteracaoSeguir);) {
                pstmAutorizarSeguirNoBancoDeDados.setBoolean(1, true);
                pstmAutorizarSeguirNoBancoDeDados.setLong(2, seguir.getId());
                pstmAutorizarSeguirNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoSeguir.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoSeguir.setLong(2, seguir.getId());
                pstmAtualizaDataAlteracaoSeguir.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar seguir!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public boolean verificaSolicitacoes(Pessoa pessoaQueSolicita, Pessoa pessoaSolicitada) {
        for (Seguir Solicitacoes : listaDeSeguir) {
            if (Solicitacoes != null && Solicitacoes.getPessoaSeguidaPeloDonoDaConta().equals(pessoaSolicitada)
                    || Solicitacoes != null && Solicitacoes.getDonoDaConta().equals(pessoaQueSolicita)) {
                return true;
            }
            if (Solicitacoes != null
                    && Solicitacoes.getPessoaSeguidaPeloDonoDaConta().equals(pessoaQueSolicita)
                    && Solicitacoes.getDonoDaConta().equals(pessoaSolicitada)) {
                return true;
            }
        }
        return false;
    }

    public void verSolicitacoesParaSeguirFeitaPeloDonoDaConta(Pessoa pessoa) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == false) {
                System.out.println(seguir + "\n");
            }
        }
    }

    public void verSolicitacoesParaSeguirFeitaPeloSolicitanteParaDonodaConta(Pessoa pessoa) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == false) {
                System.out.println(seguir + "\n");
            }
        }
    }

    public void verPessoasQueAlguemSegue(Pessoa pessoa) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true
                    || seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true) {

                if (seguir.getDonoDaConta().equals(pessoa)) {

                    System.out.println("Seguindo: " + seguir.getPessoaSeguidaPeloDonoDaConta().getNome()
                            + "," + " " + "Nome de Login: " + seguir.getPessoaSeguidaPeloDonoDaConta().getLogin());
                } else {

                    System.out.println("Seguindo: " + seguir.getDonoDaConta().getNome()
                            + "," + " " + "Nome de Login: " + seguir.getDonoDaConta().getLogin());
                }
            }

        }
    }
    
    

    public Seguir buscaSolicitacaoPorNomeDoSolicitante(Pessoa pessoa, String nomeSolicitante) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.getDonoDaConta().getNome().equals(nomeSolicitante)
                    && seguir.isSeguindo() == false) {
                return seguir;
            }
        }
        return null;

    }

    public boolean responderSolicitacaoDeSeguir(Seguir seguir, boolean aceitou) {
        if (aceitou == true) {
            seguir.setSeguindo(true);
            return true;
        } else {
            return false;
        }
    }

    public Pessoa buscaSeguidorPorNome(String nomeSeguidor) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getDonoDaConta().getNome().equals(nomeSeguidor)) {
                return seguir.getDonoDaConta();
            } else if (seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().getNome().equals(nomeSeguidor)) {
                return seguir.getPessoaSeguidaPeloDonoDaConta();
            }
        }
        return null;
    }

    public Pessoa verPessoasQueEstaoSendoSeguidas(Pessoa pessoa) {
        for (Seguir seguir : listaDeSeguir) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true
                    || seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true) {

                return pessoa;
            }

        }
        return null;
    }
}
