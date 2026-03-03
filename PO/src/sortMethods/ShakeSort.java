package sortMethods;

public class ShakeSort{

    int ini = 0, fim = tl-1;
    int aux;
    boolean flag = true;


    while(ini < fim && flag)
    {
        flag = false;
        for(int i = ini; i < fim; i++)
        {
            if(vet[i] > vet[i+1])
            {
                aux = vet[i];
                vet[i] = vet[i+1];
                vet[i+1] = aux;
                flag = true;
            }
        }
        fim--;

        if(flag)
        {
            flag = false;
            for(int i = fim; i > ini; i--)
            {
                if(vet[i] < vet[i-1])
                {
                    aux = vet[i];
                    vet[i] = vet[i-1];
                    vet[i-1] = aux;
                    flag = true;
                }
            }
            i++;
        }

    }


}
