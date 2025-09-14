#include <iostream>

#include "eleicao.hpp"
#include "utils.hpp"

#include <fstream>
#include <sstream>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

Eleicao::Eleicao(
    int codigoCidade,
    const string &candidatosPath,
    const string &eleicaoPath,
    const string &dataAtual)
    : eleicaoPath(eleicaoPath),
      candidatosPath(candidatosPath),
      codigoCidade(codigoCidade),
      dataAtual(Data::toData(dataAtual))
{
}

void Eleicao::leArquivoCandidatos()
{
    // Abre o arquivo e inicia uma linha
    ifstream in(this->candidatosPath);
    string linha;

    // Lê e descarta o cabeçalho do .csv
    getline(in, linha);

    // Realiza a leitura do arquivo;
    while (getline(in, linha))
    {
        // Converte linha para UTF-8
        linha = iso_8859_1_to_utf8(linha);

        vector<string> valores = parseCSVLine(linha);

        Partido *partido;

        // Se o partido com o número passado não existir, cria um novo partido
        if (partidos.find(stoi(valores[25])) == partidos.end())
        {
            partido = new Partido(stoi(valores[25]), valores[26]);
            partidos[stoi(valores[25])] = partido;
        }
        // Se existir, pega o partido existente;
        else
        {
            partido = partidos[stoi(valores[25])];
        }

        // Se a linha for referente ao município e for referente ao cargo de vereador (válido)
        if (this->codigoCidade == stoi(valores[11]) && valores[14] == "VEREADOR" && stoi(valores[48]) != -1)
        {
            partido->criaCandidato(
                stoi(valores[16]),
                stoi(valores[11]),
                stoi(valores[13]),
                valores[18],
                stoi(valores[28]),
                Data::toData(valores[36]),
                stoi(valores[48]),
                stoi(valores[38]),
                stoi(valores[48]));
        }
    }

    in.close();
}

void Eleicao::leArquivoVotacao()
{
    // Abre o arquivo e inicia uma linha
    ifstream in(this->eleicaoPath);
    string linha;

    // Lê e descarta o cabeçalho
    getline(in, linha);

    // Pega a lista de candidatos válidos da eleição
    map<int, Candidato *> candidatos = this->getCandidatos();

    while (getline(in, linha))
    {
        // Converte linha para UTF-8
        linha = iso_8859_1_to_utf8(linha);

        // Parse dos valores da linha
        vector<string> valores = parseCSVLine(linha);

        // Se a linha for referente ao município que estamos procurando e for referente ao cargo de vereador
        if (this->codigoCidade == stoi(valores[11]) && stoi(valores[17]) == 13 && !(stoi(valores[19]) >= 95 && stoi(valores[19]) <= 98))
        {
            // Para votos em candidatos;
            if (valores[19].size() > 2)
            {
                // Pega o candidato pelo número votável;
                Candidato *c = candidatos[stoi(valores[19])];

                // Adiciona a quantidade de votos ao candidato e ao partido dele;
                c->adicionaVotosCandidato(stoi(valores[21]));

                // Para votos de legenda;
            }
            else
            {
                // Pega o partido em questão;
                Partido *p = partidos[stoi(valores[19])];

                // Adiciona votos de legenda ao partido;
                p->adicionaVotosLegendaPartido(stoi(valores[21]));
            }
        }
    }

    in.close();
}

map<int, Candidato *> Eleicao::getCandidatos() const
{
    map<int, Candidato *> todosCandidatos;

    for (const auto &pair : partidos)
    {
        const auto &cands = pair.second->getCandidatos();
        todosCandidatos.insert(cands.begin(), cands.end());
    }

    return todosCandidatos;
}

int Eleicao::getQtdCandidatosEleitos() const
{
    int qtd = 0;

    for (const auto &pair : partidos)
    {
        qtd += pair.second->getQtdCandidatosEleitos();
    }

    return qtd;
}

