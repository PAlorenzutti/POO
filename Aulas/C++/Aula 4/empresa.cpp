#include "empresa.hpp"
#include "departamento.hpp"

#include <string>

using namespace std;

Departamento& Empresa::criaDepartamento(int codigo, const string &nome)
{
    Departamento *d = new Departamento(codigo, nome, *this);

    this->departamentos.insert({codigo, d});

    // this->departamentos[codigo] = d;

    return *d;
}

Departamento &Empresa::getDepartamento(int codigo)
{
    return *(this->departamentos[codigo]);
}

const map<int, Departamento *> &Empresa::getDepartamentos() const
{
    return this->departamentos;
}

Empresa::~Empresa()
{
    for(const auto &par : departamentos){
        delete par.second;
    }
}