# Projeto: Pilha em Java

Este projeto implementa uma estrutura de dados do tipo **pilha (stack)** em Java, com operações básicas como empilhar, desempilhar, verificar se está vazia e obter o tamanho. A pilha segue o princípio **LIFO (Last-In, First-Out)**.

## Estrutura do Projeto

```
pilha/
├── Pilha.java        # Implementação da classe Pilha usando LinkedList
├── Main.java         # Classe principal para demonstrar o uso da pilha
├── Pilha[array].old  # Versão antiga baseada em array (não utilizada)
```

## Requisitos

- Java JDK 8 ou superior

## Como Executar

1. Compile os arquivos:

```bash
javac Pilha.java
javac Main.java
```

2. Execute o programa:

```bash
java Main
```

## Funcionalidades

A classe `Pilha` fornece os seguintes métodos:

- `empilha(String s)`: Adiciona um elemento ao topo da pilha.
- `desempilha()`: Remove e retorna o elemento no topo da pilha.
- `isVazia()`: Verifica se a pilha está vazia.
- `getTamanho()`: Retorna o número de elementos na pilha.

## Design Pattern Utilizado

- **Wrapper**: A classe `Pilha` atua como um wrapper para `LinkedList<String>`, expondo apenas operações relacionadas à semântica de uma pilha.

## Exemplo de Uso

O arquivo `Main.java` demonstra o uso da pilha empilhando e desempilhando várias strings e exibindo o conteúdo no console.
