public class mergeSort2 {


    private void particao(int vet1[], int vet2[], int vet3[], int TL){
        int tlP = tl/2;

        for(int i = 0; i < tlP; i++) {
            vet3[i] = vet1[i];
            vet3[i+meio] = vet2[i];
        }

    }

    private void fusao(int vet1[], int vet2[], int vet3[] int seq) {
        int i = 0, j = 0, k = 0, seqAux = seq;
        while(k < tl) {
            while(i < seq && j < seq) {
                if(vet1[i] < vet2[j])
                    vet[k++] = vet1[i++];
                else
                    vet[k++] = vet2[j++];
            }
            while(i < seq)
                vet[k++] = vet1[i++];

            while(j < seq)
                vet[k++] = vet2[j++];

            seq = seq+seqAux;
        }
    }


    public void merge() {
        int seq = 1;
        int vet1[] = new int[TL/2];
        int vet2[]   = new int[TL/2];
        while(seq < TL) {
            particao(vet1, vet2);
            fusao(vet1, vet2, seq);
            seq = seq*2;
        }




    }








}