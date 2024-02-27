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

public class TipodeDietaDAO {

    public TipodeDietaDAO() {

    }
    
    private List<TipodeDieta>listaDeTipodeDieta = new LinkedList();
    
    public boolean insereTipoDeDietaNoBancoDeDados(TipodeDieta tipodeDieta) {
        boolean adicionado = true;
        
        String insereTipoDeDietaNoBanco = "insert into tipodedieta (nome, carboidrato, proteina, gordura,"
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereTipoDeDietaNoBancoDeDados = connection.prepareStatement(insereTipoDeDietaNoBanco)) {
                pstminsereTipoDeDietaNoBancoDeDados.setString(1, tipodeDieta.getNome());
                pstminsereTipoDeDietaNoBancoDeDados.setDouble(2, tipodeDieta.getCarboidrato());
                pstminsereTipoDeDietaNoBancoDeDados.setDouble(3, tipodeDieta.getProteina());
                pstminsereTipoDeDietaNoBancoDeDados.setDouble(4, tipodeDieta.getGordura());
                pstminsereTipoDeDietaNoBancoDeDados.setDate(5, Date.valueOf(tipodeDieta.getDataCriacao()));
                pstminsereTipoDeDietaNoBancoDeDados.setDate(6, Date.valueOf(tipodeDieta.getDataModificacao()));
                
                pstminsereTipoDeDietaNoBancoDeDados.execute();
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
    
    public void buscaTipoDeDietaNoBancoDeDados() {
        
        listaDeTipodeDieta.clear();
        String buscaTipoDeDietaNoBancoDeDados = "select * from tipodedieta;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaTipoDeDietaNoBancoDeDados = connection.prepareStatement(buscaTipoDeDietaNoBancoDeDados);
                ResultSet rs = pstmBuscaTipoDeDietaNoBancoDeDados.executeQuery(buscaTipoDeDietaNoBancoDeDados)) {
            
            while (rs.next()) {
                
                TipodeDieta tipodeDieta = new TipodeDieta();
                
                tipodeDieta.setId(rs.getInt("id"));
                tipodeDieta.setNome(rs.getString("nome"));
                tipodeDieta.setCarboidrato(rs.getDouble("carboidrato"));
                tipodeDieta.setProteina(rs.getDouble("proteina"));
                tipodeDieta.setGordura(rs.getDouble("gordura"));
                tipodeDieta.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                tipodeDieta.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
                
                listaDeTipodeDieta.add(tipodeDieta);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar tipos de dieta!" + e);
        }
        
    }
    
    public void exibirTipoDeDieta() {
        for (TipodeDieta tipodeDieta : listaDeTipodeDieta) {
            
            if (tipodeDieta != null) {
                System.out.println(tipodeDieta + "\n");
            }
        }
    }
    
    /*public boolean alterarCarboidratoDoTipoDeDieta(TipodeDieta tipodeDieta, double novoCarboidratoTipoDeDieta) {
        
        boolean atualizado = true;
        
        String atualizaCarboidratoDoTipoDeDieta = "update tipodedieta set carboidrato = ? where id = ?;";
        
        
        try(Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
            PreparedStatement pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados = connection.prepareStatement(atualizaCarboidratoDoTipoDeDieta);) 
        {
            pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.setDouble(1, novoCarboidratoTipoDeDieta);
            pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.setLong(2, tipodeDieta.getId());
            pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.execute();
        } 
        catch (SQLException e) {
            
            atualizado = false;
            System.out.println("\nErro: Não foi possível atualizar o carboidrato!" + e);
     
        }
        return atualizado != false;
    }*/
    
    public boolean alterarCarboidratoDoTipoDeDieta(TipodeDieta tipodeDieta, double novoCarboidratoTipoDeDieta) {

        boolean atualizado = true;

         String atualizaCarboidratoDoTipoDeDieta = "update tipodedieta set carboidrato = ? where id = ?;";

        String atualizaDataAlteracaoTipoDeDieta = "update tipodedieta set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados = connection.prepareStatement(atualizaCarboidratoDoTipoDeDieta);
                PreparedStatement pstmAtualizaDataAlteracaoTipoDeDieta = connection.prepareStatement(atualizaDataAlteracaoTipoDeDieta);) {
                pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.setDouble(1, novoCarboidratoTipoDeDieta);
                pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.setLong(2, tipodeDieta.getId());
                pstmAtualizaCarboidratoDoTipoDeDietaNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoTipoDeDieta.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoTipoDeDieta.setLong(2, tipodeDieta.getId());
                pstmAtualizaDataAlteracaoTipoDeDieta.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o carboidrato!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }
    
    public TipodeDieta buscaTipoDeDietaPorNome(String nomeTipoDeDieta) {
        for (TipodeDieta tipodeDieta : listaDeTipodeDieta) {

            if (tipodeDieta != null && tipodeDieta.getNome().equals(nomeTipoDeDieta)) {
                return tipodeDieta;
            }
        }
        return null;
    }
    
    public TipodeDieta buscaTipoDeDietaPorId(int Id) {
        for (TipodeDieta tipodeDieta : listaDeTipodeDieta) {

            if (tipodeDieta != null && tipodeDieta.getId() == Id) {
                return tipodeDieta;
            }
        }
        return null;
    }
}
