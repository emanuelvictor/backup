package design.patterns.decorator.coquetel;

/**
 * Created by emanuelvictor on 27/10/15.
 */
public abstract class Coquetel {

    String nome;
    double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
