public class BucketSort {

    private void insertionSortB(int[] vet, int tl) {
        int i, pos, aux;
        i = 1;
        while(i < tl) {
            pos = i;
            aux = vet[pos];

            while(pos > 0 && aux < vet[pos-1]){
                vet[pos] = vet[pos-1];
                pos--;
            }

            vet[pos] = aux;
            i++;
        }
    }

    public void bucketSort(int[] vet, int tl) {
        int qtdeB = (int)Math.sqrt(tl);
        int[] tlBaldes = new int[qtdeB];
        int[][] baldes = new int[qtdeB][tl];
        int maior = buscaMaior(vet, tl), posBalde;
        for(int i= 0; i < qtdeB; i++)
            tlBaldes[i] = 0;

        for(int i = 0; i < tl; i++) {
            posBalde = (vet[i]*qtdeB)/(maior+1);
            baldes[posBalde][tlBaldes[posBalde]++] = vet[i];
        }

        for(int i = 0; i < qtdeB; i++) {
            insertionSortB(baldes[i], tlBaldes[i]);
        }

        int k = 0;
        for(int i = 0; i < qtdeB; i++) {
            for (int j = 0; j < tlBaldes[i]; j++) {
                vet[k++] = baldes[i][j];
            }
            tlBaldes[i] = 0;
        }
    }
}