public class ListaD {
    NoLista ini;
    NoLista fim;

    int tamanhoLista = 0;

    public ListaD() {
        this.ini = fim = null;
    }

    void inserirNoInicio(int info) {
        NoLista novoNo = new NoLista(null, this.ini, info);
        if (ini == null) {
            fim = novoNo;
        } else {
            ini.setAnt(novoNo);
        }
        ini = novoNo;
        tamanhoLista++;
    }

    void inserirNoFim(int info) {
        NoLista novoNo = new NoLista(fim, null, info);
        if (fim == null) {
            ini = novoNo;
        } else {
            fim.setProx(novoNo);
        }
        fim = novoNo;
        tamanhoLista++;
    }

    void exibir() {
        NoLista aux = ini;
        while (aux != null) {
            System.out.print(aux.getInfo());
            if(aux.getProx() != null)
                System.out.print(" -> ");
            aux = aux.getProx();
        }
        System.out.println();
    }

    private NoLista buscaNoPos(int pos) {
        NoLista aux = ini;
        int cont = 0;

        while (aux != null && cont != pos) {
            aux = aux.getProx();
            cont++;
        }

        return aux;
    }

    private NoLista searchNode(NoLista atual, int quantidade) {
        int cont = 0;
        NoLista aux = atual;
        if(quantidade > 0) {
            while(cont < quantidade && aux != null) {
                aux = aux.getProx();
                cont++;
            }
        }
        else {
            quantidade = quantidade*-1;
            while(cont < quantidade && aux != null) {
                aux = aux.getAnt();
                cont++;
            }
        }
        return aux;
    }

    private NoLista binarySearch(int chave, int tl) {
        int ini = 0;
        int fim = tl - 1, meio = (ini + fim) / 2, novoMeio, saltos;
        NoLista noMeio = searchNode(this.ini, meio);

        while(ini <= fim) {
            if(chave > noMeio.getInfo())
                ini = meio+1;
            else
                fim = meio-1;


            if(ini <= fim) {
                novoMeio  = (ini+fim)/2;
                saltos = novoMeio - meio;
                noMeio = searchNode(noMeio, saltos);
                meio = novoMeio;
            }
        }
        return searchNode(noMeio, ini-meio);
    }


    public void binaryInsertionSort() {
        NoLista i, j, pos;
        int temp;
        i = ini.getProx();
        int cont = 1;
        while(i != null) {
            pos = i;
            temp = pos.getInfo();
            pos = binarySearch(pos.getInfo(), cont);

            j = i;
            while(j != pos) {
                j.setInfo(j.getAnt().getInfo());
                j = j.getAnt();
            }
            pos.setInfo(temp);
            i = i.getProx();
            cont++;
        }
    }

    public void insertionSort() {
        NoLista i = ini.getProx(); NoLista pos;
        int temp;
        while (i != null) {

            pos = i;
            temp = pos.getInfo();


            while(pos != ini && pos.getAnt().getInfo() > temp) {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }

            pos.setInfo(temp);
            i = i.getProx();
        }
    }

    public void bubbleSort() {
       NoLista i = ini, j;
       boolean flag = true;
       int temp;
       while(i != null && flag) {
           flag = false;
           j = i.getProx();
           while(j != null) {
               if(j.getAnt().getInfo() > j.getInfo()) {
                   temp = j.getAnt().getInfo();
                   j.getAnt().setInfo(j.getInfo());
                   j.setInfo(temp);
                   flag = true;
               }
               j = j.getProx();
           }
           fim = fim.getAnt();
       }
    }

    public void directSelectionSort() {
        NoLista i = ini, j, menor;
        int temp;

        while(i != null) {
            menor = i;
            j = i.getProx();
            while(j != null) {
                if(j.getInfo() < menor.getInfo()) {
                    menor = j;
                }
                j = j.getProx();
            }
            temp = i.getInfo();
            i.setInfo(menor.getInfo());
            menor.setInfo(temp);

            i = i.getProx();
        }
    }

    public void shakeSort() {
        NoLista ini = this.ini, fim = this.fim, i, j;
        int temp;
        boolean flag = true;

        while(ini != fim && flag) {

            i = ini.getProx();
            flag = false;
            while(i != null) {
                if(i.getAnt().getInfo() > i.getInfo()) {
                    temp = i.getInfo();
                    i.setInfo(i.getAnt().getInfo());
                    i.getAnt().setInfo(temp);
                    flag = true;
                }
                i = i.getProx();
            }
            fim = fim.getAnt();
            if(flag) {
                j = fim;
                flag = false;
                while(j.getAnt() != null) {
                    if(j.getInfo() < j.getAnt().getInfo()) {
                        temp = j.getInfo();
                        j.setInfo(j.getAnt().getInfo());
                        j.getAnt().setInfo(temp);
                        flag = true;
                    }
                    j = j.getAnt();
                }
                ini = ini.getProx();
            }
        }
    }

