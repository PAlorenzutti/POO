#if !defined(ALUNO_HPP)
#define ALUNO_HPP

#include <string>

using namespace std;

class Aluno
{
    string nome;
    int matricula;

public:
    Aluno(const string &nome, int matricula);
    string getNome() const;
    int getMatricula() const;
};

#endif