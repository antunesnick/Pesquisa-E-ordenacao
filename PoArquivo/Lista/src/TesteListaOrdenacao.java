import java.util.Random;

public class TesteListaOrdenacao {
    public static void main(String[] args) {
        ListaD minhaLista = new ListaD();
        Random gerador = new Random();

        // Quantidade de nós que queremos gerar
        int quantidadeRegistros = 8192*1024;

        System.out.println("Gerando " + quantidadeRegistros + " nos desordenados na lista...");

        // Laço para gerar e inserir os números aleatórios
        for (int i = 0; i < quantidadeRegistros; i++) {
            // Sorteia um número entre 1 e 9999
            int numero = gerador.nextInt(999999999) + 1;
            minhaLista.inserirNoFim(numero);
        }

        System.out.println("Lista criada com sucesso na memoria RAM!");

        // Exibir os primeiros 15 para ver a bagunça gerada
        System.out.println("\n--- Amostra da Lista Desordenada (Primeiros 15) ---");
        exibirAmostra(minhaLista, 15);

        System.out.println("\nIniciando a ordenacao...");

        // =========================================================
        // AQUI VOCÊ ESCOLHE QUAL ALGORITMO QUER TESTAR!
        // =========================================================
        minhaLista.quickSortPivot(); // Testando a sua obra-prima iterativa
        // minhaLista.quickSortPivot();
        // minhaLista.radixSort();
        // minhaLista.bucketSort();

        System.out.println("\nOrdenacao concluida! Exibindo os 15 primeiros ordenados:");
        exibirAmostra(minhaLista, 15);
    }

    // Método auxiliar para não floodar o console caso a lista tenha milhares de itens
    private static void exibirAmostra(ListaD lista, int limite) {
        NoLista aux = lista.ini;
        int cont = 0;

        while (aux != null && cont < limite) {
            System.out.print(aux.getInfo());
            if (aux.getProx() != null && cont < limite - 1) {
                System.out.print(" -> ");
            }
            aux = aux.getProx();
            cont++;
        }
        System.out.println();
    }
}