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



}