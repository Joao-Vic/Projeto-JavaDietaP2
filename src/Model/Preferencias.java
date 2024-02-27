
package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Preferencias {
    private long id;
    private Pessoa pessoa;
    private Alimento alimento;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Preferencias(Pessoa pessoa, Alimento alimento) {
        //this.id = ++Preferencias.serial;
        this.pessoa = pessoa;
        this.alimento = alimento;
    }

    public Preferencias() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.pessoa);
        hash = 17 * hash + Objects.hashCode(this.alimento);
        hash = 17 * hash + Objects.hashCode(this.dataCriacao);
        hash = 17 * hash + Objects.hashCode(this.dataModificacao);
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
        final Preferencias other = (Preferencias) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
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
        return "\n\n" + "ID da Preferencia = " + id + "\n" + 
               "Nome da Pessoa = " + pessoa.getNome() + "\n" + 
               "Nome do Alimento = " + alimento.getNome() + "\n" + 
               "Calorias do Alimento = " + alimento.getCalorias() + "\n" +
               "Data de Criação = " + dataCriacao + "\n" + 
               "Data de Modificação = " + dataModificacao;
    }
    
    
}
