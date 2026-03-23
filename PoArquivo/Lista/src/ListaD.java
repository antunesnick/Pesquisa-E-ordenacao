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

    private void mergeSort(NoLista esq, NoLista dir, NoLista meioAnt,  int idxEsq, int idxMeioAnt, int idxDir) {
        if(idxEsq < idxDir) {
            ListaD temp = new ListaD();
            int idxMeioAtual = (idxEsq+idxDir)/2;
            NoLista meioAtual = searchNode(meioAnt, idxMeioAtual-idxMeioAnt);
            mergeSort(esq, meioAtual, meioAtual, idxEsq, idxMeioAtual, idxMeioAtual);
            mergeSort(meioAtual.getProx(), dir, meioAtual, idxMeioAtual+1, idxMeioAtual, idxDir);

            int i, j;
            NoLista noK, noI, noJ;
            i = idxEsq; j = idxMeioAtual+1;
            noI = esq; noJ = meioAtual.getProx();
            while(i <= idxMeioAtual && j <= idxDir) {
                if(noI.getInfo() < noJ.getInfo()){
                    temp.inserirNoFim(noI.getInfo());
                    noI = noI.getProx();
                    i++;
                }
                else {
                    temp.inserirNoFim(noJ.getInfo());
                    noJ = noJ.getProx();
                    j++;
                }
            }
            while(i <= idxMeioAtual) {
                temp.inserirNoFim(noI.getInfo());
                noI = noI.getProx();
                i++;
            }
            while(j <= idxDir) {
                temp.inserirNoFim(noJ.getInfo());
                noJ = noJ.getProx();
                j++;
            }

            noK = temp.ini;
            noI = esq;
            while(noK != null) {
                noI.setInfo(noK.getInfo());
                noK = noK.getProx();
                noI = noI.getProx();
            }
        }
    }

    public void mergeSort() {
        int idxEsq = 0, idxDir = tamanhoLista-1;
        int idxMeio = tamanhoLista/2;
        NoLista meio = searchNode(ini, idxMeio);
        mergeSort(ini, fim, meio, idxEsq, idxMeio, idxDir);
    }

    private void particao(ListaD l, ListaD l2, ListaD l3) {
        NoLista ini = l.ini;
        NoLista fim= l.fim;
        while(ini != fim && ini.getAnt() != fim) {
            l3.inserirNoFim(ini.getInfo());
            l2.inserirNoInicio(fim.getInfo());
            ini = ini.getProx();
            fim = fim.getAnt();
        }
        if(ini == fim)
            l3.inserirNoFim(ini.getInfo());
    }

    private void fusao(ListaD l, ListaD l2, ListaD l3, int seq) {
        int tamSeq = seq;
        int i = 0, j = 0, k = 0;
        NoLista noL = l.ini, noI = l2.ini, noJ = l3.ini;
        while(k < l.tamanhoLista) {
            while(i < seq && j < seq && noI != null && noJ != null) {
                if(noI.getInfo() < noJ.getInfo())
                {
                    noL.setInfo(noI.getInfo());
                    noI = noI.getProx();
                    i++;
                }
                else {
                    noL.setInfo(noJ.getInfo());
                    noJ = noJ.getProx();
                    j++;
                }
                noL = noL.getProx();
                k++;
            }
            while(i < seq && noI != null) {
                noL.setInfo(noI.getInfo());
                noI = noI.getProx();
                noL = noL.getProx();
                i++;
                k++;
            }

            while(j < seq && noJ != null) {
                noL.setInfo(noJ.getInfo());
                noL = noL.getProx();
                noJ = noJ.getProx();
                j++;
                k++;
            }
            seq += tamSeq;
        }
    }

    public void merge2() {
        int seq = 1, tl = tamanhoLista;

        while(seq < tl) {
            ListaD l2 = new ListaD(), l3 = new ListaD();
            particao(this, l2, l3);
            fusao(this, l2, l3, seq);
            seq *=2;

        }
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

    private void quickSortPivot(NoLista ini, NoLista fim, int idxIni, int idxFim) {
        int pivot = ini.getInfo(), temp;
        int idxEsq = idxIni, idxDir = idxFim;
        NoLista esq = ini, dir = fim;

        while(idxEsq <= idxDir) {
            while(idxEsq <= idxDir && esq.getInfo() < pivot) {
                esq = esq.getProx();
                idxEsq++;
            }
            while(idxEsq <= idxDir && dir.getInfo() > pivot){
                dir = dir.getAnt();
                idxDir--;
            }
            if(idxEsq <= idxDir) {
                temp = esq.getInfo();
                esq.setInfo(dir.getInfo());
                dir.setInfo(temp);
                esq = esq.getProx();
                idxEsq++;
                dir = dir.getAnt();
                idxDir--;
            }
        }
        if(idxIni < idxDir)
            quickSortPivot(ini, dir, idxIni, idxDir);
        if(idxEsq < idxFim)
            quickSortPivot(esq, fim, idxEsq, idxFim);
    }

    public void quickSortPivot() {
        quickSortPivot(this.ini, this.fim, 0, tamanhoLista-1);
    }

    private int buscaMaior() {
        int maior = ini.getInfo();
        NoLista aux = ini.getProx();

        while(aux != null) {
            if(aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        return maior;
    }

    public void countingSort() {
        int tlC = buscaMaior()+1, posInserir, e;
        int[] vetC = new int[tlC];
        ListaD temp = new ListaD();
        NoLista aux = ini, aux2;

        for(int i = 0; i < tamanhoLista; i++)
            temp.inserirNoFim(0);

        while(aux != null) {
            vetC[aux.getInfo()]++;
            aux = aux.getProx();
        }

        for(int i = 1; i < tlC; i++) {
            vetC[i] += vetC[i-1];
        }

        aux = fim;
        aux2 = temp.ini;
        int posAtualAux2 = 0;
        int novaPosAux2;
        while (aux != null) {
            e = aux.getInfo();
            posInserir = --vetC[e];

            novaPosAux2 = posInserir-posAtualAux2;
            aux2 = searchNode(aux2, novaPosAux2);
            posAtualAux2 = posInserir;

            aux2.setInfo(e);

            aux = aux.getAnt();
        }

        aux = ini;
        aux2 = temp.ini;
        while(aux != null){
            aux.setInfo(aux2.getInfo());
            aux = aux.getProx();
            aux2 = aux2.getProx();
        }
    }


    private void countingSortDigito(int casa, ListaD temp) {
        int pos, digito, passos;
        int[] vetC = new int[10];
        NoLista auxTemp, aux = ini;

        while(aux != null) {
            digito = (aux.getInfo() / casa) % 10;
            vetC[digito]++;
            aux = aux.getProx();
        }

        for(int i = 1; i < 10; i++) {
            vetC[i] += vetC[i-1];
        }

        aux = fim;
        auxTemp = temp.ini;
        int posTemp = 0;
        while(aux != null) {
            digito = (aux.getInfo()/casa)%10;
            pos = --vetC[digito];

            passos = pos - posTemp;
            posTemp = pos;

            auxTemp = searchNode(auxTemp, passos);
            auxTemp.setInfo(aux.getInfo());

            aux = aux.getAnt();
        }

        aux = ini;
        auxTemp = temp.ini;
        while(aux != null) {
            aux.setInfo(auxTemp.getInfo());
            aux = aux.getProx();
            auxTemp = auxTemp.getProx();
        }
    }

    public void radixSort() {
        int maior = buscaMaior();

        ListaD temp = new ListaD();
        for(int i = 0; i < tamanhoLista; i++) {
            temp.inserirNoInicio(0);
        }

        for(int casa = 1; maior/casa > 0; casa *= 10) {
            countingSortDigito(casa, temp);
        }
    }

    public void combSort() {
        int gap = tamanhoLista, temp;
        boolean flag = true;
        NoLista aux, aux2;

        while(gap > 1 || flag) {
            gap = (int)(gap/1.3);
            if(gap < 1)
                gap = 1;

            flag = false;

            aux = ini;
            aux2 = searchNode(aux, gap);
            while(aux2 != null) {
                if(aux.getInfo() > aux2.getInfo()) {
                    temp = aux.getInfo();
                    aux.setInfo(aux2.getInfo());
                    aux2.setInfo(temp);
                    flag = true;
                }
                aux = aux.getProx();
                aux2 = aux2.getProx();
            }
        }
    }

    public void gnomeSort() {
        int temp;
        NoLista aux = ini.getProx();

        while(aux != null) {
            if(aux.getAnt() == null)
                aux = aux.getProx();
            else if(aux.getInfo() >= aux.getAnt().getInfo())
                aux = aux.getProx();
            else {
                temp = aux.getInfo();
                aux.setInfo(aux.getAnt().getInfo());
                aux.getAnt().setInfo(temp);
                aux = aux.getAnt();
            }
        }
    }

    private void insertionSortB(ListaD temp) {
       NoLista pos, i = temp.ini.getProx();
       int tempI;

       while(i != null) {
           pos = i;
           tempI = i.getInfo();

           while(pos != temp.ini && tempI < pos.getAnt().getInfo()) {
               pos.setInfo(pos.getAnt().getInfo());
               pos = pos.getAnt();
           }

           pos.setInfo(tempI);
           i = i.getProx();
       }
    }

    public void bucketSort() {
        int pos, maior = buscaMaior(), qtdB = (int)Math.sqrt(tamanhoLista);
        if(qtdB == 0)
            qtdB = 1;
        NoLista aux, aux2;
        ListaD[] buckets = new ListaD[qtdB];

        for(int i = 0; i < qtdB; i++) {
            buckets[i] = new ListaD();
        }

        aux = ini;
        while(aux != null) {
            pos = ((aux.getInfo()*qtdB)/(maior+1));
            buckets[pos].inserirNoFim(aux.getInfo());
            aux = aux.getProx();
        }

        for(int i = 0; i < qtdB; i++) {
            insertionSortB(buckets[i]);
        }

        aux = ini;
        for(int i = 0; i < qtdB; i++) {
            aux2 = buckets[i].ini;
            for(int j = 0; j < buckets[i].tamanhoLista; j++) {
                aux.setInfo(aux2.getInfo());
                aux2 = aux2.getProx();
                aux = aux.getProx();
            }
        }
    }

    private void insertionSortT(int ini, int fim) {
        int i = ini, pos, temp;
        NoLista noPos, pAnt;

        while(i <= fim) {
            pos = i;
            noPos = searchNode(this.ini, pos);
            temp = noPos.getInfo();

            pAnt = searchNode(noPos, -1);

            while(pos > 0 && temp < pAnt.getInfo()) {
                noPos.setInfo(pAnt.getInfo());
                noPos = pAnt;
                pos--;
                if(pos > 0)
                    pAnt = searchNode(noPos, -1);
            }

            noPos.setInfo(temp);
            i++;
        }
    }

    private void mergSortT(int ini, int meio, int fim) {
        int i = ini;
        int j = meio+1;
        int k = 0;
        ListaD temp = new ListaD();
        NoLista noI = searchNode(this.ini, i);
        NoLista noAux = noI;
        NoLista noJ = searchNode(noI, j);

        while(i <= meio && j <= fim) {
            if(noI.getInfo() < noJ.getInfo()) {
                temp.inserirNoFim(noI.getInfo());
                k++;
                i++;
                noI = noI.getProx();
            }
            else {
                temp.inserirNoFim(noJ.getInfo());
                k++;
                j++;
                noJ = noJ.getProx();
            }
        }
        while(i <= meio) {
            temp.inserirNoFim(noI.getInfo());
            i++;
            noI = noI.getProx();
            k++;
        }
        while(j <= fim) {
            temp.inserirNoFim(noJ.getInfo());
            j++;
            noJ = noJ.getProx();
            k++;
        }

        noI = temp.ini;
        while(noI != null) {
            noAux.setInfo(noI.getInfo());
            noAux = noAux.getProx();
            noI = noI.getProx();
        }
    }

    public void timSort() {
        final int tam = 32;
        int tl = tamanhoLista, fim;
        for(int i = 0; i < tl; i += tam) {
            if(i+tam-1 < tl-1)
                fim = i+tam-1;
            else
                fim = tl-1;

            insertionSortT(i, fim);
        }

        for(int i = tam; i < tl; i = tam*2) {
            for(int j = 0; j < tl; j += i*2) {
                int meio = j+i-1;
                if(j+tam*2-1 < tl-1)
                    fim = j+tam*2-1;
                else
                    fim = tl-1;

                mergSortT(j,meio, fim);
            }
        }
    }
}
