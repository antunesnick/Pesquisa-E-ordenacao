public class NoFila {
    private No info;
    private NoFila prox;

    public NoFila() {
        info = null;
        prox = null;
    }

    public NoFila(No info) {
        this();
        this.info = info;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }

    public NoFila getProx() {
        return prox;
    }

    public void setProx(NoFila prox) {
        this.prox = prox;
    }
}
