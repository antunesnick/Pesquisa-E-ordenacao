package sortMethods;

public class BinaryInsertionSort {

    public void busca_binaria(int chave, int tl)
    {
        int ini = 0; fim = tl-1, meio = fim/2;

        while(ini < fim)
        {
            if(vet[meio] > chave)
                ini = meio+1;
            else
                fim = meio-1;
            meio = (ini+fim)/2
        }
        if(chave > vet[meio])
            return meio+1;
        else
            return meio;

    }

    public void insercaoDireta()
    {
        int i;
        int vet[];
        int tl;
        int pos;


        while(i != tl)
        {
            aux = vet[i];
            pos = busca_binaria(aux,i);

            for(int j = i; j > pos; j--)
            {
                vet[j] = vet[j-1];
            }
            vet[j] = aux;

            vet[pos] = aux;
            i++;
        }

    }



}