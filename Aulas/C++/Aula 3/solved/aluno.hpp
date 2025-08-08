#if !defined(ALUNO_HPP)
#define ALUNO_HPP

#include <string>

class Aluno {
private:
    std::string nome;
    int matricula;
    double media;

public:
    Aluno(const std::string& nome, int matricula);
    std::string getNome() const;
    void setNome(const std::string& nome);
    int getMatricula() const;
    double getMedia() const;
};

#endif
