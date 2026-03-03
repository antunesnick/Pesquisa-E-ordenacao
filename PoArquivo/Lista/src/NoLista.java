public class NoLista {
    private NoLista ant;
    private NoLista prox;
    private int info;


    public NoLista(NoLista ant, NoLista prox, int info) {
        this.ant = ant;
        this.prox = prox;
        this.info = info;
    }

    public NoLista getAnt() {
        return ant;
    }

    public void setAnt(NoLista ant) {
        this.ant = ant;
    }

    public NoLista getProx() {
        return prox;
    }

    public void setProx(NoLista prox) {
        this.prox = prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
