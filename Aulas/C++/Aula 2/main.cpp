#include <iostream>
#include <string>
#include "pessoa.hpp"
#include "livro.hpp"

using namespace std;

void imprimeLivro(const Livro& l)
{
    cout << "Livro ["<< l.getTitulo() <<"] de autoria de " << l.getAutor().getNome() << endl;
}

int main()
{
    Pessoa p1("Sicrano",19); // a única pessoa que vamos criar
    Livro l1("Os pensamentos de Sicrano",p1);
    imprimeLivro(l1);
    Livro l2("Mais pensamentos de Sicrano",p1); // novo livro do mesmo autor
    imprimeLivro(l2);
    // vamos alterar o nome de p1, felizmente, não temos cópias (como em Java)
    p1.setNome("Sicrano da Silva");
    cout << "Depois de renomear o autor:" << endl;
    imprimeLivro(l1);
    imprimeLivro(l2);
    // também funciona como esperado (similar ao que ocorreria em Java):
    l1.getAutor().setNome("Sicraninho");
    cout << "Depois de renomear novamente o autor (para Sicraninho):" << endl;
    imprimeLivro(l1);
    imprimeLivro(l2);
}

