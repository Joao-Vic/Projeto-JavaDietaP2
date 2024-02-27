package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AlimentoRefeicoesDAO {
    
    public AlimentoRefeicoesDAO(){
    
    }
    
    private List<AlimentoRefeicoes>listaDeAlimentoRefeicoes = new LinkedList();
    
    public boolean insereAlimentoRefeicoesNoBancoDeDados(Preferencias preferencias, Refeicoes refeicoes) {
        boolean adicionado = true;
        
        String insereAlimentoRefeicoesNoBanco = "insert into alimentoRefeicoes (refeicao, alimento, porcao, proteinas, gorduras, calorias, "
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereAlimentoRefeicoesNoBancoDeDados = connection.prepareStatement(insereAlimentoRefeicoesNoBanco)) {
                pstminsereAlimentoRefeicoesNoBancoDeDados.setLong(1, refeicoes.getId());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setLong(2, preferencias.getAlimento().getId());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDouble(3, preferencias.getAlimento().getPorcao());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDouble(4, preferencias.getAlimento().getProteinas());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDouble(5, preferencias.getAlimento().getGorduras());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDouble(6, preferencias.getAlimento().getCalorias());
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDate(7, Date.valueOf(LocalDate.now()));
                pstminsereAlimentoRefeicoesNoBancoDeDados.setDate(8, Date.valueOf(LocalDate.now()));
                
                pstminsereAlimentoRefeicoesNoBancoDeDados.execute();
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

    public void buscaAlimentoRefeicoesNoBancoDeDados(RefeicoesDAO refeicoesDAO, AlimentoDAO alimentoDAO) {
        
        listaDeAlimentoRefeicoes.clear();
        String buscaAlimentoRefeicoesNoBancoDeDados = "select * from alimentorefeicoes;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaAlimentoRefeicoesNoBancoDeDados = connection.prepareStatement(buscaAlimentoRefeicoesNoBancoDeDados);
                ResultSet rs = pstmBuscaAlimentoRefeicoesNoBancoDeDados.executeQuery(buscaAlimentoRefeicoesNoBancoDeDados)) {
            
            while (rs.next()) {
                
                int idRefeicao = rs.getInt("refeicao");
                int idAlimento = rs.getInt("alimento");
                
                Refeicoes refeicoes = refeicoesDAO.buscaRefeicaoPorID(idRefeicao);
                Alimento alimento = alimentoDAO.buscaAlimentoPorID(idAlimento);
                
                AlimentoRefeicoes alimentoRefeicoes = new AlimentoRefeicoes();
                
                alimentoRefeicoes.setId(rs.getInt("id"));
                alimentoRefeicoes.setPorcao(rs.getDouble("porcao"));
                alimentoRefeicoes.setProteina(rs.getDouble("proteinas"));
                alimentoRefeicoes.setGordura(rs.getDouble("gorduras"));
                alimentoRefeicoes.setCalorias(rs.getDouble("calorias"));
                alimentoRefeicoes.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                alimentoRefeicoes.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
           
                alimentoRefeicoes.setRefeicao(refeicoes);
                alimentoRefeicoes.setAlimento(alimento);
                
                listaDeAlimentoRefeicoes.add(alimentoRefeicoes);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar alimento refeicao!" + e);
        }
        
    }
    
    public void exibirAlimentoRefeicoes() {
        for (AlimentoRefeicoes alimentoRefeicoes : listaDeAlimentoRefeicoes) {

            if (alimentoRefeicoes != null) {
                System.out.println(alimentoRefeicoes + "\n");
            }
        }
    }
    
    public void exibirMeuAlimentoRefeicoes(Pessoa pessoa) {
        for (AlimentoRefeicoes alimentoRefeicoes : listaDeAlimentoRefeicoes) {

            if (alimentoRefeicoes != null && alimentoRefeicoes.getRefeicao().getDieta().getPessoa().equals(pessoa)) {
                System.out.println(alimentoRefeicoes + "\n");
            }
        }
    }
    
    public void exibirMinhaDietaPersonalizada (Pessoa pessoa)
    {
        for (AlimentoRefeicoes vetorDeAlimentosRefeicoe : listaDeAlimentoRefeicoes) {
            
            if(vetorDeAlimentosRefeicoe != null && vetorDeAlimentosRefeicoe.getRefeicao().getDieta().getPessoa().equals(pessoa))
            {
                System.out.println(vetorDeAlimentosRefeicoe + "\n");
            }
            
        }
    }
    
}
