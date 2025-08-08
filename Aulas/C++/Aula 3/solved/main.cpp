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

        // temos que criar os alunos com alocação dinâmica
        Aluno* alunoPtr = new Aluno(nome, matricula);
        turma.adicionarAluno(*alunoPtr);
    }

    // colocar uma aluna a mais para alterar o nome dela
    Aluno maria("Maria",123123);
    turma.adicionarAluno(maria);
    maria.setNome("Maria da Silva");

    std::cout << "\nAlunos da turma " << turma.getCodigo() << ":\n";
    for (auto &aluno : turma.getAlunos()) {
        std::cout << "Nome: " << aluno->getNome()
                  << ", Matricula: " << aluno->getMatricula() << std::endl;
    }

    // valgrind vai reclamar, pois não desalocamos
    return 0;
}