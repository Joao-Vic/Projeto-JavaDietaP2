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

public class DietaDAO {
        
    public DietaDAO(PessoaDAO pessoaDAO, AvaliacaoFisicaDAO avaliacaoFisicaDAO, TipodeDietaDAO tipodeDietaDAO) {    
        
        
    }
    
    private List<Dieta>listaDeDieta = new LinkedList();


    public boolean insereDietaNoBancoDeDados(Dieta dieta) {
        boolean adicionado = true;
        
        String insereDietaNoBancoDeDados = "insert into dieta (pessoa, avaliacaofisica, tipodedieta, objetivo, calorias, nroRefeicoes,"
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereDietaNoBancoDeDados = connection.prepareStatement(insereDietaNoBancoDeDados)) {
                pstminsereDietaNoBancoDeDados.setLong(1, dieta.getPessoa().getId());
                pstminsereDietaNoBancoDeDados.setLong(2, dieta.getAvaliacaofisica().getId());
                pstminsereDietaNoBancoDeDados.setLong(3, dieta.getTipodedieta().getId());
                pstminsereDietaNoBancoDeDados.setString(4, dieta.getObjetivo());
                pstminsereDietaNoBancoDeDados.setDouble(5, dieta.getCalorias());
                pstminsereDietaNoBancoDeDados.setInt(6, dieta.getNroRefeicoes());
                pstminsereDietaNoBancoDeDados.setDate(7, Date.valueOf(dieta.getDataCriacao()));
                pstminsereDietaNoBancoDeDados.setDate(8, Date.valueOf(dieta.getDataModificacao()));
                
                pstminsereDietaNoBancoDeDados.execute();
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
    
    public void buscaDietaNoBancoDeDados(PessoaDAO pessoaDAO, AvaliacaoFisicaDAO avaliacaoFisicaDAO, TipodeDietaDAO tipodeDietaDAO) {
        
        listaDeDieta.clear();
        String buscaDietaNoBancoDeDados = "select * from dieta;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaAvaliacaoFisicaNoBancoDeDados = connection.prepareStatement(buscaDietaNoBancoDeDados);
                ResultSet rs = pstmBuscaAvaliacaoFisicaNoBancoDeDados.executeQuery(buscaDietaNoBancoDeDados)) {
            
            while (rs.next()) {
                
                int idPessoa = rs.getInt("pessoa");
                int idAvaliacaoFisica = rs.getInt("avaliacaofisica");
                int idTipodeDieta = rs.getInt("tipodedieta");
                
                Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);
                AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaDAO.buscaAvaliacaoFisicaPorID(idAvaliacaoFisica);
                TipodeDieta tipodeDieta = tipodeDietaDAO.buscaTipoDeDietaPorId(idTipodeDieta);
                
                Dieta dieta = new Dieta();
                
                dieta.setId(rs.getInt("id"));
                dieta.setObjetivo(rs.getString("objetivo"));
                dieta.setCalorias(rs.getDouble("calorias"));
                dieta.setNroRefeicoes(rs.getInt("nroRefeicoes"));
                dieta.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                dieta.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
           
                dieta.setPessoa(pessoa);
                dieta.setAvaliacaofisica(avaliacaoFisica);
                dieta.setTipodedieta(tipodeDieta);
                
                listaDeDieta.add(dieta);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar dieta!" + e);
        }
        
    }
    
    public boolean alterarNumerodeRefeicoesDaDietaNoBancoDeDados(Dieta dieta, int novoNumeroDeRefeicoes) {

        boolean atualizado = true;

        String atualizaNumerodeRefeicoesDaDietaNoBancoDeDados = "update dieta set nroRefeicoes = ? where id = ?;";

        String atualizaDataAlteracaoDieta = "update dieta set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaNumerodeRefeicoesDaDietaNoBancoDeDados = connection.prepareStatement(atualizaNumerodeRefeicoesDaDietaNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoDieta = connection.prepareStatement(atualizaDataAlteracaoDieta);) {
                pstmAtualizaNumerodeRefeicoesDaDietaNoBancoDeDados.setInt(1, novoNumeroDeRefeicoes);
                pstmAtualizaNumerodeRefeicoesDaDietaNoBancoDeDados.setLong(2, dieta.getId());
                pstmAtualizaNumerodeRefeicoesDaDietaNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoDieta.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoDieta.setLong(2, dieta.getId());
                pstmAtualizaDataAlteracaoDieta.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o numero de refeicoes!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

    public void exibirDieta() {
        for (Dieta dieta : listaDeDieta) {

            if (dieta != null) {
                System.out.println(dieta + "\n");
            }
        }
    }
    
    public Dieta buscaDietaPorID(int id)
    {
        for (Dieta dieta : listaDeDieta) {
            if(dieta != null && dieta.getId() == id)
            {
                return dieta;
            }
        }
        return null;
    }
    
    public void exibirMinhaDieta(Pessoa pessoa) {
        for (Dieta dieta : listaDeDieta) {

            if (dieta != null && dieta.getPessoa().equals(pessoa)) {
                System.out.println(dieta + "\n");
            }
        }
    }
    
    private double DefineCalorias(String objetivoDieta, AvaliacaoFisica avaliacaoFisica) {
        switch (objetivoDieta) {
            case "Diminuir o peso":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm - 300;
                return calorias;
            }
            case "Aumentar o peso":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm + 300;
                return calorias;
            }
            case "Manter o peso":
            case "Melhorar composição corporal":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm;
                return calorias;
            }
            default:
                break;
        }
        return 0;
    }
    
    
    
}
