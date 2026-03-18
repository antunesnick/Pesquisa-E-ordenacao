public class GnomeSort() {
    public void gnomeSort(int[] vet, int tl) {
        int i = 1, temp;
        while(i < tl) {
            if(i = 0) {
                i++;
            }
            else if(vet[i] <= vet[i-1]) {
                temp = vet[i];
                vet[i] = vet[i-1];
                vet[i-1] = temp;
                i--;
            }
            else
                i++;
        }
    }
}