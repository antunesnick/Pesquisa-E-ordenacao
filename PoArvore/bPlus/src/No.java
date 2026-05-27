public class No {
    public final static int n = 4;

    private int[] vInfo;
    private int[] vPos;
    private No[] vLig;
    private int tl;

    private No prox;
    private No ant;

    public No() {
        vInfo = new int[n];
        vPos = new int[n];
        vLig = new No[n+1];

        tl = 0;
        prox = null;
        ant = null;
    }

    public No(int info, int posArq) {
        this();
        vInfo[0] = info;
        vPos[0] = posArq;
        tl = 1;
    }

    public int procurarPosicao(int info) {
        int i = 0;

        while(tl > i && info >= vInfo[i])
            i++;

        return i;
    }


    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int vInfo) {
        this.vInfo[p] = vInfo;
    }

    public int getvPos(int p) {
        return vPos[p];
    }

    public void setvPos(int p, int vPos) {
        this.vPos[p] = vPos;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No vLig) {
        this.vLig[p] = vLig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public void remanejar(int pos) {
        vLig[tl+1] = vLig[tl];
        for(int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i-1];
            vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }

    public void remanejarExclusao(int pos) {
        for(int i = pos; i < tl-1; i++) {
            vInfo[i] = vInfo[i+1];
            vPos[i] = vPos[i+1];
            vLig[i] = vLig[i+1];
        }
        vLig[tl-1] = vLig[tl];
    }
}
