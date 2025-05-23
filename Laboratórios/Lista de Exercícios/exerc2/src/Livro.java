public class Livro extends Product {
    String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    Livro(String name, double preco, String autor){
        super(name, preco);
        this.autor = autor;
    }

    @Override
    public String toString(){
        return super.toString() + " Autor: " + this.autor;
    }
}
