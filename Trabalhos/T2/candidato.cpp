#include <iostream>

#include "candidato.hpp"

#include "partido.hpp"

using namespace std;

Genero Candidato::setGenero(int codigoGenero)
{
    switch (codigoGenero)
    {
    case MASCULINO:
        return MASCULINO;
    case FEMININO:
        return FEMININO;
    default:
        return MASCULINO;
    }
}

Candidato::Candidato(
    const int &numeroVotavel,
    const int &codigoMunicipio,
    const int &codigoCargo,
    string &nome,
    Partido &partido,
    const int &numeroFederacao,
    const Data &dataNascimento,
    const int &situacaoCandidatura,
    const int &codigoGenero,
    const bool &foiEleito)
    : numeroVotavel(numeroVotavel),
      codigoMunicipio(codigoMunicipio),
      codigoCargo(codigoCargo),
      nome(nome),
      partido(&partido),
      numeroFederacao(numeroFederacao),
      dataNascimento(dataNascimento),
      situacaoCandidatura(situacaoCandidatura),
      genero(setGenero(codigoGenero)),
      qtdVotos(0),
      foiEleito(foiEleito)
{
}

int Candidato::getCodigoCargo() const
{
    return this->codigoCargo;
}

int Candidato::getCodigoMunicipio() const
{
    return this->codigoMunicipio;
}

int Candidato::getNumeroFederacao() const
{
    return this->numeroFederacao;
}

bool Candidato::getFoiEleito() const
{
    return this->foiEleito;
}

const Partido &Candidato::getPartido() const
{
    return *(this->partido);
}

const Genero &Candidato::getGenero() const
{
    return this->genero;
}

int Candidato::getNumeroVotavel() const
{
    return this->numeroVotavel;
}

const string &Candidato::getNome() const
{
    return this->nome;
}

const Data &Candidato::getDataNascimento() const
{
    return this->dataNascimento;
}

const int Candidato::getQtdVotos() const
{
    return this->qtdVotos;
}

const int Candidato::getSituacaoCandidatura() const
{
    return this->situacaoCandidatura;
}

void Candidato::adicionaVotosCandidato(const int &qtdVotos)
{
    this->qtdVotos += qtdVotos;
    this->partido->adicionaVotosNominaisPartido(qtdVotos);
}

int Candidato::getIdade(const Data &dataAtual)
{
    return this->dataNascimento.diferencaAnos(dataAtual);
}

void Candidato::toString() const
{
    cout
        << this->nome << "; "
        << this->numeroVotavel << "; "
        << this->codigoMunicipio << "; "
        << this->codigoCargo << "; "
        << (this->partido ? this->partido->getSigla() : "-") << "; "
        << this->numeroFederacao << "; "
        << this->dataNascimento.toString() << "; "
        << this->situacaoCandidatura << "; "
        << (this->genero == MASCULINO ? "Masculino" : "Feminino") << "; "
        << this->qtdVotos << "; "
        << (this->foiEleito ? "Sim" : "Nao")
        << endl;
}