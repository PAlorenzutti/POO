# Banco - Sistema Simples em Java

Este projeto foi desenvolvido durante a aula 2 para representar um sistema bancário simples com uso de classes em Java.

## Implementação
O código reflete as principais entidades de um banco:
- **Pessoa**: Uma instância desta classe representa um cliente.
- **Conta**: Uma instância desta classe representa uma conta bancária.
- **Agência**: Uma instância desta classe representa uma agência bancária.

Cada classe tem as seguintes responsabilidades:

- **Pessoa.java**: Define atributos como nome, CPF e idade para representar um cliente do banco.
- **Conta.java**: Representa uma conta bancária com atributos como saldo, número da conta. Permite depósito, que incrementa o saldo.
- **Agencia.java**: Gerencia as contas de uma agência, permitindo adicionar contas, buscar contas existentes e listar todas as contas (inclusive filtrando por cliente).
- **App.java**: Classe principal com o método `main`, usada para iniciar e testar o funcionamento do sistema bancário.


## Tecnologias Utilizadas
- Linguagem: **Java**
- IDE recomendada: **Visual Studio Code** (com extensões de Java)

## Estrutura de Pastas
```
banco/
├── src/        # Código-fonte (.java)
├── bin/        # Arquivos compilados (.class)
├── README.md   # Documentação do projeto (este arquivo)
```

## Como Executar

1. Compile os arquivos Java:

```bash
javac -d bin src/*.java
```

2. Execute o programa:

```bash
java -cp bin App
```

> Certifique-se de estar na raiz da pasta `banco/` ao executar os comandos.













