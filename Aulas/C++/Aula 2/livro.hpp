#if !defined(LIVRO_HPP)
#define LIVRO_HPP

#include <string>
#include "pessoa.hpp"

using namespace std;

class Livro
{
    string titulo;
    Pessoa* autorPtr;
public:
    Livro(const string& titulo, Pessoa& autor);
    Pessoa& getAutor() const;
    string getTitulo() const;
};

#endif // LIVRO_HPP
