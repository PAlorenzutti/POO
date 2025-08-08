# Exercício: Complete a implementação 

Analise o código existe e produza os arquivos departamento.cpp e empresa.cpp para complementar a implementação.

# Introdução ao uso de mapas em C++

O `std::map` é um container associativo da STL (Standard Template Library) do C++ que armazena elementos em pares chave-valor. Cada elemento possui uma chave única que é usada para identificar e acessar o valor correspondente. O `map` mantém os elementos automaticamente ordenados pela chave.

## Características principais

- **Chaves únicas**: Cada chave pode aparecer apenas uma vez no map
- **Ordenação automática**: Os elementos são mantidos em ordem crescente pela chave
- **Implementação**: Geralmente implementado como árvore binária balanceada (Red-Black Tree)

## Declaração e inicialização

```cpp
#include <map>
#include <string>

// Declaração básica
std::map<int, std::string> mapa;

// Inicialização com valores
std::map<int, std::string> funcionarios = {
    {1, "João"},
    {2, "Maria"},
    {3, "Pedro"}
};

// Map com chaves string
std::map<std::string, int> idades = {
    {"João", 30},
    {"Maria", 25},
    {"Pedro", 35}
};
```

## Operações básicas

### Inserção
```cpp
// Usando operator[]
mapa[4] = "Ana";

// Usando insert()
mapa.insert({5, "Carlos"});
mapa.insert(std::make_pair(6, "Sofia"));
```

### Acesso aos elementos
```cpp
// Usando operator[] (cria elemento se não existir)
std::string nome = funcionarios[1];

// Usando at() (lança exceção se não existir)
std::string nome2 = funcionarios.at(2);

// Verificando se existe antes de acessar
if (funcionarios.find(3) != funcionarios.end()) {
    std::string nome3 = funcionarios[3];
}
```

### Busca
```cpp
// Usando find()
auto it = funcionarios.find(2);
if (it != funcionarios.end()) {
    std::cout << "Encontrado: " << it->second << std::endl;
}

// Usando count() (retorna 0 ou 1 para map)
if (funcionarios.count(1) > 0) {
    std::cout << "Chave 1 existe" << std::endl;
}
```

### Remoção
```cpp
// Remover por chave
funcionarios.erase(1);

// Remover por iterador
auto it = funcionarios.find(2);
if (it != funcionarios.end()) {
    funcionarios.erase(it);
}

// Limpar todo o map
funcionarios.clear();
```

## Iteração

```cpp
// Iteração com range-based for (C++11+)
for (const auto& par : funcionarios) {
    std::cout << "ID: " << par.first << ", Nome: " << par.second << std::endl;
}

// Iteração com iteradores
for (auto it = funcionarios.begin(); it != funcionarios.end(); ++it) {
    std::cout << "ID: " << it->first << ", Nome: " << it->second << std::endl;
}
```

