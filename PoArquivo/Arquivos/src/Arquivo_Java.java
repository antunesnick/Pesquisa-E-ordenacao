    import java.io.RandomAccessFile;
    import java.io.IOException;

    public class  Arquivo_Java {
        private String nomearquivo;
        private RandomAccessFile arquivo;
        private int comparacoes;
        private int movimentacoes;

        public Arquivo_Java(String nomearquivo) {
            try {
                arquivo = new RandomAccessFile(nomearquivo, "rw");
            } catch (IOException e) {
            }
        }

        public void truncate(long pos) //desloca eof
        {
            try {
                arquivo.setLength(pos * Registro.length());
            } catch (IOException exc) {
            }
        }

        //semelhante ao feof() da linguagem C
        //verifica se o ponteiro esta no <EOF> do arquivo
        public boolean eof() {
            boolean retorno = false;
            try {
                if (arquivo.getFilePointer() == arquivo.length())
                    retorno = true;
            } catch (IOException e) {
            }
            return (retorno);
        }

        public int filesize() {
            try {
                return (int) arquivo.length() / Registro.length();
            } catch (IOException e) {
            }
            return 0;
        }


        //insere um Registro no final do arquivo, passado por par�metro
        public void inserirRegNoFinal(Registro reg) {
            seekArq(filesize());//ultimo byte
            reg.gravaNoArq(arquivo);
        }

        public void exibirArq() {
            int i;
            Registro aux = new Registro();
            seekArq(0);
            i = 0;
            while (!this.eof()) {
                System.out.println("Posicao " + i);
                aux.leDoArq(arquivo);
                aux.exibirReg();
                i++;
            }
        }

        public void exibirUmRegistro(int pos) {
            Registro aux = new Registro();
            seekArq(pos);
            System.out.println("Posicao " + pos);
            aux.leDoArq(arquivo);
            aux.exibirReg();
        }

        public void seekArq(int pos) {
            try {
                arquivo.seek(pos * Registro.length());
            } catch (IOException e) {
            }
        }

        public void leArq() {
            int codigo, idade;
            String nome;
            codigo = Entrada.leInteger("Digite o c�digo");
            while (codigo != 0) {
                nome = Entrada.leString("Digite o nome");
                idade = Entrada.leInteger("Digite a idade");
                inserirRegNoFinal(new Registro(codigo, nome, idade));
                codigo = Entrada.leInteger("Digite o c�digo");
            }
        }

        // Métodos de Ordenacao

        public void diretcSelectSort() {
            int comparacoes = 0;
            int posMenor, menor;
            Registro regI = new Registro();
            Registro regJ = new Registro();
            int TL = filesize();

            for (int i = 0; i < TL - 1; i++) {
                seekArq(i);
                regI.leDoArq(arquivo);
                posMenor = i;
                menor = regI.getCodigo();

                for (int j = i + 1; j < TL; j++) {
                    regJ.leDoArq(arquivo);

                    comparacoes++;
                    if (menor > regJ.getCodigo()) {
                        menor = regJ.getCodigo();
                        posMenor = j;
                    }
                }

                seekArq(posMenor);
                regJ.leDoArq(arquivo);

                seekArq(i);
                regJ.gravaNoArq(arquivo);

                seekArq(posMenor);
                regI.gravaNoArq(arquivo);


            }
        }

        public void insertionSort() {
            comparacoes = 0;
            int i = 1, pos, flag;
            Registro regi = new Registro();
            Registro regPosAnt = new Registro();
            int TL = filesize();

            while (i < TL) {
                pos = i;
                seekArq(pos);
                regi.leDoArq(arquivo);

                flag = 0;
                while (pos > 0 && flag == 0) {
                    seekArq(pos - 1);
                    regPosAnt.leDoArq(arquivo);

                    comparacoes++;
                    if (regPosAnt.getCodigo() > regi.getCodigo()) {
                        seekArq(pos);
                        regPosAnt.gravaNoArq(arquivo);
                        pos--;
                    } else
                        flag = -1;
                }

                seekArq(pos);
                regi.gravaNoArq(arquivo);

                i++;
            }
        }

        private int buscaBinaria(int chave, int tl) {
            int ini = 0, fim = tl - 1, meio = fim / 2;
            Registro regMeio = new Registro();


            while (ini <= fim) {
                seekArq(meio);
                regMeio.leDoArq(arquivo);

                comparacoes++;
                if (chave > regMeio.getCodigo())
                    ini = meio + 1;
                else
                    fim = meio - 1;

                meio = (ini + fim) / 2;
            }

            return ini;
        }

        public void binaryInsertionSort() {
            comparacoes = 0;
            int i, tl = filesize(), pos, j;
            Registro regAux = new Registro();
            Registro regJ = new Registro();
            i = 1;
            while (i < tl) {
                seekArq(i);
                regAux.leDoArq(arquivo);
                pos = buscaBinaria(regAux.getCodigo(), i);

                for (j = i; j > pos; j--) {
                    seekArq(j - 1);
                    regJ.leDoArq(arquivo);
                    regJ.gravaNoArq(arquivo);
                }

                seekArq(pos);
                regAux.gravaNoArq(arquivo);
                i++;
            }
        }

        public void bubbleSort() {
            Registro regAux = new Registro();
            Registro regJ = new Registro();
            comparacoes = 0;
            int j, i = 0, tl = filesize();
            boolean flag = true;
            while (i < tl - 1 && flag) {
                j = 1;
                flag = false;
                while (j < tl - i) {
                    seekArq(j - 1);
                    regAux.leDoArq(arquivo);
                    seekArq(j);
                    regJ.leDoArq(arquivo);

                    comparacoes++;
                    if (regJ.getCodigo() < regAux.getCodigo()) {
                        seekArq(j);
                        regAux.gravaNoArq(arquivo);
                        seekArq(j - 1);
                        regJ.gravaNoArq(arquivo);
                        flag = true;
                    }
                    j++;
                }
                i++;
            }
        }

        public void shakeSort() {

            int ini = 0, fim = filesize() - 1, tl = filesize();
            Registro regAux = new Registro();
            Registro regAux2 = new Registro();
            boolean flag = true;

            while (ini < fim && flag) {

                flag = false;
                int i = ini;
                while (i < fim) {

                    seekArq(i);
                    regAux.leDoArq(arquivo);
                    seekArq(i + 1);
                    regAux2.leDoArq(arquivo);

                    if (regAux.getCodigo() > regAux2.getCodigo()) {
                        seekArq(i + 1);
                        regAux.gravaNoArq(arquivo);
                        seekArq(i);
                        regAux2.gravaNoArq(arquivo);
                        flag = true;
                    }
                    i++;
                }
                fim--;


                if (flag) {
                    i = fim;
                    flag = false;
                    while (i > ini) {
                        seekArq(i);
                        regAux.leDoArq(arquivo);
                        seekArq(i - 1);
                        regAux2.leDoArq(arquivo);

                        if (regAux.getCodigo() < regAux2.getCodigo()) {
                            seekArq(i - 1);
                            regAux.gravaNoArq(arquivo);
                            seekArq(i);
                            regAux2.gravaNoArq(arquivo);
                            flag = true;
                        }

                        i--;
                    }
                    ini++;
                }

            }

        }

        public void shellSort() {
            int i, j, h, temp;
            int tl = filesize();
            Registro regAux = new Registro();
            Registro regAux2 = new Registro();

            h = 1;
            while (h < tl)
                h = h * 3 + 1;

            while (h > 0) {
                h = h / 3;
                i = h;
                while (i < tl) {
                    seekArq(i);
                    regAux.leDoArq(arquivo); //temp
                    j = i;
                    seekArq(j - h);
                    regAux2.leDoArq(arquivo);

                    while (j >= h && regAux2.getCodigo() > regAux.getCodigo()) {
                        seekArq(j);
                        regAux2.gravaNoArq(arquivo);
                        j = j - h;
                        if (j >= h) {
                            seekArq(j - h);
                            regAux2.leDoArq(arquivo);
                        }
                    }
                    seekArq(j);
                    regAux.gravaNoArq(arquivo);
                    i++;
                }
            }

        }

        private void heapify(int tl, int i) {
            Registro regEsq = new Registro();
            Registro regDir = new Registro();
            Registro regRaiz = new Registro();
            int posMaior = i;
            int esq = i * 2 + 1;
            int dir = i * 2 + 2;

            seekArq(i);
            regRaiz.leDoArq(arquivo);

            int maior = regRaiz.getCodigo();

            if (esq < tl) {
                seekArq(esq);
                regEsq.leDoArq(arquivo);
                comparacoes++;
                if (regEsq.getCodigo() > maior) {
                    posMaior = i * 2 + 1;
                    maior = regEsq.getCodigo();
                }
            }

            if (dir < tl) {
                seekArq(dir);
                regDir.leDoArq(arquivo);
                comparacoes++;
                if (regDir.getCodigo() != -1 && regDir.getCodigo() > maior) {
                    posMaior = i * 2 + 2;
                    maior = regDir.getCodigo();
                }
            }

            if (posMaior != i) {
                seekArq(i);
                if (posMaior == esq) {
                    regEsq.gravaNoArq(arquivo);
                    seekArq(esq);
                } else {
                    regDir.gravaNoArq(arquivo);
                    seekArq(dir);
                }

                regRaiz.gravaNoArq(arquivo);
                heapify(tl, posMaior);
            }
        }

        public void heapSortR() {
            int tl = filesize();
            comparacoes = 0;
            movimentacoes = 0;
            Registro regUltimo = new Registro();
            Registro regPrimeiro = new Registro();

            for (int j = tl / 2 - 1; j >= 0; j--)
                heapify(tl, j);

            for (int i = tl - 1; i > 0; i--) {

                seekArq(0);
                regPrimeiro.leDoArq(arquivo);
                seekArq(i);
                regUltimo.leDoArq(arquivo);

                movimentacoes++;
                seekArq(i);
                regPrimeiro.gravaNoArq(arquivo);
                seekArq(0);
                regUltimo.gravaNoArq(arquivo);

                heapify(i, 0);

            }
        }

        public void heapSort() {
            int pai, fe, fd, maiorf;
            int tl = filesize();
            Registro regFd = new Registro(), regMaior = new Registro(), regPai = new Registro();

            while (tl > 1) {
                for (pai = tl / 2 - 1; pai >= 0; pai--) {

                    seekArq(pai);
                    regPai.leDoArq(arquivo);
                    fe = pai * 2 + 1;
                    seekArq(fe);
                    regMaior.leDoArq(arquivo);
                    maiorf = fe;

                    fd = fe + 1;
                    if (fd < tl) {
                        seekArq(fd);
                        regFd.leDoArq(arquivo);
                        if (regFd.getCodigo() > regMaior.getCodigo()) {
                            maiorf = fd;
                            seekArq(maiorf);
                            regMaior.leDoArq(arquivo);
                        }
                    }
                    if (regMaior.getCodigo() > regPai.getCodigo()) {
                        seekArq(maiorf);
                        regPai.gravaNoArq(arquivo);
                        seekArq(pai);
                        regMaior.gravaNoArq(arquivo);
                    }
                }

                seekArq(0);
                regPai.leDoArq(arquivo);
                seekArq(tl - 1);
                regFd.leDoArq(arquivo);

                seekArq(0);
                regFd.gravaNoArq(arquivo);
                seekArq(tl - 1);
                regPai.gravaNoArq(arquivo);
                tl--;
            }
        }

        private void quickSemPivo(int ini, int fim) {
            int i = ini, j = fim;
            Registro regI = new Registro(), regJ = new Registro();
            boolean flag = true;
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            while(i < j) {
                if(flag) {
                    while(i < j && regI.getCodigo() <= regJ.getCodigo()) {
                        i++;
                        seekArq(i);
                        regI.leDoArq(arquivo);
                    }
                }
                else {
                    while(j > i && regJ.getCodigo() >= regI.getCodigo()) {
                        j--;
                        seekArq(j);
                        regJ.leDoArq(arquivo);
                    }
                }

                if(i < j) {
                    flag = !flag;
                    seekArq(j);
                    regI.gravaNoArq(arquivo);
                    seekArq(i);
                    regJ.gravaNoArq(arquivo);

                    Registro temp = regI;
                    regI = regJ;
                    regJ = temp;
                }
            }
            if(i-1 > ini)
                quickSemPivo(ini, i-1);
            if(j+1 < fim)
                quickSemPivo(j+1, fim);

        }

        public void quickSemPivo() {
            quickSemPivo(0, filesize()-1);
        }

        private void quickSort(int ini, int fim) {
            Registro regLeft = new Registro();
            Registro regRight = new Registro();
            Registro regPivot = new Registro();
            int left = ini;
            int right = fim;
            int pivot;

            seekArq((ini + fim) / 2);
            regPivot.leDoArq(arquivo);
            pivot = regPivot.getCodigo();

            seekArq(left);
            regLeft.leDoArq(arquivo);
            seekArq(right);
            regRight.leDoArq(arquivo);

            while (left <= right) {

                while (regLeft.getCodigo() < pivot) {
                    left++;
                    seekArq(left);
                    regLeft.leDoArq(arquivo);
                }

                while (regRight.getCodigo() > pivot) {
                    right--;
                    seekArq(right);
                    regRight.leDoArq(arquivo);
                }

                if (left <= right) {
                    seekArq(left);
                    regRight.gravaNoArq(arquivo);
                    seekArq(right);
                    regLeft.gravaNoArq(arquivo);

                    right--;
                    left++;

                    if(left <= fim) {
                        seekArq(left);
                        regLeft.leDoArq(arquivo);
                    }
                    if(right >= ini) {
                        seekArq(right);
                        regRight.leDoArq(arquivo);
                    }
                }

            }

            if (ini < right)
                quickSort(ini, right);
            if (left < fim)
                quickSort(left, fim);
        }

        public void quickSort() {
            int ini = 0;
            int fim = filesize() - 1;
            comparacoes = 0;
            movimentacoes = 0;
            quickSort(ini, fim);
        }


        private void particao(Arquivo_Java temp1, Arquivo_Java temp2) {
            int tl = filesize()/2;
            int i = 0;
            temp1.seekArq(0);
            temp2.seekArq(0);
            Registro reg = new Registro();
            while(i < tl) {
                seekArq(i);
                reg.leDoArq(arquivo);
                reg.gravaNoArq(temp1.arquivo);
                seekArq(i+tl);
                reg.leDoArq(arquivo);
                reg.gravaNoArq(temp2.arquivo);
                i++;
            }
        }

        private void fusao(Arquivo_Java temp1, Arquivo_Java temp2, int seq) {
            int i = 0, j = 0, k = 0, tamSeq = seq;
            Registro reg1 = new Registro(), reg2 = new Registro();
            seekArq(0);
            temp1.seekArq(0);
            temp2.seekArq(0);
            while(k < filesize()) {
                while(i < seq && j < seq) {
                    temp1.seekArq(i);
                    temp2.seekArq(j);
                    reg1.leDoArq(temp1.arquivo);
                    reg2.leDoArq(temp2.arquivo);
                    if(reg1.getCodigo() < reg2.getCodigo()) {
                        reg1.gravaNoArq(arquivo);
                        i++;
                        k++;
                    }
                    else {
                        reg2.gravaNoArq(arquivo);
                        j++;
                        k++;
                    }
                }
                while(i < seq) {
                    temp1.seekArq(i);
                    reg1.leDoArq(temp1.arquivo);
                    reg1.gravaNoArq(arquivo);
                    i++; k++;
                }
                while(j < seq) {
                    temp2.seekArq(j);
                    reg2.leDoArq(temp2.arquivo);
                    reg2.gravaNoArq(arquivo);
                    j++; k++;
                }
                seq = seq+tamSeq;
            }
        }

        public void mergeSort() {
            Arquivo_Java temp1 = new Arquivo_Java("temp1");
            Arquivo_Java temp2 = new Arquivo_Java("temp2");
            int seq = 1, tl = filesize();

            while(seq  < tl){
                particao(temp1, temp2);
                fusao(temp1, temp2, seq);
                seq = seq*2;
            }


        }

        private void fusao2(int ini, int meio, int fim, Arquivo_Java temp) {
            int i = ini, j = meio+1, k = 0;
            Registro regI = new Registro(), regJ = new Registro();
            seekArq(ini);
            regI.leDoArq(arquivo);
            seekArq(meio+1);
            regJ.leDoArq(arquivo);
            while(i <= meio && j <= fim) {
                if(regI.getCodigo() <= regJ.getCodigo()) {
                    temp.seekArq(k++);
                    regI.gravaNoArq(temp.arquivo);
                    i++;
                    if (i <= meio) {
                        seekArq(i);
                        regI.leDoArq(arquivo);
                    }
                }
                else {
                    temp.seekArq(k++);
                    regJ.gravaNoArq(temp.arquivo);
                    j++;
                    if(j <= fim) {
                        seekArq(j);
                        regJ.leDoArq(arquivo);
                    }
                }
            }
            while(i <= meio) {
                temp.seekArq(k++);
                regI.gravaNoArq(temp.arquivo);
                i++;
                if(i <= meio) {
                    seekArq(i);
                    regI.leDoArq(arquivo);
                }
            }
            while(j <= fim) {
                temp.seekArq(k++);
                regJ.gravaNoArq(temp.arquivo);
                j++;
                if(j <= fim) {
                    seekArq(j);
                    regJ.leDoArq(arquivo);
                }
            }

            temp.seekArq(0);
            for(int l = 0; l < k; l++) {
                regI.leDoArq(temp.arquivo);
                seekArq(ini+l);
                regI.gravaNoArq(arquivo);
            }
        }

        private void merge2(int ini, int fim, Arquivo_Java temp) {
            if(ini < fim) {
                int meio = (ini+fim)/2;
                merge2(ini, meio, temp);
                merge2(meio+1, fim, temp);
                fusao2(ini, meio, fim, temp);
            }
        }

        //Implementar
        public void mergeSort2() {
            Arquivo_Java temp = new Arquivo_Java("temp");
            merge2(0, filesize()-1, temp);

        }

        public int buscaMaior() {
            Registro regI = new Registro();
            seekArq(0);
            regI.leDoArq(arquivo);
            int maior = regI.getCodigo();
            for(int i = 1; i < filesize(); i++) {
                regI.leDoArq(arquivo);
                if(regI.getCodigo() > maior)
                    maior = regI.getCodigo();
            }
            return maior;
        }



        public void countingSort() {
            int tlC = buscaMaior()+1;
            int[] vetC = new int[tlC];
            Arquivo_Java temp = new Arquivo_Java("temp");
            Registro reg = new Registro();

            seekArq(0);
            for(int i = 0; i < filesize(); i++) {
                reg.leDoArq(arquivo);
                vetC[reg.getCodigo()]++;
            }

            for(int i = 1; i < tlC; i++)
                vetC[i] += vetC[i-1];

            for(int i = filesize()-1; i > -1; i--) {
                seekArq(i);
                reg.leDoArq(arquivo);
                temp.seekArq(--vetC[reg.getCodigo()]);
                reg.gravaNoArq(temp.arquivo);
            }

            for(int i = 0; i < filesize(); i++) {
                seekArq(i);
                temp.seekArq(i);
                reg.leDoArq(temp.arquivo);
                reg.gravaNoArq(arquivo);
            }
        }

    }