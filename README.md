-----

# 📦 Pesquisa, Ordenação e Estruturas de Dados Avançadas

Este projeto consiste em uma coletânea de implementações de **estruturas de dados complexas** e **algoritmos de ordenação e pesquisa**, desenvolvidos em linguagem Java. O diferencial deste repositório é a implementação de árvores avançadas e manipulação de arquivos em disco para ordenação de grandes volumes de dados.

## 🚀 Funcionalidades

### 1\. Algoritmos de Ordenação em Memória Principal (`PO/src/sortMethods`)
* **Diversos Métodos:** Implementação de algoritmos clássicos e avançados, incluindo `BubbleSort`, `InsertionSort`, `BinaryInsertionSort`, `SelectionSort`, `ShakeSort`, `ShellSort`, `HeapSort`, `QuickSort`, `MergeSort`, `CountingSort`, `RadixSort`, `BucketSort`, `CombSort`, `GnomeSort` e `TimSort`.

### 2\. Ordenação em Memória Secundária (`PoArquivo`)
* **Manipulação de Arquivos:** Leitura e gravação de registros customizados em arquivos binários (`.dat`) utilizando `RandomAccessFile`.
* **Ordenação Externa:** Adaptação de algoritmos de ordenação (como QuickSort, MergeSort e HeapSort) para operarem diretamente sobre os arquivos no disco, otimizando acessos de leitura/escrita.

### 3\. Estruturas de Árvores Avançadas (`PoArvore`)
* **Árvore B+ (`bPlus`):** Implementação completa da estrutura base utilizada na maioria dos SGBDs. Inclui **inserção** dinâmica (com *split*), navegação eficiente e **exclusão** complexa (gerenciando *underflow* com redistribuição e concatenação de nós).
* **Árvore PATRICIA (`patricia`):** Uma Trie compactada para armazenamento e recuperação rápida de strings, poupando espaço de memória ao localizar divergências entre prefixos.
* **Árvore N-ária (`nArea`):** Estrutura genérica onde cada nó pode suportar um número configurável *N* de sub-árvores.

### 4\. Listas Encadeadas e Generalizadas (`PoListas` & `PoListaGen`)
* **Listas Duplamente Encadeadas:** Estruturas com operações de inserção e métodos de ordenação atuando diretamente nos nós.
* **Listas Generalizadas:** Uso intensivo de recursividade e estruturas de suporte (Pilhas) para fazer o *parsing* de strings (ex: `[a, [b, c], d]`) transformando-as em listas de listas e átomos.

## 🛠️ Tecnologias e Conceitos Aplicados

* **Linguagem Java**
* **Estruturas de Dados:**
  * Árvores de Pesquisa e Tries.
  * Listas Encadeadas, Pilhas e Filas dinâmicas.
* **Manipulação de E/S:** Uso de apontadores e acessos diretos no disco (`RandomAccessFile`).
* **Complexidade e Otimização:** Controle minucioso de índices, ponteiros (referências) e gerenciamento de performance tanto em RAM quanto em memória secundária.

## 📋 Pré-requisitos

Para compilar e rodar este projeto, você precisará de:
* Kit de Desenvolvimento Java (JDK).
* Uma IDE (como IntelliJ IDEA, Eclipse ou VS Code) com suporte a projetos Java com múltiplos módulos.

## ⚙️ Como Compilar e Rodar

1. Clone o repositório para o seu ambiente local.
2. Abra a pasta raiz na sua IDE de preferência. Aguarde a ferramenta reconhecer a estrutura de módulos (como `PO`, `PoArquivo`, `PoArvore`).
3. Navegue até a estrutura que deseja testar (por exemplo, `PoArvore/bPlus/src`).
4. Execute o arquivo contendo o método `main` (ex: `Main.java` ou `TesteOrdenacao.java`) correspondente.

## 📂 Estrutura dos Arquivos

* `PO/`: Códigos-fonte dos algoritmos de ordenação em memória principal.
* `PoArquivo/`: Pacotes e lógicas para criação, leitura e ordenação de dados `.dat`.
* `PoArvore/`: Implementações segmentadas para a Árvore B+, Árvore PATRICIA e Árvore N-ária.
* `PoListaGen/` e `PoListas/`: Arquivos correspondentes às Listas Generalizadas e Encadeadas.

-----
