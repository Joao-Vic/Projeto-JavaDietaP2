package Model;

import java.time.LocalDate;
import java.util.Objects;

public class Mensagem {

    private long id;
    //private static long serial;
    private Pessoa contaDeOrigem;
    private Pessoa contaDeDestino;
    private String mensagem;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataModificacao = LocalDate.now();

    public Pessoa getContaDeOrigem() {
        return contaDeOrigem;
    }

    public void setContaDeOrigem(Pessoa contaDeOrigem) {
        this.contaDeOrigem = contaDeOrigem;
    }

    public Pessoa getContaDeDestino() {
        return contaDeDestino;
    }

    public void setContaDeDestino(Pessoa contaDeDestino) {
        this.contaDeDestino = contaDeDestino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

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

    public Mensagem(Pessoa contaDeOrigem, Pessoa contaDeDestino, String mensagem) {
        //this.id = ++Mensagem.serial;
        this.contaDeOrigem = contaDeOrigem;
        this.contaDeDestino = contaDeDestino;
        this.mensagem = mensagem;
    }

    public Mensagem() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.contaDeOrigem);
        hash = 41 * hash + Objects.hashCode(this.contaDeDestino);
        hash = 41 * hash + Objects.hashCode(this.mensagem);
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
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        if (!Objects.equals(this.contaDeOrigem, other.contaDeOrigem)) {
            return false;
        }
        if (!Objects.equals(this.contaDeDestino, other.contaDeDestino)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "De: " + this.contaDeOrigem.getNome() + " " + "||" + " " + "Para: " + this.contaDeDestino.getNome() + "\n"
                + "Mensagem: " + "\n" + this.mensagem;
    }

}
