public class RadixSort{

    public int buscaMaior(int[] vet, int tl) {
        int maior = vet[0];

        for(int i = 1; i < tl; i++) {
            if(vet[i] > maior)
                maior = vet[i];
        }
        return maior;
    }

    public void countingSortDigito(int[] vet, int casa, int tl) {
        int vetC[] = new int[10];
        int vetO[] = new int[tl];
        int digito, pos, e;
        for(int i = 0; i < tl; i++) {
            digito = (vet[i]/casa)%10;
            vetC[digito]++;
        }

        for(int i = 1; i < 10; i++)
            vetC[i] += vetC[i-1];

        for(int i = tl-1; i > -1; i--) {
            digito = (vet[i]/casa)%10;
            pos = --vetC[digito];
            vetO[pos] = vet[i];
        }

        for(int i = 0; i < tl; i++)
            vet[i] = vetO[i];
    }




    public void radixSort(int vet[], int tl) {
        int maior = buscaMaior(vet, tl);

        for(int casa = 1; maior/casa > 0; casa *= 10)
            countingSortDigito(vet, casa, tl);
    }



}