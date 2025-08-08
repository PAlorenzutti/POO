#include "aluno.hpp"

Aluno::Aluno(const std::string& nome, int matricula)
    : nome(nome), matricula(matricula) {}

void Aluno::setNome(const std::string& nome)
{
    this->nome=nome;
}

std::string Aluno::getNome() const {
    return nome;
}

int Aluno::getMatricula() const {
    return matricula;
}

double Aluno::getMedia() const {
    return media;
}
