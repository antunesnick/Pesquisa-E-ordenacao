public class Pilha {
    NoPilha topo;

    public Pilha() {
        topo = null;
    }


    public void push(No info) {
        NoPilha novo = new NoPilha(info);
        novo.setProx(topo);
        topo = novo;
    }

    public No pop() {
        No info = topo.getInfo();
        topo = topo.getProx();
        return info;
    }

    boolean isEmpty() {
        return topo == null;
    }
}