public class NoPilha {
    private ListaGeneralizada info;
    private NoPilha prox;


    public NoPilha(ListaGeneralizada info, NoPilha prox) {
        this.info = info;
        this.prox = prox;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(ListaGeneralizada info) {
        this.info = info;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}
