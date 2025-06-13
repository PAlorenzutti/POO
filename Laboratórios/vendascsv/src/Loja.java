import java.util.HashMap;

public class Loja {
    private HashMap<String, Produto> produtos = new HashMap<>();
    private HashMap<String, Venda> vendas = new HashMap<>();

    public void adicionaProduto(String id, String nome, Double preco){
        produtos.put(id, new Produto(id, nome, preco));
    }

    public void adicionaVenda(String idVenda, String idProduto, int quantidade){
        vendas.put(idVenda, new Venda(this.getProduto(idProduto), idVenda, quantidade));
    }

    public Produto getProduto(String idProduto){
        return produtos.get(idProduto);
    }

    public HashMap<String, Venda> getVendas(){
        return new HashMap<>(this.vendas);
    }
}