void Eleicao::geraRelatorioCandidatosEleitos() const
{
    // Gera a string de saída no terminal;
    string s = "";

    vector<Candidato *> candidatosEleitos;

    // Pega todos os candidatos eleitos de todos os partidos
    for (const auto &pair : partidos)
    {
        const auto &eleitos = pair.second->getCandidatosEleitos();
        candidatosEleitos.insert(candidatosEleitos.end(), eleitos.begin(), eleitos.end());
    }

    // Ordena usando o comparator de Candidato;
    sort(candidatosEleitos.begin(), candidatosEleitos.end(), CandidatoComparator{});

    s += "Número de vagas: " + to_string(this->getQtdCandidatosEleitos()) + "\n\n";

    s += "Vereadores eleitos:\n";

    int i = 1;

    for (Candidato *c : candidatosEleitos)
    {
        s += to_string(i++) + " - ";

        if (c->getNumeroFederacao() != -1)
        {
            s += "*";
        }

        s += c->getNome() + " (" + c->getPartido().getSigla() + ", " + numberFormat(c->getQtdVotos()) + " votos)" + "\n";
    }

    cout << s;
}

void Eleicao::geraRelatorioCandidatosTotal() const
{
    string s = "\n";

    s += "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):\n";

    // Pega a lista de todos os candidatos presentes na eleição
    vector<Candidato *> candidatosTotais;
    map<int, Candidato *> todosCandidatos = this->getCandidatos();
    for (const auto &pair : todosCandidatos)
    {
        candidatosTotais.push_back(pair.second);
    }

    // Ordena em ordem decrescente de votos
    sort(candidatosTotais.begin(), candidatosTotais.end(), CandidatoComparator{});

    int i = 1;
    int vagas = this->getQtdCandidatosEleitos();

    for (Candidato *c : candidatosTotais)
    {
        s += to_string(i) + " - ";
        if (c->getNumeroFederacao() != -1)
            s += "*";
        s += c->getNome() + " (" + c->getPartido().getSigla() + ", " + numberFormat(c->getQtdVotos()) + " votos)\n";
        if (i++ == vagas)
            break;
    }

    cout << s;
}

void Eleicao::geraRelatorioMajoritariaProporcional() const
{
    string s = "\n";

    s += "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n";
    s += "(com sua posição no ranking de mais votados)\n";

    // Pega a lista de todos os candidatos presentes na eleição
    vector<Candidato *> candidatosTotais;
    map<int, Candidato *> todosCandidatos = this->getCandidatos();
    for (const auto &pair : todosCandidatos)
    {
        candidatosTotais.push_back(pair.second);
    }

    // Ordena usando o comparator de candidato;
    sort(candidatosTotais.begin(), candidatosTotais.end(), CandidatoComparator{});

    int i = 1;
    int vagas = this->getQtdCandidatosEleitos();

    // Candidatos que seriam eleitos pelo sistema majoritário, mas não foram eleitos
    for (Candidato *c : candidatosTotais)
    {
        if (!c->getFoiEleito())
        {
            s += to_string(i) + " - ";
            if (c->getNumeroFederacao() != -1)
                s += "*";
            s += c->getNome() + " (" + c->getPartido().getSigla() + ", " + numberFormat(c->getQtdVotos()) + " votos)\n";
        }
        if (i++ == vagas)
            break;
    }

    s += "\nEleitos, que se beneficiaram do sistema proporcional:\n";
    s += "(com sua posição no ranking de mais votados)\n";

    // Candidatos eleitos que não estariam entre os mais votados (beneficiados pelo sistema proporcional)
    for (int j = vagas; j < (int)candidatosTotais.size(); ++j)
    {
        Candidato *c = candidatosTotais[j];
        if (c->getFoiEleito())
        {
            s += to_string(j + 1) + " - ";
            if (c->getNumeroFederacao() != -1)
                s += "*";
            s += c->getNome() + " (" + c->getPartido().getSigla() + ", " + numberFormat(c->getQtdVotos()) + " votos)\n";
        }
    }

    cout << s;
}

