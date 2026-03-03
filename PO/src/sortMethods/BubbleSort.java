package sortMethods;

public class BubbleSort{

    int i;
    int temp, flag;
    int tl2;
    int[] vet;

    while(tl2 > 1 && flag == 1)
    {
        i = 0;
        flag = 0;
        while(i < tl2-1)
        {
            if(vet[i] > vet[i+1])
            {
                temp = vet[i+1];
                vet[i+1] = vet[i];
                vet[i] = temp;
                flag = 1;
            }
            i++;
        }

        tl2--;
    }


}