#include "turma.hpp"

Turma::Turma(const std::string& codigo)
    : codigo(codigo) {}

void Turma::adicionarAluno(Aluno& aluno) {
    this->alunos.push_back(&aluno);
}

std::string Turma::getCodigo() const {
    return this->codigo;
}

const std::vector<Aluno*>& Turma::getAlunos() const {
    return this->alunos;
}
