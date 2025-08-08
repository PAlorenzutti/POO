#if !defined(DEPARTAMENTO_HPP)
#define DEPARTAMENTO_HPP

#include <string>
#include "empresa.hpp"

using namespace std;

class Empresa; // forward reference

class Departamento
{
    Empresa *e;
    int codigo;
    string nome;
public:
    Departamento(int codigo, const string &nome, Empresa &e);
    int getCodigo() const;
    const string &getNome() const;
    void setNome(const string &nome);
    Empresa &getEmpresa() const;
};

#endif // DEPARTAMENTO_H
