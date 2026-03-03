public class ShellSort{
    int tl = 10;
    int i, h , j;
    int[] vet;

    h=1;
    while(h < tl)
        h = h*3+1;

    while(h > 1) {
        h = h/3;
        for(i= h; i < tl-1; i++) {
            int temp = vet[i];
            j = i;
            while(j >= h && vet[j-h] > temp) {
                vet[j] = vet[j-h];
                j = j-h;
            }
            vet[j] = temp;
        }
    }

}