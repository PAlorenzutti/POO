#include "turma.hpp"

Turma::Turma(const string &codigo) : codigo(codigo) {};

void Turma::adicionarAluno(Aluno &aluno)
{
    this->alunos.push_back(&aluno);
}

const vector<Aluno*>& Turma::getAlunos() const
{
    return this->alunos;
}

string Turma::getCodigo() const
{

    return this->codigo;
}
