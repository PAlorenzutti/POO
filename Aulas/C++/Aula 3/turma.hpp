#if !defined(TURMA_HPP)
#define TURMA_HPP

#include <string>
#include <vector>

#include "aluno.hpp"

using namespace std;

class Turma
{
    string codigo;
    vector<Aluno*> alunos;
public:
    Turma(const string &codigo);
    void adicionarAluno(Aluno &aluno);
    const vector<Aluno*>& getAlunos() const;
    string getCodigo() const;
};

#endif