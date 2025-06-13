import java.util.HashMap;

public class Produto {
    private String id;
    private String nome;
    private Double preco;
    private HashMap<String, Venda> vendas = new HashMap<>();

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void adicionaVendaProduto(Venda venda){
        vendas.put(venda.getIdVenda(), venda);
    }

    public Produto(String id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}
