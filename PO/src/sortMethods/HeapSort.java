public class HeapSort {


    int tl2 = tl, pai, f1, f2, maiorF, aux;


    while(tl2 > 1) {
        for(pai = tl2/2-1; pai >= 0; pai--) {
            f1 = pai*2+1;
            f2 = f1+1;
            maiorF = f1;

            if (f2 < tl2 && vet[f2] > vet[f1])
                maiorF = f2;
            if (vet[maiorF] > vet[pai]) {
                aux = vet[maiorF];
                vet[maiorF] = vet[pai];
                vet[pai] = aux;
            }


        }

        aux = vet[0];
        vet[0] = vet[tl2-1];
        vet[tl2-1] = aux;
        tl2--;
    }
}