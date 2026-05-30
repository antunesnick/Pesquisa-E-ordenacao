public class NoPilha {
    private No info;
    private NoPilha prox;

    public NoPilha() {
        info = null;
        prox = null;
    }

    NoPilha(No info) {
        this();
        this.info = info;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}
