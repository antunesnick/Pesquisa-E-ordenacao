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

    public void insertionSort() {
        int i = 1, pos, temp;

        while(i < tamanhoLista) {

            pos = i;
            temp = buscaNoPos(pos).getInfo();

            while(pos > 0 && temp < buscaNoPos(pos-1).getInfo()) {
                buscaNoPos(pos).setInfo(buscaNoPos(pos-1).getInfo());
                pos--;
            }


            buscaNoPos(pos).setInfo(temp);
            i++;
        }
    }

    public void bubbleSort() {
        int i = 0, j, tl = tamanhoLista-1;
        NoLista aux, aux2;
        int temp;
        boolean flag = true;

        while(tl >0 && flag) {

            j = 0;
            flag = false;
            while(j < tl) {

                aux = buscaNoPos(j);
                aux2 = buscaNoPos(j+1);
                if(aux.getInfo() > aux2.getInfo())
                {
                    temp = aux2.getInfo();
                    aux2.setInfo(aux.getInfo());
                    aux.setInfo(temp);
                    flag = true;
                }
                j++;
            }
            tl--;
        }
    }

    public void directSelectionSort() {


        for(int i = 0; i < tamanhoLista; i++) {
            NoLista auxI = buscaNoPos(i);
            int menor = auxI.getInfo();
            int posMenor = i;
            NoLista auxJ;
            for(int j = i+1; j < tamanhoLista; j++) {
                auxJ = buscaNoPos(j);
                if(auxJ.getInfo() < menor) {
                    menor = auxJ.getInfo();
                    posMenor = j;
                }
            }
            buscaNoPos(posMenor).setInfo(auxI.getInfo());
            auxI.setInfo(menor);
        }
    }

    public void shakeSort() {
        int ini = 0, fim = tamanhoLista-1, temp;
        NoLista noI, noJ, aux;
        boolean flag = true;
        while(ini < fim && flag) {
            flag = false;
            for(int i = ini; i < fim; i++) {
                noI = buscaNoPos(i);
                aux = buscaNoPos(i+1);
                if(noI.getInfo() > aux.getInfo()) {
                    temp = noI.getInfo();
                    noI.setInfo(aux.getInfo());
                    aux.setInfo(temp);
                    flag = true;
                }
            }

            fim--;

            if(flag) {
                flag = false;

                for(int j = fim; j > ini; j--) {
                    noJ = buscaNoPos(j);
                    aux = buscaNoPos(j-1);

                    if(noJ.getInfo() < aux.getInfo()) {
                        temp = noJ.getInfo();
                        noJ.setInfo(aux.getInfo());
                        aux.setInfo(temp);
                        flag = true;
                    }
                }
                ini++;
            }

        }

    }

    public void shellSort() {
        int h, i, pos, tl = tamanhoLista-1;
        NoLista noPos, noAux;
        h =1;
        while(h < tamanhoLista)
            h = h*3 +1;

        while(h > 1) {
            h = h/3;

            for(i = h; i < tl; i++) {
                pos = i;
                noPos = buscaNoPos(pos);
                int temp = noPos.getInfo();
                noPos = buscaNoPos(pos-h);

                while(pos >= h && temp < noPos.getInfo()) {
                    buscaNoPos(pos).setInfo(noPos.getInfo());
                    pos = pos-h;
                    if(pos >= h)
                        noPos = buscaNoPos(pos);
                }

                noPos.setInfo(temp);
            }

        }

    }

}
