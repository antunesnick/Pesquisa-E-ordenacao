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
        int h, i, pos, tl = tamanhoLista;
        NoLista noPos, noAux;
        h =1;
        while(h < tamanhoLista)
            h = h*3 +1;

        while(h > 1) {
            h = h/3;

            for(i = h; i < tl; i++) {
                int temp = buscaNoPos(i).getInfo();
                pos = i;


                while(pos >= h && temp < buscaNoPos(pos-h).getInfo()) {
                    buscaNoPos(pos).setInfo(buscaNoPos(pos-h).getInfo());
                    pos = pos-h;
                }

                noPos = buscaNoPos(pos);
                noPos.setInfo(temp);
            }
        }

    }

    public void heapSort() {
        int tl = tamanhoLista-1;
        int fe, fd, maiorf, temp;
        NoLista noMaior, noFe, noFd, noPai;
        while(tl > 1) {

            for(int pai = tl/2-1; pai > 0; pai--) {
                fe = pai * 2+1;
                fd = fe+1;
                noFe = buscaNoPos(fe);
                noPai = buscaNoPos(pai);
                maiorf = fe;
                noMaior = noFe;

                if(fd < tl) {
                    noFd = buscaNoPos(fd);
                    if(noFd.getInfo() > noMaior.getInfo()) {
                        maiorf = fd;
                        noMaior = noFd;
                    }
                }

                if(noMaior.getInfo() > noPai.getInfo()) {
                    temp = noMaior.getInfo();
                    noMaior.setInfo(noPai.getInfo());
                    noPai.setInfo(temp);
                }
            }

            noFe = buscaNoPos(0);
            noFd = buscaNoPos(tl);

            temp = noFe.getInfo();
            noFe.setInfo(noFd.getInfo());
            noFd.setInfo(temp);
            tl--;
        }


    }

    private NoLista meioMerge(NoLista cabeca) {
        if(cabeca == null)
            return cabeca;

        NoLista lento = cabeca;
        NoLista rapido = cabeca;

        while(rapido.getProx() != null && rapido.getProx().getProx() != null) {
            lento = lento.getProx();
            rapido = rapido.getProx().getProx();
        }

        return lento;
    }

    private NoLista merge(NoLista esq, NoLista dir) {
        NoLista resultado;

        if(esq == null)
            return dir;
        if(dir == null)
            return esq;

        if(esq.getInfo() < dir.getInfo()) {
            resultado = esq;
            resultado.setProx(merge(esq.getProx(), dir));
            if(resultado.getProx() != null)
                resultado.getProx().setAnt(resultado);
            resultado.setAnt(null);
        }
        else {
            resultado = dir;
            resultado.setProx(merge(esq, dir.getProx()));
            if(resultado.getProx() != null)
                resultado.getProx().setAnt(resultado);

            resultado.setAnt(null);
        }
        return resultado;
    }

    public void mergeSort(int ini, int fim, ListaD l, ListaD aux) {

        if(ini < fim) {
            int meio = (ini+fim)/2;
            mergeSort(ini, meio, l, aux);
            mergeSort(meio+1, fim, l, aux);

            for(int k = ini; k <= fim; k++) {
                aux.buscaNoPos(k).setInfo(l.buscaNoPos(k).getInfo());
            }

            int i = ini;
            int j = meio+1;

            for(int k = ini; k <= fim; k++) {
                if(i > meio)
                    l.buscaNoPos(k).setInfo(aux.buscaNoPos(j++).getInfo());
                else if(j > fim)
                    l.buscaNoPos(k).setInfo(aux.buscaNoPos(i++).getInfo());
                else
                    if(aux.buscaNoPos(i).getInfo() < aux.buscaNoPos(j).getInfo())
                        l.buscaNoPos(k).setInfo(aux.buscaNoPos(i++).getInfo());
                    else
                        l.buscaNoPos(k).setInfo(aux.buscaNoPos(j++).getInfo());
            }
        }
    }

    public void mergeSort() {

        int ini = 0, fim = tamanhoLista-1;
        ListaD aux = new ListaD();
        for(int i = 0; i < tamanhoLista; i++) {
            aux.inserirNoFim(0);
        }

        mergeSort(ini, fim, this, aux);

    }

    public NoLista mergeSort(NoLista cabeca) {
        if(cabeca == null || cabeca.getProx() == null)
            return cabeca;

        NoLista meio = meioMerge(cabeca);
        NoLista iniDir = meio.getProx();
        meio.setProx(null);
        if(iniDir != null)
            iniDir.setAnt(null);

        NoLista esq = mergeSort(cabeca);
        NoLista dir = mergeSort(iniDir);

        return merge(esq, dir);
    }

    public void mergeSort(char a) {
        ini = mergeSort(ini);
        NoLista aux = ini;
        while(aux.getProx() != null)
            aux = aux.getProx();
        fim = aux;
    }


}
