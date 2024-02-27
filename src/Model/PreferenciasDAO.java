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

public class PreferenciasDAO {

    public PreferenciasDAO(PessoaDAO pessoaDAO, AlimentoDAO alimentoDAO) {

    }
    
    private List<Preferencias>listaDePreferencias = new LinkedList();
    
    public void exibirPreferencias() {
        for (Preferencias preferencias : listaDePreferencias) {

            if (preferencias != null) {
                System.out.println(preferencias + "\n");
            }
        }
    }
    
    public void verMinhasPreferencias(Pessoa pessoa) {
        boolean encontrouPreferencias = false;

        for (Preferencias preferencias : listaDePreferencias) {
            if (preferencias != null && preferencias.getPessoa().equals(pessoa)) {
                System.out.println(preferencias);
                encontrouPreferencias = true;
            }
        }
        if (!encontrouPreferencias) {
            System.out.println("Você ainda não possui nenhuma preferência!");
        }
    }
    
    public Preferencias buscaPreferenciaPorNomeDaPessoa(String nomePessoa) {
        for (Preferencias preferencias : listaDePreferencias) {

            if (preferencias != null && preferencias.getPessoa().getNome().equals(nomePessoa)) {
                return preferencias;
            }
        }
        return null;
    }
    
    public boolean alterarIDPreferencia(int id, int novoID) {
        for (Preferencias preferencias : listaDePreferencias) {
            if (preferencias != null && preferencias.getId() == id) {
                preferencias.setId(novoID);
                return true;
            }

        }
        return false;

    }   
    
    public boolean inserePreferenciasNoBancoDeDados(Preferencias preferencias) {
        boolean adicionado = true;
        
        String insereAvaliacaoFisicaNoBanco = "insert into preferencias (pessoa, alimento, "
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereAvaliacaoFisicaNoBancoDeDados = connection.prepareStatement(insereAvaliacaoFisicaNoBanco)) {
                pstminsereAvaliacaoFisicaNoBancoDeDados.setLong(1, preferencias.getPessoa().getId());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(2, preferencias.getAlimento().getId());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDate(3, Date.valueOf(preferencias.getDataCriacao()));
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDate(4, Date.valueOf(preferencias.getDataModificacao()));
                
                pstminsereAvaliacaoFisicaNoBancoDeDados.execute();
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
    
    public void buscaPreferenciasNoBancoDeDados(PessoaDAO pessoaDAO, AlimentoDAO alimentoDAO) {
        
        listaDePreferencias.clear();
        String buscaPreferenciasNoBancoDeDados = "select * from preferencias;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaPreferenciasNoBancoDeDados = connection.prepareStatement(buscaPreferenciasNoBancoDeDados);
                ResultSet rs = pstmBuscaPreferenciasNoBancoDeDados.executeQuery(buscaPreferenciasNoBancoDeDados)) {
            
            while (rs.next()) {
                
                int idPessoa = rs.getInt("pessoa");
                int idAlimento = rs.getInt("alimento");
                
                Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);
                Alimento alimento = alimentoDAO.buscaAlimentoPorID(idAlimento);
                
                Preferencias preferencias = new Preferencias();
                
                preferencias.setId(rs.getInt("id"));
                preferencias.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                preferencias.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
           
                preferencias.setPessoa(pessoa);
                preferencias.setAlimento(alimento);
                
                listaDePreferencias.add(preferencias);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar preferencias!" + e);
        }
        
    }
    
     public boolean alterarAlimentoDaPreferenciaNoBancoDeDados(Preferencias preferencias, Alimento alimento) {

        boolean atualizado = true;

        String atualizaAlimentoDaPreferenciaNoBancoDeDados = "update preferencias set alimento = ? where id = ?;";

        String atualizaDataAlteracaoPreferencia = "update preferencias set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaAlimentoDaPreferenciaNoBancoDeDados = connection.prepareStatement(atualizaAlimentoDaPreferenciaNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoPreferencia = connection.prepareStatement(atualizaDataAlteracaoPreferencia);) {
                pstmAtualizaAlimentoDaPreferenciaNoBancoDeDados.setLong(1, alimento.getId());
                pstmAtualizaAlimentoDaPreferenciaNoBancoDeDados.setLong(2, preferencias.getId());
                pstmAtualizaAlimentoDaPreferenciaNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoPreferencia.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoPreferencia.setLong(2, preferencias.getId());
                pstmAtualizaDataAlteracaoPreferencia.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o alimento da preferencia!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }
     
