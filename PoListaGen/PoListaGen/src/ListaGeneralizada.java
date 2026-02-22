public class ListaGeneralizada extends No{
    private No cabeca;
    private No cauda;

    public ListaGeneralizada() {
        this.cabeca = null;
        this.cauda = null;
    }

    public No getCabeca() {
        return cabeca;
    }

    public void setCabeca(No cabeca) {
        this.cabeca = cabeca;
    }

    public No getCauda() {
        return cauda;
    }

    public void setCauda(No cauda) {
        this.cauda = cauda;
    }

    public ListaGeneralizada(No cabeca, ListaGeneralizada cauda) {
        this.cabeca = cabeca;
        this.cauda = cauda;
    }
}
