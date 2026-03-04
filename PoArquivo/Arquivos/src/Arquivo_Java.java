import java.io.RandomAccessFile;
import java.io.IOException;

public class  Arquivo_Java
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comparacoes;
    private int movimentacoes;

    public Arquivo_Java(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e)
        { }
        return (retorno);
    }

    public int filesize() {
        try{
            return (int)arquivo.length()/Registro.length();
        }catch (IOException e) {}
        return 0;
    }


    //insere um Registro no final do arquivo, passado por par�metro
    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            System.out.println("Posicao " + i);
            aux.leDoArq(arquivo);
            aux.exibirReg();
            i++;
        }
    }

    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public void leArq()
    {
        int codigo, idade;
        String nome;
        codigo = Entrada.leInteger("Digite o c�digo");
        while (codigo != 0)
        {
            nome = Entrada.leString("Digite o nome");
            idade = Entrada.leInteger("Digite a idade");
            inserirRegNoFinal(new Registro(codigo, nome, idade));
            codigo = Entrada.leInteger("Digite o c�digo");
        }
    }

    //.............................................................................
    /*

    insira aqui os m�todos de Ordena��o;

    */

    public void diretcSelectSort(){
        int comparacoes = 0;
        int posMenor, menor;
        Registro regI = new Registro();
        Registro regJ = new Registro();
        int TL = filesize();

        for(int i = 0; i < TL-1; i++)
        {
            seekArq(i);
            regI.leDoArq(arquivo);
            posMenor = i;
            menor = regI.getCodigo();

            for(int j = i+1; j < TL; j++)
            {
                regJ.leDoArq(arquivo);

                comparacoes++;
                if(menor > regJ.getCodigo())
                {
                    menor = regI.getCodigo();
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

    public void insertionSort()
    {
        comparacoes = 0;
        int i = 1, pos, flag;
        Registro regi = new Registro();
        Registro regPosAnt = new Registro();
        int TL = filesize();

        while(i < TL)
        {
            pos = i;
            seekArq(pos);
            regi.leDoArq(arquivo);

            flag = 0;
            while(pos > 0 && flag == 0)
            {
                seekArq(pos-1);
                regPosAnt.leDoArq(arquivo);

                comparacoes++;
                if(regPosAnt.getCodigo() > regi.getCodigo()) {
                    seekArq(pos);
                    regPosAnt.gravaNoArq(arquivo);
                    pos--;
                }
                else
                    flag = -1;
            }

            seekArq(pos);
            regi.gravaNoArq(arquivo);

            i++;
        }
    }


    private int buscaBinaria(int chave, int tl)
    {
        int ini = 0, fim = tl-1, meio = fim/2;
        Registro regMeio = new Registro();


        while(ini <= fim)
        {
            seekArq(meio);
            regMeio.leDoArq(arquivo);

            comparacoes++;
            if(chave > regMeio.getCodigo())
                ini = meio+1;
            else
                fim = meio -1;

            meio = (ini+fim)/2;
        }

        if(chave > regMeio.getCodigo())
            return meio+1;
        else
            return meio;
    }

    public void binaryInsertionSort()
    {
        comparacoes = 0;
        int i,tl = filesize(), pos, j;
        Registro regAux = new Registro();
        Registro regJ = new Registro();
        i = 1;
        while(i < tl)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);
            pos = buscaBinaria(regAux.getCodigo(), i);

            for(j = i; j > pos; j--)
            {
                seekArq(j-1);
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
        while(i < tl-1 && flag) {
            j = 1;
            flag = false;
            while(j < tl-i) {
                seekArq(j-1);
                regAux.leDoArq(arquivo);
                seekArq(j);
                regJ.leDoArq(arquivo);


                comparacoes++;
                if(regJ.getCodigo() < regAux.getCodigo()) {
                    seekArq(j);
                    regAux.gravaNoArq(arquivo);
                    seekArq(j-1);
                    regJ.gravaNoArq(arquivo);
                    flag = true;
                }
                j++;
            }
            i++;
        }
    }

    public void shakeSort() {

        int ini = 0, fim = filesize()-1, tl = filesize();
        Registro regAux = new Registro();
        Registro regAux2  = new Registro();
        boolean flag = true;

        while(ini < fim && flag) {

            flag = false;
            int i = ini;
            while(i < fim) {

                seekArq(i);
                regAux.leDoArq(arquivo);
                seekArq(i+1);
                regAux2.leDoArq(arquivo);

                if (regAux.getCodigo() > regAux2.getCodigo()) {
                    seekArq(i+1);
                    regAux.gravaNoArq(arquivo);
                    seekArq(i);
                    regAux2.gravaNoArq(arquivo);
                    flag = true;
                }
                i++;
            }
            fim--;


            if(flag) {
                i = fim;
                flag = false;
                while(i > ini) {
                    seekArq(i);
                    regAux.leDoArq(arquivo);
                    seekArq(i-1);
                    regAux2.leDoArq(arquivo);

                    if(regAux.getCodigo() < regAux2.getCodigo()) {
                        seekArq(i-1);
                        regAux.gravaNoArq(arquivo);
                        seekArq(i);
                        regAux2.gravaNoArq(arquivo);
                        flag = true;
                    }

                    i--;
                }


            }

        }

    }

    public void shellSort() {
        int i, j, h, temp;
        int tl = filesize();
        Registro regAux = new Registro();
        Registro regAux2 =  new Registro();

        h = 1;
        while(h < tl)
            h = h*3+1;

        while(h > 1) {
            h = h/3;
            i = h;
            while(i < tl) {

                seekArq(i);
                regAux.leDoArq(arquivo); //temp
                j = i;
                seekArq(j-h);
                regAux2.leDoArq(arquivo);

                while(j >= h && regAux2.getCodigo() > regAux.getCodigo()) {
                    seekArq(j);
                    regAux2.gravaNoArq(arquivo);
                    j = j-h;

                    if(j >= h) {
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
        Registro regEsq =  new Registro();
        Registro regDir =  new Registro();
        Registro regRaiz = new Registro();
        int posMaior = i;
        int esq = i*2+1;
        int dir = i*2+2;

        seekArq(i);
        regRaiz.leDoArq(arquivo);

        if(esq < tl) {
            seekArq(i*2+1);
            regEsq.leDoArq(arquivo);
            comparacoes++;
            if(regEsq.getCodigo() > regRaiz.getCodigo()) {
                posMaior = i*2+1;
            }
        }

        if(dir < tl){
            seekArq(i*2+2);
            regDir.leDoArq(arquivo);
            comparacoes++;
            if(regDir.getCodigo() != -1 && regDir.getCodigo() > regRaiz.getCodigo()) {
                posMaior = i*2+2;
            }
        }

        if(posMaior != i) {
            seekArq(i);
            if(posMaior == esq) {
                regEsq.gravaNoArq(arquivo);
                seekArq(esq);
            }
            else {
                regDir.gravaNoArq(arquivo);
                seekArq(dir);
            }

            regRaiz.gravaNoArq(arquivo);
            heapify(tl, i*2+1);
        }
    }

    public void heapSort() {
        int tl = filesize();
        comparacoes = 0;
        movimentacoes = 0;
        Registro regUltimo = new Registro();
        Registro regPrimeiro = new Registro();

        for(int j = tl/2 -1; j >= 0; j--)
            heapify(tl, j);

        for(int i = tl-1; i > 0; i--) {

            seekArq(0);
            regPrimeiro.leDoArq(arquivo);
            seekArq(i);
            regUltimo.leDoArq(arquivo);

            movimentacoes ++;
            seekArq(i);
            regPrimeiro.gravaNoArq(arquivo);
            seekArq(0);
            regUltimo.gravaNoArq(arquivo);

            heapify(i, 0);

        }
    }

}
