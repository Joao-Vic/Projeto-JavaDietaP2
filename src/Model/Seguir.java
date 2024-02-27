package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Seguir {

    private long id;
    //private static long serial;
    private Pessoa donoDaConta;
    private Pessoa pessoaSeguidaPeloDonoDaConta;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();
    private boolean seguindo;
    
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public Pessoa getDonoDaConta() {
        return donoDaConta;
    }

    public void setDonoDaConta(Pessoa donoDaConta) {
        this.donoDaConta = donoDaConta;
    }

    public Pessoa getPessoaSeguidaPeloDonoDaConta() {
        return pessoaSeguidaPeloDonoDaConta;
    }

    public void setPessoaSeguidaPeloDonoDaConta(Pessoa pessoaSeguidaPeloDonoDaConta) {
        this.pessoaSeguidaPeloDonoDaConta = pessoaSeguidaPeloDonoDaConta;
    }

    public boolean isSeguindo() {
        return seguindo;
    }

    public void setSeguindo(boolean seguindo) {
        this.seguindo = seguindo;
    }

    public Seguir(Pessoa donoDaConta, Pessoa pessoaSeguidaPeloDonoDaConta, boolean seguindo) {
        //this.id = ++Seguir.serial;
        this.donoDaConta = donoDaConta;
        this.pessoaSeguidaPeloDonoDaConta = pessoaSeguidaPeloDonoDaConta;
        this.seguindo = seguindo;
    }

    public Seguir() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.donoDaConta);
        hash = 59 * hash + Objects.hashCode(this.pessoaSeguidaPeloDonoDaConta);
        hash = 59 * hash + (this.seguindo ? 1 : 0);
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
        final Seguir other = (Seguir) obj;
        if (this.seguindo != other.seguindo) {
            return false;
        }
        if (!Objects.equals(this.donoDaConta, other.donoDaConta)) {
            return false;
        }
        if (!Objects.equals(this.pessoaSeguidaPeloDonoDaConta, other.pessoaSeguidaPeloDonoDaConta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        if (this.seguindo == true) {

            return "Quem Solicitou: " + this.donoDaConta.getNome() + "," + " "
                    + "ID da Solicitacao: " + this.id + "," + " "
                    + "Nome de Login: " + this.donoDaConta.getLogin()+ "," + " "
                    + "Seguindo: " + this.pessoaSeguidaPeloDonoDaConta.getNome() + "," + " "
                    + "Nome de Login: " + this.pessoaSeguidaPeloDonoDaConta.getLogin()+ "," + " ";

        } else {
            return "Quem Solicitou: " + this.donoDaConta.getNome() + "," + " "
                    + "ID da Solicitacao: " + this.id + "," + " "
                    + "Nome de Login: " + this.donoDaConta.getLogin()+ "," + " "
                    + "Deseja seguir: " + this.pessoaSeguidaPeloDonoDaConta.getNome() + "," + " "
                    + "Nome de Login: " + this.pessoaSeguidaPeloDonoDaConta.getLogin()+ "," + " ";
        }

    }
    
}
