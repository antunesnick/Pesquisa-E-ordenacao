package sortMethods;

import java.sql.Array;

public class InsertionSort {

    int i, aux, pos;
    int tl;
    int vet[];

    public void InsertionSort()
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




}
