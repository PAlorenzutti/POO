public abstract class Product{
    private String name;
    private double preco;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Product(String name, double preco) {
        this.name = name;
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "Nome: " + this.name + " Preço: " + this.preco;
    }
}