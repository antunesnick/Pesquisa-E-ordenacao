public class ListaD {
    NoLista ini;
    NoLista fim;

    int tamanhoLista = 0;

    public ListaD() {
        this.ini = fim = null;
    }

    void inserirNoInicio(int info)
    {
        NoLista novoNo = new NoLista(null, this.ini, info);
        if(ini == null)
        {
            fim = novoNo;
        }
        else
        {
            ini.setAnt(novoNo);
        }
        ini = novoNo;
        tamanhoLista++;
    }

    void inserirNoFim(int info)
    {
        NoLista novoNo =  new NoLista(fim, null, info);
        if(fim == null) {
            ini = novoNo;
        }
        else {
            fim.setProx(novoNo);
        }
        fim = novoNo;
        tamanhoLista++;
    }

    void exibir() {
        NoLista aux = ini;
        while(aux != null)
        {
            System.out.print(aux.getInfo() + " -> ");
            aux = aux.getProx();
        }
        System.out.println();
    }



}
