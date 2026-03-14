public class QuickSort {

    int[] vet;
    int tl, temp;
    int left, right, pivot;

    public quickSort(int[] vet, int ini, int fim) {
        pivot = vet[ini];
        int left = ini;
        int right = fim
        while(left <= right) {

            while(vet[left] < pivot) left++;
            while(vet[right] >= pivot) right--;

            if(left <= right) {
                temp = vet[left];
                vet[left] = vet[right];
                vet[right] = vet[left];
                left++;
                right--;
            }

            if(ini < left) quickSort(vet, ini, r);
            if(fim < right) quickSort(vet, left, fim);

        }

    }



    private void quickSemPivo(int ini, int fim) {
        int i = ini, j = fim;

        if (ini < fim) {
            while (i < j) {
                while (vet[i] <= vet[j] && i < j)
                    i++;
                if (i < j) {
                    int temp = vet[i];
                    vet[i] = vet[j];
                    vet[j] = temp;
                }
                while (vet[j] >= vet[i] && j > i) {
                    j--;
                }
                if (j > i) {
                    int temp = vet[i];
                    vet[i] = vet[j];
                    vet[j] = temp;
                }
            }
            quickSemPivo(ini, i - 1);
            quickSemPivo(j + 1, fim);
        }
    }

    public void quickSemPivo()
    {
        quickSemPivo(0, tl-1);
    }


}