package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AvaliacaoFisica {

    private long id;
    //private static long serial;
    private Pessoa pessoa;
    private double peso;
    private double altura;
    private int idade;
    private double pescoco;
    private double cintura;
    private double quadril;
    private double IMC;
    private double TMB;
    private double BF;
    private double massaGorda;
    private double massaMagra;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPescoco() {
        return pescoco;
    }

    public void setPescoco(double pescoco) {
        this.pescoco = pescoco;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getTMB() {
        return TMB;
    }

    public void setTMB(double TMB) {
        this.TMB = TMB;
    }

    public double getBF() {
        return BF;
    }

    public void setBF(double BF) {
        this.BF = BF;
    }

    public double getMassaGorda() {
        return massaGorda;
    }

    public void setMassaGorda(double massaGorda) {
        this.massaGorda = massaGorda;
    }

    public double getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(double massaMagra) {
        this.massaMagra = massaMagra;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public AvaliacaoFisica(Pessoa pessoa, double peso, double altura, int idade, 
            double pescoco, double cintura, double quadril, double IMC, 
            double TMB, double BF, double massaGorda, double massaMagra) {
        this.pessoa = pessoa;
        this.peso = peso;
        this.altura = altura;
        this.idade = idade;
        this.pescoco = pescoco;
        this.cintura = cintura;
        this.quadril = quadril;
        this.IMC = IMC;
        this.TMB = TMB;
        this.BF = BF;
        this.massaGorda = massaGorda;
        this.massaMagra = massaMagra;
    }

    public AvaliacaoFisica() {
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.pessoa);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.altura) ^ (Double.doubleToLongBits(this.altura) >>> 32));
        hash = 67 * hash + this.idade;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.pescoco) ^ (Double.doubleToLongBits(this.pescoco) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.cintura) ^ (Double.doubleToLongBits(this.cintura) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.quadril) ^ (Double.doubleToLongBits(this.quadril) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.IMC) ^ (Double.doubleToLongBits(this.IMC) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.TMB) ^ (Double.doubleToLongBits(this.TMB) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.BF) ^ (Double.doubleToLongBits(this.BF) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.massaGorda) ^ (Double.doubleToLongBits(this.massaGorda) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.massaMagra) ^ (Double.doubleToLongBits(this.massaMagra) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.dataCriacao);
        hash = 67 * hash + Objects.hashCode(this.dataModificacao);
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
        final AvaliacaoFisica other = (AvaliacaoFisica) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (Double.doubleToLongBits(this.altura) != Double.doubleToLongBits(other.altura)) {
            return false;
        }
        if (this.idade != other.idade) {
            return false;
        }
        if (Double.doubleToLongBits(this.pescoco) != Double.doubleToLongBits(other.pescoco)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cintura) != Double.doubleToLongBits(other.cintura)) {
            return false;
        }
        if (Double.doubleToLongBits(this.quadril) != Double.doubleToLongBits(other.quadril)) {
            return false;
        }
        if (Double.doubleToLongBits(this.IMC) != Double.doubleToLongBits(other.IMC)) {
            return false;
        }
        if (Double.doubleToLongBits(this.TMB) != Double.doubleToLongBits(other.TMB)) {
            return false;
        }
        if (Double.doubleToLongBits(this.BF) != Double.doubleToLongBits(other.BF)) {
            return false;
        }
        if (Double.doubleToLongBits(this.massaGorda) != Double.doubleToLongBits(other.massaGorda)) {
            return false;
        }
        if (Double.doubleToLongBits(this.massaMagra) != Double.doubleToLongBits(other.massaMagra)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
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
        return  "--------------------------------------- \n"+
                " ID da Avaliacao Fisica = " + id + "\n"
                + pessoa + "\n"
                + " Peso = " + peso + "\n"
                + " Altura = " + altura + "\n"
                + " Idade = " + idade + "\n"
                + " Pescoco = " + pescoco + "\n"
                + " Cintura = " + cintura + "\n"
                + " Quadril = " + quadril + "\n"
                + " IMC = " + IMC + "\n"
                + " TMB = " + TMB + "\n"
                + " BF = " + BF + "%\n"
                + " Massa Gorda = " + massaGorda + "%\n"
                + " Massa Magra = " + massaMagra + "%\n"
                + " Data de Criacao = " + dataCriacaoFormatada + "\n"
                + " Data de Modificacao = " + dataModificacaoFormatada + "\n";
    }

}
