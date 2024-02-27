package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Dieta {

    private long id;
    //private static long serial;
    private Pessoa pessoa;
    private AvaliacaoFisica avaliacaofisica;
    private TipodeDieta tipodedieta;
    private String objetivo;
    private double calorias;
    private int nroRefeicoes;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public AvaliacaoFisica getAvaliacaofisica() {
        return avaliacaofisica;
    }

    public void setAvaliacaofisica(AvaliacaoFisica avaliacaofisica) {
        this.avaliacaofisica = avaliacaofisica;
    }

    public TipodeDieta getTipodedieta() {
        return tipodedieta;
    }

    public void setTipodedieta(TipodeDieta tipodedieta) {
        this.tipodedieta = tipodedieta;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public int getNroRefeicoes() {
        return nroRefeicoes;
    }

    public void setNroRefeicoes(int nroRefeicoes) {
        this.nroRefeicoes = nroRefeicoes;
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

    public Dieta(Pessoa pessoa, AvaliacaoFisica avaliacaofisica, TipodeDieta tipodedieta, String objetivo, double calorias, int nroRefeicoes) {
        //this.id = ++Dieta.serial;
        this.pessoa = pessoa;
        this.avaliacaofisica = avaliacaofisica;
        this.tipodedieta = tipodedieta;
        this.objetivo = objetivo;
        this.calorias = calorias;
        this.nroRefeicoes = nroRefeicoes;
    }

    public Dieta() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.pessoa);
        hash = 47 * hash + Objects.hashCode(this.avaliacaofisica);
        hash = 47 * hash + Objects.hashCode(this.tipodedieta);
        hash = 47 * hash + Objects.hashCode(this.objetivo);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.calorias) ^ (Double.doubleToLongBits(this.calorias) >>> 32));
        hash = 47 * hash + this.nroRefeicoes;
        hash = 47 * hash + Objects.hashCode(this.dataCriacao);
        hash = 47 * hash + Objects.hashCode(this.dataModificacao);
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
        final Dieta other = (Dieta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.calorias) != Double.doubleToLongBits(other.calorias)) {
            return false;
        }
        if (this.nroRefeicoes != other.nroRefeicoes) {
            return false;
        }
        if (!Objects.equals(this.objetivo, other.objetivo)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.avaliacaofisica, other.avaliacaofisica)) {
            return false;
        }
        if (!Objects.equals(this.tipodedieta, other.tipodedieta)) {
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
        return "ID = " + id + "\n" +
               "Nome da pessoa = " + pessoa.getNome() + "\n" +
               "TMB da Avaliacao Física = " + avaliacaofisica.getTMB() + "\n" +
               "Tipo de Dieta = " + tipodedieta + "\n" +
               "Objetivo = " + objetivo + "\n" +
               "Calorias = " + calorias + "\n" +
               "Número de Refeições = " + nroRefeicoes + "\n" +
               "Data de Criacao = " + dataCriacaoFormatada + "\n" +
               "Data de Modificacao = " + dataModificacaoFormatada;
    }
    
}
