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

public class AlimentoDAO {

    public AlimentoDAO() {

        /*Alimento arroz = new Alimento("arroz branco", 28.18, 2.5, 0.23); //124,79
        Alimento feijao = new Alimento("feijao carioca", 13.61, 5.04, 0.85); //82,25 TOTAL = 451,29
        Alimento bife = new Alimento("bife de vaca", 0, 27.29, 15.01); // 244,25
        Alimento cafe = new Alimento("cafe", 30, 30, 30);
        Alimento alface = new Alimento("alface", 1.63, 0.5, 0.08);
        Alimento asa_frango = new Alimento ("asa de frango", 0, 26.64, 19.3);
        Alimento presunto = new Alimento ("presunto", 3.83, 16.6, 8.6);
        Alimento costela = new Alimento ("costela de frango", 0, 27.91, 14.57);
        Alimento pao_branco = new Alimento ("pao", 50.61, 7.64, 3.29);
        Alimento ovo = new Alimento ("ovo", 0.77, 12.58, 9.94);
        Alimento peixe = new Alimento ("peixe", 0, 20.08, 1.7);
        Alimento leite = new Alimento ("leite", 4.71, 3.29, 2);
        Alimento manteiga = new Alimento ("manteiga", 0.06, 0.85, 81.11);
        Alimento arroz_legumes = new Alimento ("arroz_legumes", 19.14, 2.54, 1.37);
        Alimento feijao_preto = new Alimento ("feijao preto", 16.56, 6.03, 0.29);
        Alimento salada_batata = new Alimento ("salda de batata", 11.17, 2.68, 8.2);*/

    }
    
    private List<Alimento>listaDeAlimentos = new LinkedList();
    
    public boolean insereAlimentoNoBancoDeDados(Alimento alimento) {
        boolean adicionado = true;
        
        String insereAlimentoNoBanco = "insert into alimento (nome, carboidratos, proteinas, gorduras, calorias, porcao, dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereAlimentoNoBancoDeDados = connection.prepareStatement(insereAlimentoNoBanco)) {
                pstminsereAlimentoNoBancoDeDados.setString(1, alimento.getNome());
                pstminsereAlimentoNoBancoDeDados.setDouble(2, alimento.getCarboidratos());
                pstminsereAlimentoNoBancoDeDados.setDouble(3, alimento.getProteinas());
                pstminsereAlimentoNoBancoDeDados.setDouble(4, alimento.getGorduras());
                pstminsereAlimentoNoBancoDeDados.setDouble(5, alimento.getCalorias());
                pstminsereAlimentoNoBancoDeDados.setDouble(6, alimento.getPorcao());
                pstminsereAlimentoNoBancoDeDados.setDate(7, Date.valueOf(alimento.getDataCriacao()));
                pstminsereAlimentoNoBancoDeDados.setDate(8, Date.valueOf(alimento.getDataModificacao()));
                
                pstminsereAlimentoNoBancoDeDados.execute();
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
    
    public void exibirAlimentos() {
        for (Alimento alimento : listaDeAlimentos) {
            
            if (alimento != null) {
                System.out.println(alimento + "\n");
            }
        }
    }
    
    public void buscaAlimentoNoBancoDeDados() {
        
        listaDeAlimentos.clear();
        String buscaAlimentoNoBancoDeDados = "select * from alimento;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaAlimentoNoBancoDeDados = connection.prepareStatement(buscaAlimentoNoBancoDeDados);
                ResultSet rs = pstmBuscaAlimentoNoBancoDeDados.executeQuery(buscaAlimentoNoBancoDeDados)) {
            
            while (rs.next()) {
                
                Alimento alimento = new Alimento();
                
                alimento.setId(rs.getInt("id"));
                alimento.setNome(rs.getString("nome"));
                alimento.setCarboidratos(rs.getDouble("carboidratos"));
                alimento.setProteinas(rs.getDouble("proteinas"));
                alimento.setGorduras(rs.getDouble("gorduras"));
                alimento.setCalorias(rs.getDouble("calorias"));
                alimento.setPorcao(rs.getDouble("porcao"));
                alimento.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                alimento.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
                
                listaDeAlimentos.add(alimento);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar alimentos!" + e);
        }
        
    }
    
    public Alimento buscaAlimentoPorNome(String nomeAlimento) {
        for (Alimento alimento : listaDeAlimentos) {

            if (alimento != null && alimento.getNome().equals(nomeAlimento)) {
                return alimento;
            }
        }
        return null;
    }
    
    public Alimento buscaAlimentoPorID(int id)
    {
        for (Alimento alimento : listaDeAlimentos) {
            if(alimento != null && alimento.getId() == id)
            {
                return alimento;
            }
        }
        return null;
    }
    
    public boolean alterarNomeDoAlimentoNoBancoDeDados(Alimento alimento, String novoNome) {

        boolean atualizado = true;

        String atualizaNomeDoAlimentoNoBancoDeDados = "update alimento set nome = ? where nome = ?;";

        String atualizaDataAlteracaoAlimento = "update alimento set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaNomeDoAlimentoNoBancoDeDados = connection.prepareStatement(atualizaNomeDoAlimentoNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoAlimento = connection.prepareStatement(atualizaDataAlteracaoAlimento);) {
                pstmAtualizaNomeDoAlimentoNoBancoDeDados.setString(1, novoNome);
                pstmAtualizaNomeDoAlimentoNoBancoDeDados.setString(2, alimento.getNome());
                pstmAtualizaNomeDoAlimentoNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoAlimento.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoAlimento.setLong(2, alimento.getId());
                pstmAtualizaDataAlteracaoAlimento.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o nome!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }

}
