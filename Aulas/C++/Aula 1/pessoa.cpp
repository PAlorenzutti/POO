#include "pessoa.hpp"

#include <iostream>

using namespace std;

Pessoa::Pessoa(const string &nome, int idade) : nome(nome), idade(idade)
{
    cout << "Construímos uma pessoa de nome " << nome << " e idade " << idade << endl;
};

void Pessoa::setNome(const string &nome)
{
    this->nome = nome;
}

string Pessoa::getNome() const
{
    return this->nome;
}

int Pessoa::getIdade() const
{
    return this->idade;
}