     public List<Preferencias> buscaPreferenciasPorPessoa(Pessoa pessoa, Refeicoes refeicoes)
    {
        System.out.println(refeicoes.getCalorias());
        double quantidadeDeCalorias = 0;
        double caloriasAlimento = 0;
        
        List<Preferencias>listaDePreferenciasDaPessoa = new LinkedList();
        
        for (Preferencias listaDePreferencia : listaDePreferencias) {
            
            if(listaDePreferencia.getPessoa() != null && listaDePreferencia.getPessoa().equals(pessoa))
            {
                quantidadeDeCalorias += listaDePreferencia.getAlimento().getCalorias();
                if(quantidadeDeCalorias <= refeicoes.getCalorias())
                {
                    caloriasAlimento += listaDePreferencia.getAlimento().getCalorias();
                    listaDePreferenciasDaPessoa.add(listaDePreferencia);
                }
            }
            
        }
        System.out.println("\n" + "A quantidade de calorias dos alimentos somadas foi de: " + caloriasAlimento);
        return listaDePreferenciasDaPessoa;
    }
     
    
    
    /*public boolean gerarDietaComPreferencia(Refeicoes refeicao, AlimentoRefeicoesDAO alimentoRefeicoesDAO) {
        double quantidadeCalorias = 0;

        for (int i = 0; i < vetorPreferencias.length; i++) {

            if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())) {
                
                quantidadeCalorias += vetorPreferencias[i].getAlimento().getCalorias();

                if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())
                        && quantidadeCalorias <= refeicao.getCalorias()) {
                    

                    AlimentoRefeicoes alimentoRefeicoes = new AlimentoRefeicoes(refeicao, vetorPreferencias[i].getAlimento(),
                            vetorPreferencias[i].getAlimento().getPorcao(), vetorPreferencias[i].getAlimento().getProteinas(),
                            vetorPreferencias[i].getAlimento().getGorduras(), vetorPreferencias[i].getAlimento().getCalorias());

                    System.out.println(alimentoRefeicoes + "\n");

                    alimentoRefeicoesDAO.inserirAlimentoRefeicao(alimentoRefeicoes);

                    System.out.println("\nCalorias somada = " + quantidadeCalorias);

                }

            } else {

                return true;

            }

        }

        if (quantidadeCalorias <= refeicao.getCalorias()) {
            return true;
        }
        return false;
    }
    
    public List<Preferencias> buscaPreferenciasPorPessoa(Pessoa pessoa)
    {
        private List<Preferencias>listaDePreferencias = new LinkedList();
    }*/

    /*private Preferencias vetorPreferencias[] = new Preferencias[50];

    public boolean inserirPreferencias(Preferencias preferencias) {
        for (int i = 0; i < vetorPreferencias.length; i++) {

            if (vetorPreferencias[i] == null) {
                vetorPreferencias[i] = preferencias;

                return true;
            }
        }
        return false;
    }

    public Preferencias buscaPreferenciaPorNomeDaPessoa(String nomePessoa) {
        for (Preferencias preferencias : vetorPreferencias) {

            if (preferencias != null && preferencias.getPessoa().getNome().equals(nomePessoa)) {
                return preferencias;
            }
        }
        return null;
    }

    public void verMinhasPreferencias(Pessoa pessoa) {
        boolean encontrouPreferencias = false;

        for (Preferencias preferencias : vetorPreferencias) {
            if (preferencias != null && preferencias.getPessoa().equals(pessoa)) {
                System.out.println(preferencias);
                encontrouPreferencias = true;
            }
        }
        if (!encontrouPreferencias) {
            System.out.println("Você ainda não possui nenhuma preferência!");
        }
    }

    public boolean alterarIDPreferencia(int id, int novoID) {
        for (Preferencias preferencias : vetorPreferencias) {
            if (preferencias != null && preferencias.getId() == id) {
                preferencias.setId(novoID);
                return true;
            }

        }
        return false;

    }

    public boolean removerPreferenciaPorID(int ID) {
        for (int i = 0; i < vetorPreferencias.length; i++) {
            if (vetorPreferencias[i] != null && vetorPreferencias[i].getId() == ID) {
                vetorPreferencias[i] = null;
                return true;
            }
        }
        return false;

    }

    public boolean gerarDietaComPreferencia(Refeicoes refeicao, AlimentoRefeicoesDAO alimentoRefeicoesDAO) {
        double quantidadeCalorias = 0;

        for (int i = 0; i < vetorPreferencias.length; i++) {

            if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())) {
                
                quantidadeCalorias += vetorPreferencias[i].getAlimento().getCalorias();

                if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())
                        && quantidadeCalorias <= refeicao.getCalorias()) {

                    AlimentoRefeicoes alimentoRefeicoes = new AlimentoRefeicoes(refeicao, vetorPreferencias[i].getAlimento(),
                            vetorPreferencias[i].getAlimento().getPorcao(), vetorPreferencias[i].getAlimento().getProteinas(),
                            vetorPreferencias[i].getAlimento().getGorduras(), vetorPreferencias[i].getAlimento().getCalorias());

                    System.out.println(alimentoRefeicoes + "\n");

                    alimentoRefeicoesDAO.inserirAlimentoRefeicao(alimentoRefeicoes);

                    System.out.println("\nCalorias somada = " + quantidadeCalorias);

                }

            } else {

                return true;

            }

        }

        if (quantidadeCalorias <= refeicao.getCalorias()) {
            return true;
        }
        return false;
    }*/

}
