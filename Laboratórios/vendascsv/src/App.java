import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        String produtos = "produtos.csv";
        String vendas = "vendas.csv";

        //cria a loja;
        Loja loja = new Loja();

        //leitura de produtos;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(produtos), StandardCharsets.UTF_8)) {
            String line;
            br.readLine(); //ler o cabeçalho
            while ((line = br.readLine()) != null) {
                // Processar a linha
                String[] dados = line.split(",");
                //idProduto, nome, preço
                loja.adicionaProduto(dados[0], dados[1], Double.parseDouble(dados[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //leitura de vendas;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(vendas), StandardCharsets.UTF_8)) {
            String line;
            br.readLine(); //ler o cabeçalho
            while ((line = br.readLine()) != null) {
                // Processar a linha
                String[] dados = line.split(",");

                //idVenda, idProduto, quantidade;
                loja.adicionaVenda(dados[0], dados[1], Integer.parseInt(dados[2]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double totalGeral = 0.0;

        for(Venda venda : loja.getVendas().values()){
            venda.imprimeVenda();
            totalGeral += venda.getProduto().getPreco() * venda.getQuantidade();
        }

        System.out.printf("Total geral: R$ %.2f\n", totalGeral);
    }
}