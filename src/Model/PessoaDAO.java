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

public class PessoaDAO {

    public PessoaDAO() {

    }

    private List<Pessoa> listaDePessoa = new LinkedList();

    public boolean inserePessoaNoBancoDeDados(Pessoa pessoa) {
        boolean adicionado = true;

        String inserePessoaNoBanco = "insert into pessoa (nome, sexo, nascimento, login, senha, tipousuario, dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmInserePessoaNoBanco = connection.prepareStatement(inserePessoaNoBanco)) {
                pstmInserePessoaNoBanco.setString(1, pessoa.getNome());
                pstmInserePessoaNoBanco.setString(2, pessoa.getSexo());
                pstmInserePessoaNoBanco.setDate(3, Date.valueOf(pessoa.getNascimento()));
                pstmInserePessoaNoBanco.setString(4, pessoa.getLogin());
                pstmInserePessoaNoBanco.setString(5, pessoa.getSenha());
                pstmInserePessoaNoBanco.setString(6, pessoa.getTipoUsuario());
                pstmInserePessoaNoBanco.setDate(7, Date.valueOf(pessoa.getDataCriacao()));
                pstmInserePessoaNoBanco.setDate(8, Date.valueOf(pessoa.getDataModificacao()));

                pstmInserePessoaNoBanco.execute();
                connection.commit();

            } catch (SQLException e) {

                connection.rollback();
                adicionado = false;
            }

        } catch (SQLException e) {

        }

        return adicionado != false;
    }

    public void exibirPessoas() {
        for (Pessoa pessoa : listaDePessoa) {

            if (pessoa != null) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public void buscaPessoaNoBancoDeDados() {

        listaDePessoa.clear();
        String buscaPessoaNoBancoDeDados = "select * from pessoa;";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaPessoaNoBancoDeDados = connection.prepareStatement(buscaPessoaNoBancoDeDados);
                ResultSet rs = pstmBuscaPessoaNoBancoDeDados.executeQuery(buscaPessoaNoBancoDeDados)) {

            while (rs.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSexo(rs.getString("sexo"));
                pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setTipoUsuario(rs.getString("tipousuario"));
                pessoa.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                pessoa.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());

                listaDePessoa.add(pessoa);
            }

        } catch (SQLException e) {

            System.out.println("\nErro: Não foi possível buscar pessoas!" + e);
        }

    }

    public Pessoa buscaPessoaQuerendoLogar(String login, String senha) {
        for (Pessoa pessoa : listaDePessoa) {

            if (pessoa != null && pessoa.getLogin().equals(login)
                    && pessoa.getSenha().equals(senha)) {
                return pessoa;
            }
        }

        return null;
    }

    public Pessoa buscaPessoaPorNome(String nomePessoa) {
        for (Pessoa pessoa : listaDePessoa) {

            if (pessoa != null && pessoa.getNome().equals(nomePessoa)) {
                return pessoa;
            }
        }
        return null;
    }

    public Pessoa buscaPessoaPorId(long idPessoa) {
        for (Pessoa pessoa : listaDePessoa) {

            if (pessoa != null && pessoa.getId() == idPessoa) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean alterarNomeDaPessoaNoBancoDeDados(Pessoa pessoa, String novoNome) {

        boolean atualizado = true;

        String atualizaNomeDaPessoaNoBancoDeDados = "update pessoa set nome = ? where nome = ?;";

        String atualizaDataAlteracaoPessoa = "update pessoa set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaNomeDaPessoaNoBancoDeDados = connection.prepareStatement(atualizaNomeDaPessoaNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoPessoa = connection.prepareStatement(atualizaDataAlteracaoPessoa);) {
                pstmAtualizaNomeDaPessoaNoBancoDeDados.setString(1, novoNome);
                pstmAtualizaNomeDaPessoaNoBancoDeDados.setString(2, pessoa.getNome());
                pstmAtualizaNomeDaPessoaNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoPessoa.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoPessoa.setLong(2, pessoa.getId());
                pstmAtualizaDataAlteracaoPessoa.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o nome!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

}
