public class TimSort {

    private void mergeSort(int[] vet, int esq, int meio, int dir) {
        if(esq < dir){
            int i, j, k, tlE = meio-esq+1, tlD = dir-meio;
            int[] L = new int[tlE];
            int[] R = new int[tlD];


            for(i = 0; i < tlE; i++) {
                L[i] = vet[esq+i];
            }
            for(j = 0; j < tlD; j++)
                R[j] = vet[meio+1+j];

            i = 0;
            j = 0;
            k = esq;
            while(i < tlE && j < tlD) {
                if(L[i] <= R[j])
                    vet[k++] = L[i++];
                else
                    vet[k++] = R[j++];
            }
            while(i <= tlE)
                vet[k++] = L[i++];
            while(j <= tlD)
                vet[k++] = R[j++];
        }
    }


    private void insertionSortT(int[] vet, int esq, int dir) {
        int temp, pos , i = esq+1;

        while(i <= dir) {
            pos = i;
            temp = vet[pos];

            while(pos > esq && temp < vet[pos-1]) {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = temp;
            i++;
        }
    }


    public void timSort(int vet[], int tl) {
        final int div = 32;
        int dir, meio, fim;

        for(int i = 0; i < tl; i+=32) {
            if(i + div-1 < tl-1)
                fim = i+div-1;
            else
                fim = tl-1;
            insertionSortT(vet, i, fim);
        }
        for(int tam = div; tam < tl; tam*=2) {
            for(int esq = 0; esq < tl; esq+= 2*tam) {
                meio = esq+tam-1;
                if(esq+2*tam-1 < tl-1)
                    dir = esq+2*tam-1;
                else
                    dir = tl-1;
                if(meio < dir)
                    mergeSort(vet, esq, meio, dir);
            }
        }
    }


}