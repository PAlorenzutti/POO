Implemente as classes que faltam para um sistema simples para cadastrar alunos em uma turma.

Classe Aluno
	•	Atributos privados: nome (string) e matrícula (int).
	•	Construtor que inicialize todos os atributos.
	•	Métodos de acesso (getters) para cada atributo.

Classe Turma
	•	Atributo privado: código (string) e um vetor de alunos (std::vector<Aluno> alunos). (Basta fazer #include <vetor>).
	•	Construtor que recebe o código da turma.
	•	Método para adicionar alunos à turma. (Use a função membro push_back de vector. )
	•	Método para obter todos os alunos cadastrados.
	•	Método para obter o código da turma.

O programa principal (main já fornecido) faz o seguinte:
	1.	Solicita ao usuário o código da turma.
	2.	Solicita os dados de 3 alunos (nome, matrícula), cria cada objeto Aluno e adiciona à turma.
	3.	Ao final, exibe todos os alunos cadastrados na turma, mostrando nome e matrícula.
