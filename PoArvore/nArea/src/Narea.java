public class Narea {
    No raiz;

    public void inserir(int info) {
        No aux;
        int pos;
        if(raiz == null)
            raiz = new No(info);
        else {
            aux = raiz;
            boolean flag = false;
            while(!flag) {
                pos = aux.buscaPos(info);
                if(aux.getTl() < No.N-1) {
                    aux.remanejar(pos);
                    aux.setvInfo(pos, info);
                    aux.setTl(aux.getTl()+1);
                    flag = true;
                }
                else {
                    if(aux.getvLig(pos) == null) {
                        aux.setvLig(pos, new No(info));
                        flag = true;
                    }
                    else {
                        aux = aux.getvLig(pos);
                    }
                }
            }
        }
    }

    public void inOrdem() {
        inOrdem(this.raiz);
    }

    private void inOrdem(No no) {
        if(no != null) {
            for(int i = 0; i < no.getTl(); i++) {
                inOrdem(no.getvLig(i));
                System.out.println(no.getvInfo(i));
            }
            inOrdem(raiz.getvLig(raiz.getTl()));
        }
    }
}
