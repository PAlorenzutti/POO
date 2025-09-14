public class Main {
    public static void main(String[] args) throws Exception {
        // cria uma instância de eleição com os argumentos de código do município,
        // caminho dos arquivos de consulta_cand e votacao_secao e data de referência,
        // respectivamente;
        Eleicao eleicao = new Eleicao(Integer.parseInt(args[0]), args[1], args[2], args[3]);

        // lê o arquivo consulta_cand;
        eleicao.leArquivoCandidatos();

        // lê o arquivo votacao_secao;
        eleicao.leArquivoVotacao();

        // gera relatório dos vereadores eleitos;
        eleicao.geraRelatorioCandidatosEleitos();

        // gera relatório dos candidatos mais votados;
        eleicao.geraRelatorioCandidatosTotal();

        // gera relatório dos que teriam sido eleitos se a votação fosse majoritária e
        // dos que se beneficiaram da eleição majoritária;
        eleicao.geraRelatorioMajoritariaProporcional();

        // gera relatório geral dos partidos;
        eleicao.geraRelatorioPartidos();

        // gera relatório do primeiro e último candidato de cada partido;
        eleicao.geraRelatorioPrimeiroUltimoPartidos();

        // gera relatório da faixa etária dos candidatos eleitos;
        eleicao.geraRelatorioFaixaEtaria();

        // gera relatório do gênero dos candidatos eleitos;
        eleicao.geraRelatorioGenero();

        // gera relatório do levantamento de votos (total, nominal e de legenda).
        eleicao.geraRelatorioVotos();
    }
}
