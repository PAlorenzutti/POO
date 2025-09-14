#include "eleicao.hpp"

#include <iostream>

int main(int argc, char *argv[])
{
    // Cria uma instância de eleição com os argumentos de código do município,
    // caminho dos arquivos de consulta_cand e votacao_secao e data de referência,
    // respectivamente;
    Eleicao eleicao(stoi(argv[1]), argv[2], argv[3], argv[4]);

    // Lê o arquivo consulta_cand;
    eleicao.leArquivoCandidatos();

    // Lê o arquivo votacao_secao
    eleicao.leArquivoVotacao();

    // Imprime relatório de candidatos eleitos;
    eleicao.geraRelatorioCandidatosEleitos();

    // Imprime relatório de candidatos totais;
    eleicao.geraRelatorioCandidatosTotal();

    // Imprime relatório de eleição majoritária proporcional
    eleicao.geraRelatorioMajoritariaProporcional();

    // Imprime relatório de partidos
    eleicao.geraRelatorioPartidos();

    // Imprime primeiro e último de cada partido
    eleicao.geraRelatorioPrimeiroUltimoPartidos();

    // Imprime relatório de faixa etária
    eleicao.geraRelatorioFaixaEtaria();

    // Imprime relatório de gênero;
    eleicao.geraRelatorioGenero();

    // Imprime relatório de votos;
    eleicao.geraRelatorioVotos();

    return 0;
}