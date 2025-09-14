#include <iostream>

#include "partido.hpp"

using namespace std;

Partido::Partido(int numero, const string &sigla) : numero(numero), sigla(sigla), qtdVotosLegenda(0), qtdVotosNominais(0)
{
}

const string &Partido::getSigla() const
{
    return this->sigla;
}

int Partido::getNumero() const
{
    return this->numero;
}

int Partido::getQtdVotosNominais() const
{
    return this->qtdVotosNominais;
}

int Partido::getQtdVotosLegenda() const
{
    return this->qtdVotosLegenda;
}

int Partido::getQtdVotos() const
{
    return this->qtdVotosLegenda + this->qtdVotosNominais;
}

Candidato &Partido::criaCandidato(const int &numeroVotavel, const int &codigoMunicipio, const int &codigoCargo, string &nome, const int &numeroFederacao, const Data &dataNascimento, const int &situacaoCandidatura, const int &genero, const int &foiEleito)
{
    Candidato *c = new Candidato(numeroVotavel, codigoMunicipio, codigoCargo, nome, *this, numeroFederacao, dataNascimento, situacaoCandidatura, genero, foiEleito >= 0 && foiEleito <= 3);

    this->candidatos.insert({numeroVotavel, c});

    return *c;
}

void Partido::adicionaVotosLegendaPartido(const int &qtdVotos)
{
    this->qtdVotosLegenda += qtdVotos;
}

void Partido::adicionaVotosNominaisPartido(const int &qtdVotos)
{
    this->qtdVotosNominais += qtdVotos;
}

const map<int, Candidato *> &Partido::getCandidatos() const
{
    return this->candidatos;
}

const Candidato &Partido::getCandidatoMaisVotado() const
{
    vector<Candidato *> candidatosComVotos;

    for (const auto &pair : this->candidatos)
    {
        candidatosComVotos.push_back(pair.second);
    }

    // Ordena pelos votos dos candidatos (usa operator< de Candidato)
    sort(candidatosComVotos.begin(), candidatosComVotos.end(), CandidatoComparator{});
    // operator< ordena do maior para o menor, então front() é o mais votado
    return *candidatosComVotos.front();
}

const set<Candidato *> Partido::getCandidatosEleitos() const
{
    set<Candidato *> candidatosEleitos;

    for (const auto &pair : this->candidatos)
    {
        if (pair.second->getFoiEleito())
        {
            candidatosEleitos.insert(pair.second);
        }
    }

    return candidatosEleitos;
}

int Partido::getQtdCandidatosEleitos()
{
    return this->getCandidatosEleitos().size();
}

Partido::~Partido()
{
    for (const auto &pair : this->candidatos)
    {
        delete pair.second;
    }
}

void Partido::toString() const
{
    cout << this->sigla << "; "
         << this->numero << "; "
         << this->qtdVotosNominais << "; "
         << this->qtdVotosLegenda << "; "
         << this->getQtdVotos() << endl;

    for (const auto &pair : this->candidatos)
    {
        if (pair.second)
            pair.second->toString();
    }
}