#include "aluno.hpp"

#include <iostream>

Aluno::Aluno(const string &nome, int matricula) : nome(nome), matricula(matricula) {};

string Aluno::getNome() const
{
    return this->nome;
}

int Aluno::getMatricula() const
{
    return this->matricula;
}