#include "departamento.hpp"

using namespace std;

Departamento::Departamento(int codigo, const string &nome, Empresa &e) : codigo(codigo), nome(nome), e(&e)
{
}

int Departamento::getCodigo() const
{
    return this->codigo;
}

const string& Departamento::getNome() const
{
    return this->nome;
}

void Departamento::setNome(const string &nome)
{
    this->nome = nome;
}

Empresa& Departamento::getEmpresa() const
{
    return *(this->e);
}