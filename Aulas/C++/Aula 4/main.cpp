#include <iostream> // cout
#include <fstream>  // ifstream
#include <sstream>  // istringstream
#include <map>      // map :-)

#include "departamento.hpp"
#include "empresa.hpp"
using namespace std;

int main()
{    
    std::ifstream in("departamentos.csv");
    std::string linha;
    std::getline(in,linha); // descarta o cabeçalho
    Empresa e;
    while (getline(in,linha))
    {
        istringstream linhaStream(linha);
        string coluna1;
        getline(linhaStream,coluna1,';');
        string coluna2;
        getline(linhaStream,coluna2,';');
        int codigo = stoi(coluna1);
        e.criaDepartamento(codigo,coluna2);
    }    
    // inv: arquivo lido
    in.close();

    // usando iteração em pairs
    for (const auto &p : e.getDepartamentos())
    {
        cout << "|" << p.first << "|" << p.second->getNome() << endl;
    }
    // usando structured bindings
    for (auto & [key, value] : e.getDepartamentos())
      std::cout << "|" << key << "|" <<  value->getNome() << std::endl;

}
