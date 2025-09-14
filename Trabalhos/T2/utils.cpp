#include "utils.hpp"

#include <sstream>
#include <cstdint>
#include <algorithm>
#include <locale>

using namespace std;

// Função de conversão de ISO-8859-1 para UTF-8 fornecida pelo professor
string iso_8859_1_to_utf8(string &str)
{
  // adaptado de: https://stackoverflow.com/a/39884120 :-)
  string strOut;
  for (string::iterator it = str.begin(); it != str.end(); ++it)
  {
    uint8_t ch = *it;
    if (ch < 0x80)
    {
      // já está na faixa ASCII (bit mais significativo 0), só copiar para a saída
      strOut.push_back(ch);
    }
    else
    {
      // está na faixa ASCII-estendido, escrever 2 bytes de acordo com UTF-8
      // o primeiro byte codifica os 2 bits mais significativos do byte original (ISO-8859-1)
      strOut.push_back(0b11000000 | (ch >> 6));
      // o segundo byte codifica os 6 bits menos significativos do byte original (ISO-8859-1)
      strOut.push_back(0b10000000 | (ch & 0b00111111));
    }
  }
  return strOut;
}

vector<string> parseCSVLine(const string &line)
{
  vector<string> valores;
  istringstream linhaStream(line);
  string coluna;
  while (getline(linhaStream, coluna, ';'))
  {
    // Remove aspas do início e fim, se existirem
    if (!coluna.empty() && coluna.front() == '"' && coluna.back() == '"' && coluna.size() >= 2)
    {
      coluna = coluna.substr(1, coluna.size() - 2);
    }

    valores.push_back(coluna);
  }
  return valores;
}

// Formata inteiros com separador de milhares no padrão brasileiro (ponto)
string numberFormat(int n)
{
  // Tenta usar locale do sistema para pt_BR;
  locale loc("pt_BR.UTF-8");
  stringstream ss;
  ss.imbue(loc);
  ss << n;
  return ss.str();
}

// Formata um valor percentual ao estilo pt-BR: duas casas, vírgula como separador e sufixo "%" (ex.: "12,34%").
// Ex.: percentFormat(0.0) => "0,00%"; percentFormat(57.1) => "57,10%".
string percentFormat(double percent)
{
  stringstream ss;
  ss.setf(ios::fixed);
  ss.precision(2);
  ss << percent;
  string out = ss.str();
  for (char &ch : out)
    if (ch == '.')
      ch = ','; // pt-BR decimal
  out += '%';
  return out;
}