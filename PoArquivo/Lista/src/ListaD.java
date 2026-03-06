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

    private NoLista buscaNoPos(int pos) {
        NoLista aux = ini;
        int cont = 0;

        while(aux != null && cont != pos){
            aux = aux.getProx();
            cont++;
        }

        return aux;
    }


    private int binarySearch(int chave, int tl) {
        int ini = 0; int fim = tl-1, meio = (ini+fim)/2;
        NoLista noMeio;
        noMeio = buscaNoPos(meio);

        while(ini <= fim) {

            if(chave > noMeio.getInfo()) {
                ini = meio+1;
            }
            else
                fim = meio-1;

            meio = (ini+fim)/2;

            noMeio = buscaNoPos(meio);
        }
       return ini;

    }


    public void binaryInsertionSort() {
        int pos; int i;
        i = 1;
        while(i < tamanhoLista) {

            NoLista noAux = buscaNoPos(i);
            int temp = noAux.getInfo();
            NoLista noPos;
            NoLista noAnt;
            pos = binarySearch(temp, i);

            for(int j = i; j > pos; j--) {
                noPos = buscaNoPos(j);
                noAnt = buscaNoPos(j-1);
                noPos.setInfo(noAnt.getInfo());
            }

            noPos = buscaNoPos(pos);
            noPos.setInfo(temp);
            i++;
        }

    }


}
