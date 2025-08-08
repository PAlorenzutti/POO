#include "livro.hpp"

Livro::Livro(const string& titulo, Pessoa& autor) : titulo(titulo), autorPtr(&autor) {
}

Pessoa& Livro::getAutor() const
{
    return *autorPtr;
}

string Livro::getTitulo() const
{
    return titulo;
}
