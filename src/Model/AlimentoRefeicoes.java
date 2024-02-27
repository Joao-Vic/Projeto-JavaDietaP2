package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AlimentoRefeicoes {

    private long id;
    //private static long serial;
    private Refeicoes refeicao;
    private Alimento alimento;
    private double porcao;
    private double proteina;
    private double gordura;
    private double calorias;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();

    public Refeicoes getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicoes refeicao) {
        this.refeicao = refeicao;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public double getPorcao() {
        return porcao;
    }

    public void setPorcao(double porcao) {
        this.porcao = porcao;
    }

    public double getProteina() {
        return proteina;
    }

    public void setProteina(double proteina) {
        this.proteina = proteina;
    }

    public double getGordura() {
        return gordura;
    }

    public void setGordura(double gordura) {
        this.gordura = gordura;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public AlimentoRefeicoes(Refeicoes refeicao, Alimento alimento, double porcao, double proteina, double gordura, double calorias) {

        //this.id = ++AlimentoRefeicoes.serial;
        this.refeicao = refeicao;
        this.alimento = alimento;
        this.porcao = porcao;
        this.proteina = proteina;
        this.gordura = gordura;
        this.calorias = calorias;
    }
    
    public AlimentoRefeicoes() {

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.refeicao);
        hash = 29 * hash + Objects.hashCode(this.alimento);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.porcao) ^ (Double.doubleToLongBits(this.porcao) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.proteina) ^ (Double.doubleToLongBits(this.proteina) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.gordura) ^ (Double.doubleToLongBits(this.gordura) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.calorias) ^ (Double.doubleToLongBits(this.calorias) >>> 32));
        hash = 29 * hash + Objects.hashCode(this.dataCriacao);
        hash = 29 * hash + Objects.hashCode(this.dataModificacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlimentoRefeicoes other = (AlimentoRefeicoes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.porcao) != Double.doubleToLongBits(other.porcao)) {
            return false;
        }
        if (Double.doubleToLongBits(this.proteina) != Double.doubleToLongBits(other.proteina)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gordura) != Double.doubleToLongBits(other.gordura)) {
            return false;
        }
        if (Double.doubleToLongBits(this.calorias) != Double.doubleToLongBits(other.calorias)) {
            return false;
        }
        if (!Objects.equals(this.refeicao, other.refeicao)) {
            return false;
        }
        if (!Objects.equals(this.alimento, other.alimento)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        if (!Objects.equals(this.dataModificacao, other.dataModificacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataCriacaoFormatada = dataCriacao.format(formatter);
        String dataModificacaoFormatada = dataModificacao.format(formatter);
        return "ID do Alimento da Dieta Personalizada = " + id + "\n" +
               "Refeicao = " + refeicao.getNomedarefeicao() + "\n" +
               "Alimento = " + alimento.getNome() + "\n" +
               "Porcao = " + porcao + "\n" +
               "Proteina = " + proteina + "\n" +
               "Gordura = " + gordura + "\n" +
               "Calorias = " + calorias + "\n" +
               "Data da Criacao = " + dataCriacaoFormatada + "\n" +
               "Data de Modificacao = " + dataModificacaoFormatada;
    }
    
    

}
