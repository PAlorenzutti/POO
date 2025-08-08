#include <iostream>
#include <string>
#include "aluno.hpp"
#include "turma.hpp"

int main() {
    std::string codigoTurma;
    std::cout << "Digite o codigo da turma: ";
    std::getline(std::cin, codigoTurma);

    Turma turma(codigoTurma);

    int quantidadeAlunos = 3;
    for (int i = 1; i <= quantidadeAlunos; ++i) {
        std::cout << "\nAluno " << i << ":\n";
        std::string nome;
        int matricula;
 
        std::cout << "Nome: ";
        std::getline(std::cin, nome);

        std::cout << "Matricula: ";
        std::cin >> matricula;

        std::cin.ignore(); // Limpa buffer para o próximo getline

        Aluno aluno(nome, matricula);
        turma.adicionarAluno(aluno);
    }

    std::cout << "\nAlunos da turma " << turma.getCodigo() << ":\n";
    for (const Aluno& aluno : turma.getAlunos()) {
        std::cout << "Nome: " << aluno.getNome()
                  << ", Matricula: " << aluno.getMatricula() << std::endl;
    }

    return 0;
}