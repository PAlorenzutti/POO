public class Venda {
    private String idVenda;
    private int quantidade;
    private Produto produto;

    public Venda(Produto produto, String idVenda, int quantidade) {
        this.idVenda = idVenda;
        this.quantidade = quantidade;
        this.produto = produto;
        produto.adicionaVendaProduto(this);
    }

    public String getIdVenda() {
        return idVenda;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    

    public void imprimeVenda(){
        System.out.println("Venda " + this.idVenda + ": " + this.quantidade + "x " + this.produto.getNome() + " - Total: R$ " + String.format("%.2f", this.produto.getPreco() * this.quantidade));
    }

}