void Eleicao::geraRelatorioPartidos() const
{
    string s = "\n";

    s += "Votação dos partidos e número de candidatos eleitos:\n";

    // Pega a lista de partidos em um vetor para ordenar
    vector<Partido *> partidosRelatorio;
    for (const auto &pair : partidos)
    {
        partidosRelatorio.push_back(pair.second);
    }

    // Ordena em ordem decrescente de votos e crescente de número votável (operator< já faz isso)
    sort(partidosRelatorio.begin(), partidosRelatorio.end(), PartidoComparator());

    int i = 1;
    for (Partido *p : partidosRelatorio)
    {
        s += to_string(i) + " - " + p->getSigla() + " - " + to_string(p->getNumero()) + ", ";

        int votosTotais = p->getQtdVotos();
        s += numberFormat(votosTotais) + ((votosTotais == 1 || votosTotais == 0) ? " voto " : " votos ");

        int votosNominais = p->getQtdVotosNominais();
        int votosLegenda = p->getQtdVotosLegenda();
        s += "(" + numberFormat(votosNominais) + ((votosNominais == 1 || votosNominais == 0) ? " nominal e " : " nominais e ") + numberFormat(votosLegenda) + " de legenda), ";

        int qtdEleitos = p->getQtdCandidatosEleitos();
        if (qtdEleitos > 1)
        {
            s += to_string(qtdEleitos) + " candidatos eleitos\n";
        }
        else
        {
            s += to_string(qtdEleitos) + " candidato eleito\n";
        }

        i++;
    }

    cout << s;
}

void Eleicao::geraRelatorioPrimeiroUltimoPartidos() const
{
    string s = "\n";

    s += "Primeiro e último colocados de cada partido:\n";

    // Adiciona apenas partidos com candidatos que concorreram às eleições
    vector<Partido *> partidosPrimeiroUltimo;
    for (const auto &pair : partidos)
    {
        if (!pair.second->getCandidatos().empty())
        {
            partidosPrimeiroUltimo.push_back(pair.second);
        }
    }

    // Ordena em função dos candidatos mais votados de cada partido (ordem decrescente)
    sort(partidosPrimeiroUltimo.begin(), partidosPrimeiroUltimo.end(), PartidoCandidatoComparator{});

    int i = 1;
    for (Partido *p : partidosPrimeiroUltimo)
    {
        // Pega a lista de candidatos com votos de cada partido e ordena em função da quantidade de votos
        vector<Candidato *> candidatos;
        const auto &candidatosMap = p->getCandidatos();
        for (const auto &candPair : candidatosMap)
        {
            candidatos.push_back(candPair.second);
        }
        sort(candidatos.begin(), candidatos.end(), CandidatoComparator{});

        if (candidatos.size() > 1)
        {
            Candidato *primeiro = candidatos.front();
            Candidato *ultimo = candidatos.back();

            string votosPrimeiro = numberFormat(primeiro->getQtdVotos());
            string palavraVotoPrimeiro = (primeiro->getQtdVotos() > 1) ? "votos" : "voto";
            string strPrimeiro = primeiro->getNome() + " (" + to_string(primeiro->getNumeroVotavel()) + ", " + votosPrimeiro + " " + palavraVotoPrimeiro + ")";

            string votosUltimo = numberFormat(ultimo->getQtdVotos());
            string palavraVotoUltimo = (ultimo->getQtdVotos() > 1) ? "votos" : "voto";
            string strUltimo = ultimo->getNome() + " (" + to_string(ultimo->getNumeroVotavel()) + ", " + votosUltimo + " " + palavraVotoUltimo + ")";

            s += to_string(i) + " - " + p->getSigla() + " - " + to_string(p->getNumero()) + ", " + strPrimeiro + " / " + strUltimo + "\n";
            i++;
        }
    }

    cout << s;
}

