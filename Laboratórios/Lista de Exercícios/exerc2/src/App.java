public class App {
    public static void main(String[] args) throws Exception {
        Product[] produtos = new Product[5];

        produtos[0] = new Livro("Harry Potter", 99.99, "J.K Rowling");
        produtos[1] = new Jogo("Coup", 50.00, 10);
        produtos[2] = new Livro("Deuses Americanos", 99.99, "Pedro");
        produtos[3] = new Jogo("Minecraft",40.00, 16);
        produtos[4] = new Livro("Deuses Americanos",120.99, "Claudio");

        for(Product produto : produtos){
            System.out.println(produto.toString());
        }
    }
}
