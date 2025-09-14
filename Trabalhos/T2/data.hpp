#ifndef DATA_HPP
#define DATA_HPP

#include <string>

using namespace std;

class Data
{
private:
    int ano;
    int mes;
    int dia;

public:
    Data(int ano, int mes, int dia);
    bool isBissexto(int ano) const;
    int diasNoMes(int ano, int mes) const;
    Data proximoDia() const;
    int diferencaDias(const Data &outraData) const;
    int diferencaAnos(const Data &outraData) const;
    bool operator<(const Data &outraData) const;
    string toString() const;
    static Data toData(const string &dataStr);
};

#endif