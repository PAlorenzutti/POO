#ifndef TURMA_HPP
#define TURMA_HPP

#include <string>
#include <vector>
#include "aluno.hpp"

class Turma {
private:
    std::string codigo;
    std::vector<Aluno*> alunos;

public:
    Turma(const std::string& codigo);
    void adicionarAluno(Aluno& aluno);
    std::string getCodigo() const;
    const std::vector<Aluno*>& getAlunos() const;
};

#endif