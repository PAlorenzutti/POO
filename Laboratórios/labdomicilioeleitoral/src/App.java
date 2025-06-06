public class App {
    public static void main(String[] args) throws Exception {
        // Criação das UFs
        UF es = new UF("Espírito Santo", "ES");
        UF mg = new UF("Minas Gerais", "MG");

        // Criação dos Municípios
        Municipio vix = es.criarMunicipio("Vitória");
        Municipio vv = es.criarMunicipio("Vila Velha");
        Municipio bh = mg.criarMunicipio("Belo Horizonte");

        // Criação dos Eleitores
        Pessoa ana = new Pessoa("Ana", "111", vix);
        Pessoa bruno = new Pessoa("Bruno", "222", vv);
        Pessoa carla = new Pessoa("Carla", "333", bh);
        Pessoa joao = new Pessoa("João", "444", bh);

        // Adiciona as UFs em uma lista
        java.util.List<UF> listaDeUFs = new java.util.ArrayList<>();
        listaDeUFs.add(es);
        listaDeUFs.add(mg);

        // Exporta para DOT
        DotExporter.exportToDot(listaDeUFs, "saida-antes.dot");

        // João muda para Vitória
        joao.setDomicilioEleitoral(vix);

        // Exporta para DOT
        DotExporter.exportToDot(listaDeUFs, "saida-depois-vix.dot");

        // Depois para Vila-Velha
        vv.adicionaEleitor(joao);

        // Exporta para DOT
        DotExporter.exportToDot(listaDeUFs, "saida-depois-vv.dot");
    }
}
