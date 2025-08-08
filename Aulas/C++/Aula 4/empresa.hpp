#if !defined(EMPRESA_H)
#define EMPRESA_H

#include <map>

#include <string>

class Departamento;

using namespace std;

class Empresa
{
    map<int,Departamento*> departamentos;
public:
    Departamento &criaDepartamento(int codigo, const string &nome);
    Departamento &getDepartamento(int codigo);
    const map<int,Departamento*> &getDepartamentos() const;
    ~Empresa();
};


#endif // EMPRESA_H
