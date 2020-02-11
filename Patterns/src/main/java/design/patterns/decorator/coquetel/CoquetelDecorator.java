package design.patterns.decorator.coquetel;

/**
 * Created by emanuelvictor on 27/10/15.
 */
public abstract class CoquetelDecorator extends Coquetel {

    Coquetel coquetel;

    //Construtor criad para a classe filha poder ter esse construtor.
    // Assim o spring pode instanciar com o construtor padrão
    public CoquetelDecorator() {
    }

    public CoquetelDecorator(Coquetel coquetel) {
        this.coquetel = coquetel;
    }

    @Override
    public String getNome() {
        return coquetel.getNome() + super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public double getPreco() {
        return coquetel.getPreco() + super.getPreco();
    }

    @Override
    public void setPreco(double preco) {
        super.setPreco(preco);
    }
}
