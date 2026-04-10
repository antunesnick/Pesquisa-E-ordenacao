public class No {
    public static final int N = 3;
    private int[] vInfo;
    private final No[] vLig;
    private int tl;

    public No(int info) {
        vInfo = new int[N-1];
        vLig = new No[N];
        vInfo[0] = info;
        tl = 1;
    }

    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
        this.vInfo[p] = info;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No lig) {
        this.vLig[p] = lig;
    }

    public int getTl() {
        return tl;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }


    public int buscaPos(int info) {
        int pos = 0;
        while(pos < tl && info > vInfo[pos]) {
            pos++;
        }
        return pos;
    }

    public void remanejar(int pos) {
        for(int i = tl; i > pos; i--)
            vInfo[i] = vInfo[i-1];
    }
}