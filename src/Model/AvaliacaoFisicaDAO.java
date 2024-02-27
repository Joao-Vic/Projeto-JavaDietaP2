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

public class AvaliacaoFisicaDAO {
    

    public AvaliacaoFisicaDAO(PessoaDAO pessoaDAO) {
    }
    
    private List<AvaliacaoFisica>listaDeAvaliacaoFisica = new LinkedList();
    
    public double calcularIMC(double peso, double altura) {

        return peso / (altura / 100 * altura / 100);
    }
    
    public double calcularBodyFatHomens(int idade, double IMC) {

        double bodyFat = (1.20 * IMC) + (0.23 * idade) - 10.8 - 5.4;

        return bodyFat;

    }
    
    public double calcularMassaGordaHomens(double bodyFat, double peso) {


        double massaGorda = (bodyFat / 100) * peso;

        return massaGorda;

    }
    
    public double calcularMassaMagraHomens(double massaGorda, double peso) {

        double massaMagra = 100 - massaGorda;

        return massaMagra;

    }
    
    public double calcularTBMHomens(Pessoa pessoa, double peso, double altura, int idade) {
        switch (pessoa.getTipoUsuario()) {
            case "sedentario":
                return (1.2 * 66) + (13.7 * peso) + (5 * altura) - (6.8 * idade);
            case "levemente ativo":
                return (1.375 * 66) + (13.7 * peso) + (5 * altura) - (6.8 * idade);
            case "moderadamente ativo":
                return (1.55 * 66) + (13.7 * peso) + (5 * altura) - (6.8 * idade);
            case "muito ativo":
                return (1.725 * 66) + (13.7 * peso) + (5 * altura) - (6.8 * idade);
            case "extra ativo":
                return (1.9 * 66) + (13.7 * peso) + (5 * altura) - (6.8 * idade);
            default:
                return 1000;
        }
    }

    public double calcularTBMMulheres(Pessoa pessoa, double peso, double altura, int idade) {
        switch (pessoa.getTipoUsuario()) {
            case "sedentario":
                return (1.2 * 655) + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
            case "levemente ativo":
                return (1.375 * 655) + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
            case "moderadamente ativo":
                return (1.55 * 655) + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
            case "muito ativo":
                return (1.725 * 655) + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
            case "extra ativo":
                return (1.9 * 655) + (9.6 * peso) + (1.8 * altura) - (4.7 * idade);
            default:
                return 1000;
        }
    }
    
    public double calcularBodyFatMulheres(int idade, double IMC) {
        
        double bodyFat = (1.20 * IMC) + (0.23 * idade) - 10.8;

        return bodyFat;
    }
    
    public double calcularMassaGordaMulheres(double bodyFat, double peso) {
        
        double massaGorda = (bodyFat / 100) * peso;

        return massaGorda;
    }
    
    public double calcularMassaMagraMulheres(double massaGorda, double peso) {
        
        double massaMagra = massaGorda - 100;

        return massaMagra;
    }
    
    public boolean insereAvaliacaoFisicaNoBancoDeDados(AvaliacaoFisica avaliacaoFisica) {
        boolean adicionado = true;
        
        String insereAvaliacaoFisicaNoBanco = "insert into avaliacaofisica (pessoa, peso, altura, idade, pescoco, cintura, quadril, IMC, TMB, BF, massaGorda, massaMagra, "
                + "dataCriacao, dataModificacao)\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {
            connection.setAutoCommit(false);
            
            try (PreparedStatement pstminsereAvaliacaoFisicaNoBancoDeDados = connection.prepareStatement(insereAvaliacaoFisicaNoBanco)) {
                pstminsereAvaliacaoFisicaNoBancoDeDados.setLong(1, avaliacaoFisica.getPessoa().getId());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(2, avaliacaoFisica.getPeso());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(3, avaliacaoFisica.getAltura());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setInt(4, avaliacaoFisica.getIdade());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(5, avaliacaoFisica.getPescoco());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(6, avaliacaoFisica.getCintura());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(7, avaliacaoFisica.getQuadril());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(8, avaliacaoFisica.getIMC());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(9, avaliacaoFisica.getTMB());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(10, avaliacaoFisica.getBF());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(11, avaliacaoFisica.getMassaGorda());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDouble(12, avaliacaoFisica.getMassaMagra());
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDate(13, Date.valueOf(avaliacaoFisica.getDataCriacao()));
                pstminsereAvaliacaoFisicaNoBancoDeDados.setDate(14, Date.valueOf(avaliacaoFisica.getDataModificacao()));
                
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
    
    public void exibirAvaliacoesFisicas() {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {
            
            if (avaliacaoFisica != null) {
                System.out.println(avaliacaoFisica + "\n");
            }
        }
    }
    
    public void verMinhaAvaliacaoFisica(Pessoa pessoa) {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {
            
            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa)) {
                System.out.println(avaliacaoFisica + "\n");
            }
        }
    }
    
