
#include "data.hpp"

#include <sstream>
#include <iomanip>

using namespace std;

Data::Data(int ano, int mes, int dia) : ano(ano), mes(mes), dia(dia) {}

bool Data::isBissexto(int ano) const
{
    return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
}

int Data::diasNoMes(int ano, int mes) const
{
    int diasPorMes[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (mes == 2 && isBissexto(ano))
    {
        return 29;
    }
    return diasPorMes[mes];
}

Data Data::proximoDia() const
{
    Data proximo = *this; // Copia a data atual
    proximo.dia++;
    if (proximo.dia > diasNoMes(proximo.ano, proximo.mes))
    {
        proximo.dia = 1;
        proximo.mes++;
        if (proximo.mes > 12)
        {
            proximo.mes = 1;
            proximo.ano++;
        }
    }
    return proximo;
}

int Data::diferencaDias(const Data &outraData) const
{
    Data data1 = *this;
    Data data2 = outraData;
    // Garante que data1 seja a menor data
    if (data1.ano > data2.ano || (data1.ano == data2.ano && data1.mes > data2.mes) ||
        (data1.ano == data2.ano && data1.mes == data2.mes && data1.dia > data2.dia))
    {
        Data temp = data1;
        data1 = data2;
        data2 = temp;
    }
    int dias = 0;
    while (data1.ano != data2.ano || data1.mes != data2.mes || data1.dia != data2.dia)
    {
        data1 = data1.proximoDia();
        dias++;
    }
    return dias;
}

int Data::diferencaAnos(const Data &outraData) const
{
    Data data1 = *this;
    Data data2 = outraData;
    int anos = 0;
    // Garante que data1 seja a menor data
    if (data1.ano > data2.ano || (data1.ano == data2.ano && data1.mes > data2.mes) ||
        (data1.ano == data2.ano && data1.mes == data2.mes && data1.dia > data2.dia))
    {
        Data temp = data1;
        data1 = data2;
        data2 = temp;
    }
    anos = data2.ano - data1.ano;
    // Ajusta o número de anos se data2 ainda não ocorreu este ano
    if (data2.mes < data1.mes || (data2.mes == data1.mes && data2.dia < data1.dia))
    {
        anos--;
    }
    return anos;
}

bool Data::operator<(const Data &outraData) const
{
    if (ano != outraData.ano)
    {
        return ano < outraData.ano;
    }

    if (mes != outraData.mes)
    {
        return mes < outraData.mes;
    }

    return dia < outraData.dia;
}

string Data::toString() const
{
    ostringstream oss;
    oss << setfill('0') << setw(2) << dia << "/"
        << setw(2) << mes << "/" << ano;
    return oss.str();
}

// Parse string dd/MM/AAAA para objeto Data
Data Data::toData(const string &dataStr)
{
    int dia, mes, ano;
    char sep1, sep2;
    istringstream iss(dataStr);
    iss >> dia >> sep1 >> mes >> sep2 >> ano;
    if (iss.fail() || sep1 != '/' || sep2 != '/')
    {
        throw invalid_argument("Formato de data inválido: " + dataStr);
    }
    return Data(ano, mes, dia);
}