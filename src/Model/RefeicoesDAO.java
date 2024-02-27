package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class RefeicoesDAO {
    
    public RefeicoesDAO() {        

    }
    
    private List<Refeicoes>listaDeRefeicoes = new LinkedList();
    
    public boolean insereRefeicaoNoBancoDeDados(Refeicoes refeicoes) {
        boolean adicionado = true;
        
        String insereAvaliacaoFisicaNoBanco = "insert into refeicoes (dieta, carboidrato, proteina, gordura, calorias, nomedarefeicao, "
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereRefeicaoNoBancoDeDados = connection.prepareStatement(insereAvaliacaoFisicaNoBanco)) {
                pstminsereRefeicaoNoBancoDeDados.setLong(1, refeicoes.getDieta().getId());
                pstminsereRefeicaoNoBancoDeDados.setDouble(2, refeicoes.getCarboidrato());
                pstminsereRefeicaoNoBancoDeDados.setDouble(3, refeicoes.getProteina());
                pstminsereRefeicaoNoBancoDeDados.setDouble(4, refeicoes.getGordura());
                pstminsereRefeicaoNoBancoDeDados.setDouble(5, refeicoes.getCalorias());
                pstminsereRefeicaoNoBancoDeDados.setString(6, refeicoes.getNomedarefeicao());
                pstminsereRefeicaoNoBancoDeDados.setDate(7, Date.valueOf(refeicoes.getDataCriacao()));
                pstminsereRefeicaoNoBancoDeDados.setDate(8, Date.valueOf(refeicoes.getDataModificacao()));
                
                pstminsereRefeicaoNoBancoDeDados.execute();
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

    public void buscaRefeicaoNoBancoDeDados(DietaDAO dietaDAO) {
        
        listaDeRefeicoes.clear();
        String buscaRefeicaoNoBancoDeDados = "select * from refeicoes;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaRefeicaoNoBancoDeDados = connection.prepareStatement(buscaRefeicaoNoBancoDeDados);
                ResultSet rs = pstmBuscaRefeicaoNoBancoDeDados.executeQuery(buscaRefeicaoNoBancoDeDados)) {
            
            while (rs.next()) {
                
                int idDieta = rs.getInt("dieta");
                
                Dieta dieta = dietaDAO.buscaDietaPorID(idDieta);
                
                Refeicoes refeicoes = new Refeicoes();
                
                refeicoes.setId(rs.getInt("id"));
                refeicoes.setCarboidrato(rs.getDouble("carboidrato"));
                refeicoes.setProteina(rs.getDouble("proteina"));
                refeicoes.setGordura(rs.getDouble("gordura"));
                refeicoes.setCalorias(rs.getDouble("calorias"));
                refeicoes.setNomedarefeicao(rs.getString("nomedarefeicao"));
                refeicoes.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                refeicoes.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
           
                refeicoes.setDieta(dieta);
                
                listaDeRefeicoes.add(refeicoes);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar refeicao!" + e);
        }
        
    }
    
    public Refeicoes buscaRefeicaoPorID(int id)
    {
        for (Refeicoes refeicao : listaDeRefeicoes) {
            if(refeicao != null && refeicao.getId() == id)
            {
                return refeicao;
            }
        }
        return null;
    }
    
    public void exibirMinhaRefeicao(Pessoa pessoa) {
        for (Refeicoes refeicao : listaDeRefeicoes) {

            if (refeicao != null && refeicao.getDieta().getPessoa().equals(pessoa)) {
                System.out.println(refeicao + "\n");
            }
        }
    }
    
    public void exibirRefeicoes() {
        for (Refeicoes refeicao : listaDeRefeicoes) {

            if (refeicao != null) {
                System.out.println(refeicao + "\n");
            }
        }
    }
    
    public boolean alterarNomeDaRefeicaoNoBancoDeDados(Refeicoes refeicoes, String novoNomeRefeicao) {

        boolean atualizado = true;

        String atualizaNomeDaRefeicaoNoBancoDeDados = "update refeicoes set nomedarefeicao = ? where nomedarefeicao = ?;";

        String atualizaDataAlteracaoRefeicao = "update refeicoes set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaNomeDaRefeicaoNoBancoDeDados = connection.prepareStatement(atualizaNomeDaRefeicaoNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoDieta = connection.prepareStatement(atualizaDataAlteracaoRefeicao);) {
                pstmAtualizaNomeDaRefeicaoNoBancoDeDados.setString(1, novoNomeRefeicao);
                pstmAtualizaNomeDaRefeicaoNoBancoDeDados.setString(2, refeicoes.getNomedarefeicao());
                pstmAtualizaNomeDaRefeicaoNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoDieta.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoDieta.setLong(2, refeicoes.getId());
                pstmAtualizaDataAlteracaoDieta.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o nome da refeicao!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }
    
}