void Eleicao::geraRelatorioFaixaEtaria() const
{
    string s = "\n";
    s += "Eleitos, por faixa etária (na data da eleição):\n";

    int trinta = 0;
    int trintaQuarenta = 0;
    int quarentaCinquenta = 0;
    int cinquentaSessenta = 0;
    int sessenta = 0;

    // Pega todos os candidatos eleitos;
    vector<Candidato *> candidatos;
    for (const auto &pair : partidos)
    {
        const auto &eleitos = pair.second->getCandidatosEleitos();
        candidatos.insert(candidatos.end(), eleitos.begin(), eleitos.end());
    }

    // Faz o levantamento de cada idade;
    for (Candidato *c : candidatos)
    {
        int idade = c->getIdade(this->dataAtual);
        if (idade < 30)
            trinta++;
        else if (idade < 40)
            trintaQuarenta++;
        else if (idade < 50)
            quarentaCinquenta++;
        else if (idade < 60)
            cinquentaSessenta++;
        else
            sessenta++;
    }

    int totalFaixa = (int)candidatos.size();
    s += "      Idade < 30: " + to_string(trinta) + " (" + percentFormat(totalFaixa == 0 ? 0.0 : ((double)(trinta) * 100.0 / totalFaixa)) + ")\n";
    s += "30 <= Idade < 40: " + to_string(trintaQuarenta) + " (" + percentFormat(totalFaixa == 0 ? 0.0 : ((double)(trintaQuarenta) * 100.0 / totalFaixa)) + ")\n";
    s += "40 <= Idade < 50: " + to_string(quarentaCinquenta) + " (" + percentFormat(totalFaixa == 0 ? 0.0 : ((double)(quarentaCinquenta) * 100.0 / totalFaixa)) + ")\n";
    s += "50 <= Idade < 60: " + to_string(cinquentaSessenta) + " (" + percentFormat(totalFaixa == 0 ? 0.0 : ((double)(cinquentaSessenta) * 100.0 / totalFaixa)) + ")\n";
    s += "60 <= Idade     : " + to_string(sessenta) + " (" + percentFormat(totalFaixa == 0 ? 0.0 : ((double)(sessenta) * 100.0 / totalFaixa)) + ")\n";

    cout << s;
}

void Eleicao::geraRelatorioGenero() const
{
    string s = "\n";
    s += "Eleitos, por gênero:\n";

    int masculino = 0;
    int feminino = 0;

    vector<Candidato *> candidatos;
    for (const auto &pair : partidos)
    {
        const auto &eleitos = pair.second->getCandidatosEleitos();
        candidatos.insert(candidatos.end(), eleitos.begin(), eleitos.end());
    }

    for (Candidato *c : candidatos)
    {
        if (c->getGenero() == MASCULINO)
            masculino++;
        else if (c->getGenero() == FEMININO)
            feminino++;
    }

    int totalGenero = (int)(candidatos.size());
    s += "Feminino:  " + to_string(feminino) + " (" + percentFormat(totalGenero == 0 ? 0.0 : ((double)(feminino) * 100.0 / totalGenero)) + ")\n";
    s += "Masculino: " + to_string(masculino) + " (" + percentFormat(totalGenero == 0 ? 0.0 : ((double)(masculino) * 100.0 / totalGenero)) + ")\n";

    cout << s;
}

void Eleicao::geraRelatorioVotos() const
{
    string s = "\n";

    int votosValidos = 0;
    int votosNominais = 0;
    int votosLegenda = 0;

    for (const auto &pair : partidos)
    {
        votosLegenda += pair.second->getQtdVotosLegenda();
        votosNominais += pair.second->getQtdVotosNominais();
    }

    votosValidos = votosLegenda + votosNominais;

    s += "Total de votos válidos:    " + numberFormat(votosValidos) + "\n";
    s += "Total de votos nominais:   " + numberFormat(votosNominais) + " (" + percentFormat(votosValidos == 0 ? 0.0 : ((double)(votosNominais) * 100.0 / votosValidos)) + ")\n";
    s += "Total de votos de legenda: " + numberFormat(votosLegenda) + " (" + percentFormat(votosValidos == 0 ? 0.0 : ((double)(votosLegenda) * 100.0 / votosValidos)) + ")\n\n";

    cout << s;
}

Eleicao::~Eleicao()
{
    for (auto &pair : partidos)
    {
        delete pair.second;
    }
}

void Eleicao::toString() const
{
    for (const auto &pair : partidos)
    {
        if (pair.second)
            pair.second->toString();
    }
}