    public void shellSort() {
        int h = 1, i;
        NoLista noPos, noI;

        while(h < tamanhoLista)
            h = h *3+1;

        h = h/3;
        while(h > 0) {

            i = h;
            noI = searchNode(ini, h);

            while(noI != null) {
                noPos = noI;
                int temp = noI.getInfo();
                int pos = i;

                NoLista pAnt = searchNode(noPos, -h);

                while(pos >= h && temp < pAnt.getInfo()) {
                    noPos.setInfo(pAnt.getInfo());
                    noPos = pAnt;
                    pos = pos-h;
                    if(pos >= h) {
                        pAnt = searchNode(pAnt, -h);
                    }
                }
                noPos.setInfo(temp);
                noI = noI.getProx();
                i++;
            }
            h = h/3;
        }
    }

    public void heapSort() {
        int fe, fd, temp, tl;
        int pai;
        tl= tamanhoLista;
        NoLista noTL = fim, noPai, noEsq, noDir, maiorF;

        while(tl > 1) {
            for(pai = tl/2-1; pai >= 0; pai--) {
                noPai = searchNode(ini, pai);
                fe = pai*2+1;
                fd = fe+1;
                noEsq = searchNode(ini, fe);
                maiorF = noEsq;

                if(fd < tl) {
                    noDir = noEsq.getProx();
                    if(noDir.getInfo() > maiorF.getInfo())
                        maiorF = noDir;
                }
                if(maiorF.getInfo() > noPai.getInfo()) {
                    temp = maiorF.getInfo();
                    maiorF.setInfo(noPai.getInfo());
                    noPai.setInfo(temp);
                }
            }
            temp = noTL.getInfo();
            noTL.setInfo(ini.getInfo());
            ini.setInfo(temp);
            tl--;
            noTL = noTL.getAnt();
        }
    }

    private void merge(NoLista esq, NoLista dir, NoLista inicioDir) {

        NoLista i = esq, j = inicioDir;
        NoLista fim = dir.getProx();

        while(i != j && j != fim) {

            if(i.getInfo() <= j.getInfo())
                i = i.getProx();
            else{
                int temp = j.getInfo();

                NoLista aux = j;
                while(aux != i) {
                    aux.setInfo(aux.getAnt().getInfo());
                    aux = aux.getAnt();
                }
                i.setInfo(temp);

                i = i.getProx();
                j = j.getProx();
            }
        }
    }

    private void mergeSort(NoLista esq, NoLista dir, int tamanho) {
        if(tamanho > 1) {
            int tamEsq = tamanho/2;
            int tamDir = tamanho-tamEsq;

            NoLista meio = searchNode(esq, tamEsq-1);
            NoLista inicioDir = meio.getProx();

            mergeSort(esq, meio, tamEsq);
            mergeSort(inicioDir, dir, tamDir);

            merge(esq, dir, inicioDir);

        }

    }

    public void mergeSort() {
        mergeSort(ini, fim, tamanhoLista);
    }


    public void quickSort() {
        quickSort(ini, fim);

    }

    private void quickSort(NoLista ini, NoLista fim){
        NoLista i = ini, j = fim;
        boolean flag = true;

        while(i != j) {
            if(flag)
                while(i != j && i.getInfo() < j.getInfo())
                    i = i.getProx();
            else
                while(j != i && j.getInfo() > i.getInfo())
                    j = j.getAnt();
            int temp = i.getInfo();
            i.setInfo(j.getInfo());
            j.setInfo(temp);
            flag = !flag;
        }
        if(ini != i && ini != i.getAnt())
            quickSort(ini, i.getAnt());
        if(j != fim && fim != j.getProx())
            quickSort(j.getProx(), fim);
    }

    private void quickSortPivot(NoLista ini, NoLista fim) {
        int pivot = ini.getInfo(), temp;
        NoLista esq = ini, dir = fim;

        while(esq != dir) {

            while(esq != dir && esq.getInfo() < pivot)
                esq = esq.getProx();

            while(esq != dir && dir.getInfo() > pivot)
                dir = dir.getAnt();

            temp = esq.getInfo();
            esq.setInfo(dir.getInfo());
            dir.setInfo(temp);
        }

        if(esq != ini && esq.getAnt() != ini)
            quickSortPivot(ini, esq.getAnt());
        if(dir != fim && dir.getProx() != fim)
            quickSortPivot(dir.getProx(), fim);
    }

    public void quickSortPivot() {
        quickSortPivot(this.ini, this.fim);
    }
}