    public AvaliacaoFisica buscaAvaliacaoFisicaPorPessoa(Pessoa pessoa) {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {

            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa)) {
                return avaliacaoFisica;
            }
        }
        return null;
    }
    
    public void buscaAvaliacaoFisicaNoBancoDeDados(PessoaDAO pessoaDAO) {
        
        listaDeAvaliacaoFisica.clear();
        String buscaAvaliacaoFisicaNoBancoDeDados = "select * from avaliacaofisica;";
        
        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados();
                PreparedStatement pstmBuscaAvaliacaoFisicaNoBancoDeDados = connection.prepareStatement(buscaAvaliacaoFisicaNoBancoDeDados);
                ResultSet rs = pstmBuscaAvaliacaoFisicaNoBancoDeDados.executeQuery(buscaAvaliacaoFisicaNoBancoDeDados)) {
            
            while (rs.next()) {
                
                int idPessoa = rs.getInt("pessoa");
                
                Pessoa pessoa = pessoaDAO.buscaPessoaPorId(idPessoa);
                
                AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
                
                avaliacaoFisica.setId(rs.getInt("id"));
                avaliacaoFisica.setPeso(rs.getDouble("peso"));
                avaliacaoFisica.setAltura(rs.getDouble("altura"));
                avaliacaoFisica.setIdade(rs.getInt("idade"));
                avaliacaoFisica.setPescoco(rs.getDouble("pescoco"));
                avaliacaoFisica.setCintura(rs.getDouble("cintura"));
                avaliacaoFisica.setQuadril(rs.getDouble("quadril"));
                avaliacaoFisica.setIMC(rs.getDouble("imc"));
                avaliacaoFisica.setTMB(rs.getDouble("tmb"));
                avaliacaoFisica.setBF(rs.getDouble("bf"));
                avaliacaoFisica.setMassaGorda(rs.getDouble("massagorda"));
                avaliacaoFisica.setMassaMagra(rs.getDouble("massamagra"));
                avaliacaoFisica.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime().toLocalDate());
                avaliacaoFisica.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime().toLocalDate());
           
                avaliacaoFisica.setPessoa(pessoa);
                
                listaDeAvaliacaoFisica.add(avaliacaoFisica);
            }
                    
            
        } catch (SQLException e) {
            
            System.out.println("\nErro: Não foi possível buscar avaliação física!" + e);
        }
        
    }
    
    public AvaliacaoFisica buscaAvaliacaoFisicaPorNome(String nomePessoa) {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {

            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().getNome().equals(nomePessoa)) {
                return avaliacaoFisica;
            }
        }
        return null;
    }
    
    public AvaliacaoFisica buscaAvaliacaoFisicaPorID(int idAvaliacaoFisica) {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {

            if (avaliacaoFisica != null && avaliacaoFisica.getId() == idAvaliacaoFisica) {
                return avaliacaoFisica;
            }
        }
        return null;
    }
    
    public boolean alterarPesodaPessoaNoBancoDeDados(AvaliacaoFisica avaliacaoFisica, double novoPesoAvaliacaoFisica) {

        boolean atualizado = true;

        String atualizaPesodaPessoaNoBancoDeDados = "update avaliacaofisica set peso = ? where id = ?;";

        String atualizaDataAlteracaoAvaliacaoFisica = "update avaliacaofisica set dataModificacao = ? where id = ?";

        try (Connection connection = new ConexaoBancoDeDados().ConectaBancoDeDados()) {

            try (PreparedStatement pstmAtualizaPesodaPessoaNoBancoDeDados = connection.prepareStatement(atualizaPesodaPessoaNoBancoDeDados);
                    PreparedStatement pstmAtualizaDataAlteracaoAvaliacaoFisica = connection.prepareStatement(atualizaDataAlteracaoAvaliacaoFisica);) {
                pstmAtualizaPesodaPessoaNoBancoDeDados.setDouble(1, novoPesoAvaliacaoFisica);
                pstmAtualizaPesodaPessoaNoBancoDeDados.setLong(2, avaliacaoFisica.getId());
                pstmAtualizaPesodaPessoaNoBancoDeDados.execute();
                
                pstmAtualizaDataAlteracaoAvaliacaoFisica.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                pstmAtualizaDataAlteracaoAvaliacaoFisica.setLong(2, avaliacaoFisica.getId());
                pstmAtualizaDataAlteracaoAvaliacaoFisica.execute();
            } catch (SQLException e) {

                atualizado = false;
                System.out.println("\nErro: Não foi possível atualizar o nome!" + e);

            }

        } catch (Exception e) {
        }

        return atualizado != false;
    }
    
    public void verMeuRelatorio(Pessoa pessoa) {
        for (AvaliacaoFisica avaliacaoFisica : listaDeAvaliacaoFisica) {
            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa) && avaliacaoFisica.getPessoa().getSexo().equals("Masculino") || avaliacaoFisica != null && avaliacaoFisica.getPessoa().getSexo().equals("masculino")) {
                if (avaliacaoFisica.getBF() < 11 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() < 12 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() < 14 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() < 15 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem o fisico de um atleta, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 11 && avaliacaoFisica.getBF() <= 13 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 12 && avaliacaoFisica.getBF() <= 14 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 14 && avaliacaoFisica.getBF() <= 16 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 15 && avaliacaoFisica.getBF() <= 17 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado bom, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 14 && avaliacaoFisica.getBF() <= 20 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 15 && avaliacaoFisica.getBF() <= 21 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 17 && avaliacaoFisica.getBF() <= 23 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 18 && avaliacaoFisica.getBF() <= 24 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado normal, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 21 && avaliacaoFisica.getBF() <= 23 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 22 && avaliacaoFisica.getBF() <= 24 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 24 && avaliacaoFisica.getBF() <= 26 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 25 && avaliacaoFisica.getBF() <= 27 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado elevado, procure um especialista por favor!");
                } else if (avaliacaoFisica.getBF() > 23 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() > 24 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() > 26 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() > 27 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado muito elevado, procure um especialista com urgencia por favor!");
                }

            } else if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa) && avaliacaoFisica.getPessoa().getSexo().equals("Feminino") || avaliacaoFisica != null && avaliacaoFisica.getPessoa().getSexo().equals("feminino")) {
                if (avaliacaoFisica.getBF() < 16 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() < 17 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() < 18 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() < 19 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem o fisico de um atleta, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 16 && avaliacaoFisica.getBF() <= 19 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 17 && avaliacaoFisica.getBF() <= 20 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 18 && avaliacaoFisica.getBF() <= 21 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 19 && avaliacaoFisica.getBF() <= 22 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado bom, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 20 && avaliacaoFisica.getBF() <= 28 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 21 && avaliacaoFisica.getBF() <= 29 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 22 && avaliacaoFisica.getBF() <= 30 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 23 && avaliacaoFisica.getBF() <= 31 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado normal, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 29 && avaliacaoFisica.getBF() <= 31 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 30 && avaliacaoFisica.getBF() <= 32 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 31 && avaliacaoFisica.getBF() <= 33 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 32 && avaliacaoFisica.getBF() <= 34 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado elevado, procure um especialista por favor!");
                } else if (avaliacaoFisica.getBF() > 31 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() > 32 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() > 33 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() > 34 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado muito elevado, procure um especialista com urgencia por favor!");
                }

            }
        }
    }

}
