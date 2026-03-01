package sortMethods;

import java.sql.Array;

public class InsertionSort {

    int i, aux, pos;
    int tl;
    int vet[];

    public void insertionSort()
    {
        i = 1;
        while(i != tl)
        {
            pos = i;
            aux = vet[pos];

            while(pos > 0 && aux < vet[pos-1])
            {
                vet[pos] = vet[pos-1];
                pos--;
            }
            vet[pos] = aux;
        }
        i++;
    }

    public void insertionSortL()
    {
        Lista i = lista.inicio.getProx();
        int aux;
        Lista pos;

        while(i != null)
        {
            pos = i;
            aux = i.getInfo();

            while(pos != inicio && aux < pos.getAnt().getInfo())
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }

            pos.setInfo(aux);
            i = i.getProx();
        }



    }


}
