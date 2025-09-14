#if !defined(CANDIDATO_HPP)
#define CANDIDATO_HPP

#include <string>

#include "data.hpp"

class Partido;

using namespace std;

enum Genero
{
    MASCULINO = 2,
    FEMININO = 4
};

class Candidato
{
private:
    int numeroVotavel;
    int codigoMunicipio;
    int codigoCargo;
    string nome;
    Partido *partido;
    int numeroFederacao;
    Data dataNascimento;
    int situacaoCandidatura;
    Genero genero;
    int qtdVotos;
    bool foiEleito;
    Genero setGenero(int codigoGenero);

public:
    Candidato(
        const int &numeroVotavel,
        const int &codigoMunicipio,
        const int &codigoCargo,
        string &nome,
        Partido &partido,
        const int &numeroFederacao,
        const Data &dataNascimento,
        const int &situacaoCandidatura,
        const int &codigoGenero,
        const bool &foiEleito);

    int getCodigoCargo() const;
    int getCodigoMunicipio() const;
    int getNumeroFederacao() const;
    bool getFoiEleito() const;
    const Partido &getPartido() const;
    const Genero &getGenero() const;
    int getNumeroVotavel() const;
    const string &getNome() const;
    const Data &getDataNascimento() const;
    const int getQtdVotos() const;
    const int getSituacaoCandidatura() const;
    void adicionaVotosCandidato(const int &qtdVotos);
    int getIdade(const Data &dataAtual);
    void toString() const;
};

// Comparator reutilizável para ordenar ponteiros para Candidato
// Ordena por: (1) mais votos primeiro; (2) em empate, mais velho primeiro
struct CandidatoComparator
{
    bool operator()(Candidato *a, Candidato *b) const
    {
        if (a->getQtdVotos() != b->getQtdVotos())
        {
            return a->getQtdVotos() > b->getQtdVotos();
        }
        // Em caso de empate: mais velho primeiro (menor data de nascimento)
        return a->getDataNascimento() < b->getDataNascimento();
    }
};

#endif