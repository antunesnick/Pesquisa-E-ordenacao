public class CombSort{


    public void combSort(int vet[], int tl) {
        int gap = tl, temp;
        boolean flag = true;

        while(gap > 1 || flag) {
            gap = (int) gap/1.3;
            if(gap < 1)
                gap = 1;

            flag = false;
            for(int i = 0; i + gap < tl; i++) {
                if(vet[i] > vet[i + gap]) {
                    temp = vet[i];
                    vet[i] = vet[i+gap];
                    vet[i+gap] = temp;
                    flag = true;
                }
            }
        }
    }
}