public class MergeSort {

    public void MergeSort(int ini, int fim, int[] vet, int[] aux) {

        if(ini <  fim) {
            int meio = (ini + fim) / 2;
            MergeSort(ini, meio, vet, aux);
            MergeSort(meio + 1, fim, vet, aux);

            for(int k = ini; k <= fim; k++)
                aux[k] = vet[k];

            int i = ini;
            int j = meio+1;


            for (int k = ini; k <=fim; k++){
                if (i > meio)
                    vet[k] = aux[j++];
                else if (j > fim)
                    vet[k] = aux[i++];
                else if (aux[i] < aux[j]) {
                    vet[k] = aux[i++];
                } else
                    vet[k] = aux[j++];
            }
        }
    }




}