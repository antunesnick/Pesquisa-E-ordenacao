public class DirectSelectionSort {

    int[] vet;
    int i, menor, pos, j,

    for(i = 0; i < TL-1; i++)
    {
        menor = vet[i];
        pos = i;
        for(j = i+1; j < TL; j++)
        {
            if(menor > vet[j])
            {
                menor = vet[j];
                pos = j;
            }
        }
        vet[pos] = vet[i];
        vet[i] = menor;

    }

    Reg novo reg;
    reg.dado = 1
    reg.dado2 = "asdasd";






}