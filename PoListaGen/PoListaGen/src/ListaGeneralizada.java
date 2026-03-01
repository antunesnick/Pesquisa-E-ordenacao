public class ListaGeneralizada extends No{
    private No cabeca;
    private ListaGeneralizada cauda;

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

    public void setCauda(ListaGeneralizada cauda) {
        this.cauda = cauda;
    }

    public ListaGeneralizada(No cabeca, ListaGeneralizada cauda) {
        this.cabeca = cabeca;
        this.cauda = cauda;
    }
}
