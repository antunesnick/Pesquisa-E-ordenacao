public class Tree {
    private No raiz;


    public Tree() {
        raiz = null;
    }

    public No navegarAteFolha(int info) {
        No aux = raiz;
        int pos;
        while(aux.getvLig(0) != null) {
            pos = aux.procurarPosicao(info);
            aux = aux.getvLig(pos);
        }
        return aux;
    }

    private No localizarPai(No folha, int info) {
        No pai, filho;
        pai = filho = raiz;

        while(filho != folha) {
            pai = filho;
            filho = filho.getvLig(filho.procurarPosicao(info));
        }
        return pai;
    }

    private void split(No pai, No folha) {
        No cx1 = new No();
        No cx2 = new No();
        int qtdCx1, qtdCx2, posPai;

        if(folha.getvLig(0) == null) { // nó folha
            cx1.setAnt(folha.getAnt());
            cx2.setProx(folha.getProx());
            cx1.setProx(cx2);
            cx2.setAnt(cx1);

            if(folha.getAnt() != null) folha.getAnt().setProx(cx1);
            if(folha.getProx() != null) folha.getProx().setAnt(cx2);

            qtdCx1 = (int)(Math.ceil((No.n-1)/2.0));
            for(int i = 0; i < qtdCx1; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setvPos(i, folha.getvPos(i));
                cx1.setvLig(i, folha.getvLig(i));
                cx1.setTl(cx1.getTl()+1);
            }
            cx1.setvLig(cx1.getTl(), folha.getvLig(cx1.getTl()));

            qtdCx2 = folha.getTl() - qtdCx1;
            for(int i = 0; i <qtdCx2; i++) {
                cx2.setvInfo(i, folha.getvInfo(i+qtdCx1));
                cx2.setvPos(i, folha.getvPos(i+qtdCx1));
                cx2.setvLig(i, folha.getvLig(i+qtdCx1));
                cx2.setTl(cx2.getTl()+1);
            }
            cx2.setvLig(cx2.getTl(), folha.getvLig(folha.getTl()));

            if(pai == folha) {
                folha.setvInfo(0, cx2.getvInfo(0));
                folha.setvPos(0, -1);
                folha.setvLig(0, cx1);
                folha.setvLig(1, cx2);
                folha.setTl(1);
                folha.setAnt(null);
                folha.setProx(null);
            }
            else {
                posPai = pai.procurarPosicao(cx2.getvInfo(0));
                pai.remanejar(posPai);
                pai.setvInfo(posPai, cx2.getvInfo(0));
                pai.setvPos(posPai, cx2.getvPos(0));
                pai.setvLig(posPai, cx1);
                pai.setvLig(posPai+1, cx2);
                pai.setTl(pai.getTl()+1);
                if(pai.getTl() > No.n-1) {
                    folha = pai;
                    pai = localizarPai(folha, folha.getvInfo(0));
                    split(pai, folha);
                }
            }
        }
        else { // nó nao folha
            qtdCx1 = (int)(Math.ceil(No.n/2.0))-1;
            for(int i = 0; i < qtdCx1; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setvPos(i, folha.getvPos(i));
                cx1.setvLig(i, folha.getvLig(i));
                cx1.setTl(cx1.getTl()+1);
            }
            cx1.setvLig(cx1.getTl(), folha.getvLig(cx1.getTl()));

            qtdCx2 = folha.getTl() - qtdCx1-1;
            for(int i = 0; i < qtdCx2; i++) {
                cx2.setvInfo(i, folha.getvInfo(i+qtdCx1+1));
                cx2.setvPos(i, folha.getvPos(i+qtdCx1+1));
                cx2.setvLig(i, folha.getvLig(i+qtdCx1+1));
                cx2.setTl(cx2.getTl()+1);
            }
            cx2.setvLig(cx2.getTl(), folha.getvLig(folha.getTl()));


            if(pai == folha) {
                folha.setvInfo(0, folha.getvInfo(qtdCx1));
                folha.setvPos(0, folha.getvPos(qtdCx1));
                folha.setvLig(0, cx1);
                folha.setvLig(1, cx2);
                folha.setTl(1);
            }
            else {
                posPai = pai.procurarPosicao(folha.getvInfo(qtdCx1));
                pai.remanejar(posPai);

                pai.setvInfo(posPai, folha.getvInfo(qtdCx1));
                pai.setvPos(posPai, folha.getvPos(qtdCx1));
                pai.setvLig(posPai, cx1);
                pai.setvLig(posPai+1, cx2);
                pai.setTl(pai.getTl()+1);
                if(pai.getTl() > No.n-1) {
                    folha = pai;
                    pai = localizarPai(pai, pai.getvInfo(0));
                    split(pai, folha);
                }
            }
        }
    }

    public void inserir(int info, int posArq) {
        No folha, pai;
        int pos;

        if(raiz == null){
            raiz = new No(info, posArq);
        }
        else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, posArq);
            folha.setTl(folha.getTl()+1);


            if(folha.getTl() > No.n-1) {
                pai = localizarPai(folha, folha.getvInfo(0));
                split(pai, folha);
            }
        }

    }

    private void inOrdem(No raiz) {
        if(raiz != null) {
            for(int i = 0; i < raiz.getTl(); i++) {
                inOrdem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            inOrdem(raiz.getvLig(raiz.getTl()));
        }
    }

    public void exibirFolhas() {
        No folha = raiz;

        while(folha.getvLig(0) != null)
            folha = folha.getvLig(0);

        while(folha != null) {
            for (int i = 0; i < folha.getTl(); i++) {
                System.out.println("INFO = " + folha.getvInfo(i) + " POS = " + folha.getvPos(i));
            }
            folha = folha.getProx();
        }
    }

    public void inOrdem() {
        inOrdem(raiz);
    }
}
