
#if !defined(ELEICAO_HPP)
#define ELEICAO_HPP

#include <string>
#include <map>

#include "data.hpp"
#include "partido.hpp"

using namespace std;

class Eleicao
{
private:
    string eleicaoPath;
    string candidatosPath;
    int codigoCidade;
    Data dataAtual;
    map<int, Partido *> partidos;

public:
    Eleicao(
        int codigoCidade,
        const string &candidatosPath,
        const string &eleicaoPath,
        const string &dataAtual);

    void leArquivoCandidatos();
    void leArquivoVotacao();
    map<int, Candidato *> getCandidatos() const;
    int getQtdCandidatosEleitos() const;
    void geraRelatorioCandidatosEleitos() const;
    void geraRelatorioCandidatosTotal() const;
    void geraRelatorioMajoritariaProporcional() const;
    void geraRelatorioPartidos() const;
    void geraRelatorioPrimeiroUltimoPartidos() const;
    void geraRelatorioFaixaEtaria() const;
    void geraRelatorioGenero() const;
    void geraRelatorioVotos() const;
    ~Eleicao();
    void toString() const;
};

#endif