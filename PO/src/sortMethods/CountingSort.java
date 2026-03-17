public class CountingSort {
    public int buscaMaior(int[] vet, int tl){
        int maior = vet[0];
        for(int i = 1; i < tl; i++) {
            if (vet[i] > maior)
                maior = vet[i];
        }
        return maior;
    }

    public void count(int[] vet, int[] vetC, int tl) {
        for(int i = 0; i < tl; i++) {
            vetC[vet[i]]++;
        }
    }

    public void soma(int[] vetC, int tlC) {
        for(int i = 1; i < tlC; i++)
            vetC[i] += vetC[i-1];
    }

    public void CountingSort(int[] vet, int tl){
        int tlC = buscaMaior(vet,tl)+1, e, pos;
        int[] vetC = new int[tlC];
        int[] vetO = new int[tl];

        for(int i = 0; i < tlC; i++)
            vetC[i] = 0;

        count(vet, vetC, tl);
        soma(vetC, tlC);

        for(int i = tl-1; i > -1; i--) {
            e = vet[i];
            pos = vetC[e]--;
            vetO[pos-1] = e;
        }

        for(int i = 0; i < tl; i++) {
            vet[i] = vetO[i];
        }

    }
}