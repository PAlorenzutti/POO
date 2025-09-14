#if !defined(PARTIDO_HPP)
#define PARTIDO_HPP

#include <map>
#include <string>
#include <set>
#include <vector>
#include <algorithm>

#include "data.hpp"
#include "candidato.hpp"

using namespace std;

class Partido
{
private:
    int numero;
    string sigla;
    int qtdVotosLegenda;
    int qtdVotosNominais;
    map<int, Candidato *> candidatos;

public:
    Partido(int numero, const string &sigla);
    const string &getSigla() const;
    int getNumero() const;
    int getQtdVotosLegenda() const;
    int getQtdVotosNominais() const;
    int getQtdVotos() const;

    Candidato &criaCandidato(
        const int &numeroVotavel,
        const int &codigoMunicipio,
        const int &codigoCargo,
        string &nome,
        const int &numeroFederacao,
        const Data &dataNascimento,
        const int &situacaoCandidatura,
        const int &genero,
        const int &foiEleito);

    void adicionaVotosNominaisPartido(const int &qtdVotos);
    void adicionaVotosLegendaPartido(const int &qtdVotos);
    const map<int, Candidato *> &getCandidatos() const;
    const Candidato &getCandidatoMaisVotado() const;
    const set<Candidato *> getCandidatosEleitos() const;
    int getQtdCandidatosEleitos();
    ~Partido();
    void toString() const;
};

// Comparator para ponteiros de Partido: ordena por quantidade de votos de cada partido e, em empate, pelo número do partido
struct PartidoComparator
{
    bool operator()(const Partido *p1, const Partido *p2) const
    {
        if (p1->getQtdVotos() != p2->getQtdVotos())
        {
            return p1->getQtdVotos() > p2->getQtdVotos();
        }

        // Empate: ordenar por número do partido em ordem crescente
        return p1->getNumero() < p2->getNumero();
    }
};

// Comparator para ponteiros de Partido: ordena por votos do mais votado (desc) e, em empate, pelo número do partido (asc)
struct PartidoCandidatoComparator
{
    bool operator()(const Partido *p1, const Partido *p2) const
    {
        int votos1 = p1->getCandidatoMaisVotado().getQtdVotos();
        int votos2 = p2->getCandidatoMaisVotado().getQtdVotos();
        if (votos1 != votos2)
        {
            return votos1 > votos2; // decrescente
        }

        // Empate: ordenar por número do partido em ordem crescente
        return p1->getNumero() < p2->getNumero(); // crescente
    }
};

#endif