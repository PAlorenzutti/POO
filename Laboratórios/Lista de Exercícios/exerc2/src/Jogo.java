public class Jogo extends Product {
    int idadeMinima;

    public Jogo(String name, double preco, int idadeMinima){
        super(name, preco);
        this.idadeMinima = idadeMinima;
    }

    @Override
    public String toString(){
        return super.toString() + " Idade mínima: " + this.idadeMinima + "anos";
    }
}
