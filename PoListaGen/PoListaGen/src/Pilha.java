public class Pilha {

    private NoPilha topo;

    void inicializa()
    {
        topo = null;
    }

    boolean pilhaVazia()
    {
        if(topo == null)
            return true;
        return false;
    }

    void push(ListaGeneralizada info)
    {
        if(pilhaVazia())
        {
            topo = new NoPilha(info, null);
        }
        else
        {
            NoPilha novoNo = new NoPilha(info, topo);
            topo = novoNo;
        }
    }

    NoPilha pop() {
        NoPilha no = null;
        if(!pilhaVazia())
        {
            no = topo;
            topo = topo.getProx();
        }
        return no;
    }


    public NoPilha getTopo() {
        return topo;
    }

    public void setTopo(NoPilha topo) {
        this.topo = topo;
    }
}
