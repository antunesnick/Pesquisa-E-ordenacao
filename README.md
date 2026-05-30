# \# 🚀 Pesquisa, Ordenação e Estruturas de Dados Avançadas

# 

# Bem-vindo ao repositório de \*\*Pesquisa e Ordenação\*\*! 

# 

# Este projeto é um portfólio de implementações em \*\*Java\*\* focadas em algoritmos clássicos de ordenação, manipulação de arquivos (memória secundária) e \*\*estruturas de dados avançadas\*\*, como a Árvore PATRICIA e a Árvore B+. 

# 

# \---

# 

# \## 📂 Estrutura do Repositório e Tópicos Abordados

# 

# \### 1. Métodos de Ordenação (Memória Principal) - `PO/src/sortMethods`

# Implementação de \*\*15 algoritmos de ordenação\*\*, desde as abordagens baseadas em comparação ($O(N^2)$) até abordagens avançadas e de tempo linear ($O(N \\log N)$ e $O(N)$):

# \* \*\*Simples:\*\* Bubble, Insertion, Binary Insertion, Selection, Shake, Comb, Gnome.

# \* \*\*Eficientes/Avançados:\*\* QuickSort (com e sem pivô), MergeSort, ShellSort, HeapSort, TimSort.

# \* \*\*Não-comparativos:\*\* CountingSort, RadixSort, BucketSort.

# 

# \### 2. Ordenação e Manipulação de Arquivos - `PoArquivo`

# A manipulação de arquivos binários diretos (via `RandomAccessFile`) é um desafio clássico que exige otimização de I/O.

# \* Gravação e leitura de registros customizados diretamente em arquivos `.dat`.

# \* Adaptação dos algoritmos de ordenação (como QuickSort, MergeSort e HeapSort) para atuar \*\*diretamente no disco\*\*, simulando o comportamento de SGBDs e gerenciamento de arquivos pesados.

# 

# \### 3. Estruturas em Árvores - `PoArvore`

# O destaque do projeto reside na implementação de árvores de pesquisa altamente otimizadas:

# 

# \* 🌳 \*\*Árvore B+ (`PoArvore/bPlus`)\*\*: 

# &#x20; Implementação completa da estrutura de dados base usada na maioria dos Bancos de Dados Relacionais.

# &#x20; \* \*\*Inserção\*\* lidando dinamicamente com quebra de nós (\*split\*).

# &#x20; \* \*\*Navegação\*\* (Busca) até o nível folha de forma rápida.

# &#x20; \* \*\*Exclusão\*\* complexa, gerenciando \*underflow\* com mecanismos de \*\*redistribuição\*\* e \*\*concatenação\*\* de páginas (nós).

# 

# \* 🌲 \*\*Árvore PATRICIA (`PoArvore/patricia`)\*\*:

# &#x20; Uma Trie compactada muito eficiente para armazenamento de strings e recuperação rápida. A árvore localiza divergências (\*dif\*) entre prefixos, poupando espaço de memória em relação a uma Trie convencional.

# &#x20; \* Inserção de palavras e identificação automática de prefixos comuns.

# &#x20; \* Busca e exibição de palavras salvas com percorrimento em largura (por nível).

# 

# \* 🌿 \*\*Árvore N-ária (`PoArvore/nArea`)\*\*:

# &#x20; Estrutura genérica onde cada nó pode suportar um número configurável \*N\* de sub-árvores.

# 

# \### 4. Listas Encadeadas e Generalizadas - `PoListas` \& `PoListaGen`

# \* \*\*Listas Duplamente Encadeadas (`ListaD`)\*\*: Implementação nativa com operações de inserção, remoção, e ordenação completa diretamente nas conexões dos nós.

# \* \*\*Listas Generalizadas\*\*: Uso intenso de recursão e estruturas de suporte (Pilhas) para fazer o \*parsing\* de strings (ex: `\[a, \[b, c], d]`) transformando-as em listas de listas e átomos.

# 

# \---

# 

# \## 🛠️ Tecnologias e Conceitos Aplicados

# \* \*\*Linguagem:\*\* Java (JDK nativo)

# \* \*\*Conceitos Demonstrados:\*\*

# &#x20; \* Complexidade de Algoritmos (Big-O)

# &#x20; \* Manipulação de I/O (Memória Primária vs Secundária)

# &#x20; \* Programação Orientada a Objetos (POO)

# &#x20; \* Alocação Dinâmica e Estruturas Auto-balanceáveis

# &#x20; \* Recursividade

# &#x20; \* Controle minucioso de índices e ponteiros (referências).

# 

# \---



