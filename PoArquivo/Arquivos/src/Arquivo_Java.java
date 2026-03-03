import java.io.RandomAccessFile;
import java.io.IOException;
import java.lang.reflect.Parameter;

public class  Arquivo_Java
{
    private String nomearquivo;
    private RandomAccessFile arquivo;

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

    public void diretionSelectSort(){
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


    public int buscaBinaria(int chave, int tl)
    {
        int ini = 0, fim = tl-1, meio = fim/2;
        Registro regMeio = new Registro();


        while(ini <= fim)
        {
            seekArq(meio);
            regMeio.leDoArq(arquivo);

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


}
