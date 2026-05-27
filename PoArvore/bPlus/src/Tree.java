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

    public void excluir(int info) {
        No folha = navegarAteFolha(info);
        int pos;

        pos = folha.procurarPosicao(info);
        folha.remanejarExclusao(pos-1);
        folha.setTl(folha.getTl()-1);

        if(folha != raiz && folha.getTl() < Math.ceil(No.n/2.0)-1) {
            redestribuirConcatenar(folha, info);
        }
    }

    private void redestribuirConcatenar(No folha, int infoE) {
        No pai, irmaE = null, irmaD = null;
        int posPai;

        pai = localizarPai(folha, folha.getvInfo(0));
        posPai = 0;
        while (posPai <= pai.getTl() && pai.getvLig(posPai) != folha) {
            posPai++;
        }
        if (posPai > 0)
            irmaE = pai.getvLig(posPai - 1);
        if (posPai < pai.getTl()) {
            irmaD = pai.getvLig(posPai + 1);
        }

        if(folha.getvLig(0) == null) { // é folha
            if (irmaE != null && irmaE.getTl() > Math.ceil(No.n / 2.0) - 1) { //redestribuicao
                folha.remanejar(0);
                folha.setvInfo(0, irmaE.getvInfo(irmaE.getTl() - 1));
                folha.setvPos(0, irmaE.getvPos(irmaE.getTl() - 1));
                folha.setTl(folha.getTl() + 1);

                irmaE.setTl(irmaE.getTl() - 1);

                pai.setvInfo(posPai - 1, folha.getvInfo(0));
                pai.setvPos(posPai - 1, folha.getvPos(0));

            } else if (irmaD != null && irmaD.getTl() > Math.ceil(No.n / 2.0) - 1) {
                folha.setvInfo(folha.getTl(), irmaD.getvInfo(0));
                folha.setvPos(folha.getTl(), irmaD.getvPos(0));
                folha.setTl(folha.getTl() + 1);

                irmaD.remanejarExclusao(0);
                irmaD.setTl(irmaD.getTl() - 1);

                pai.setvPos(posPai, irmaD.getvPos(0));
                pai.setvInfo(posPai, irmaD.getvInfo(0));
            } else { // concatenacao
                if (irmaD != null) {
                    for (int i = 0; i < irmaD.getTl(); i++) {
                        folha.setvInfo(folha.getTl(), irmaD.getvInfo(i));
                        folha.setvPos(folha.getTl(), irmaD.getvPos(i));
                        folha.setvLig(folha.getTl(), irmaD.getvLig(i));
                        folha.setTl(folha.getTl() + 1);
                    }
                    folha.setvLig(folha.getTl(), irmaD.getvLig(irmaD.getTl()));
                    irmaD.setTl(0);

                    folha.setProx(irmaD.getProx());
                    if (folha.getProx() != null) {
                        folha.getProx().setAnt(folha);
                    }

                    pai.remanejarExclusao(posPai);
                    pai.setTl(pai.getTl() - 1);
                    pai.setvLig(posPai, folha);
                } else if (irmaE != null) {
                    for (int i = 0; i < folha.getTl(); i++) {
                        irmaE.setvInfo(irmaE.getTl(), folha.getvInfo(i));
                        irmaE.setvPos(irmaE.getTl(), folha.getvPos(i));
                        irmaE.setvLig(irmaE.getTl(), folha.getvLig(i));
                        irmaE.setTl(irmaE.getTl() + 1);
                    }
                    irmaE.setvLig(irmaE.getTl(), folha.getvLig(folha.getTl()));

                    irmaE.setProx(folha.getProx());
                    if (folha.getProx() != null) {
                        folha.getProx().setAnt(irmaE);
                    }
                    folha.setTl(0);

                    pai.remanejarExclusao(posPai - 1);
                    pai.setTl(pai.getTl()-1);
                    pai.setvLig(posPai - 1, irmaE);
                }
                if (pai == raiz && pai.getTl() == 0) {
                    if (irmaE != null) {
                        raiz = irmaE;
                    } else {
                        raiz = folha;
                    }
                }
                else if(pai != raiz && pai.getTl() < Math.ceil(No.n/2.0)-1)
                    redestribuirConcatenar(pai, pai.getvInfo(0));
            }
        }
        else { // NÓ INTERNO!!!¿
            if(irmaE != null && irmaE.getTl() > Math.ceil(No.n/2.0)-1) { //redestribuicao
                folha.remanejar(0);
                folha.setvInfo(0, pai.getvInfo(posPai-1));
                folha.setvPos(0, pai.getvPos(posPai-1));
                folha.setvLig(0, irmaE.getvLig(irmaE.getTl()));
                folha.setTl(folha.getTl()+1);


                pai.setvInfo(posPai-1, irmaE.getvInfo(irmaE.getTl() -1));
                pai.setvPos(posPai-1, irmaE.getvPos(irmaE.getTl() -1));

                irmaE.setTl(irmaE.getTl()-1);
            }
            else if(irmaD != null && irmaD.getTl() > Math.ceil(No.n/2.0)-1) {
                folha.setvInfo(folha.getTl(), pai.getvInfo(posPai));
                folha.setvPos(folha.getTl(), pai.getvPos(posPai));
                folha.setvLig(folha.getTl()+1, irmaD.getvLig(0));
                folha.setTl(folha.getTl()+1);

                pai.setvInfo(posPai, irmaD.getvInfo(0));
                pai.setvPos(posPai, irmaD.getvPos(0));

                irmaD.remanejarExclusao(0);
                irmaD.setTl(irmaD.getTl()-1);
            }
            else { // concatenacao
                if(irmaE != null) {
                    irmaE.setvInfo(irmaE.getTl(), pai.getvInfo(posPai-1));
                    irmaE.setvPos(irmaE.getTl(), pai.getvPos(posPai-1));
                    irmaE.setTl(irmaE.getTl()+1);

                    for(int i = 0; i < folha.getTl(); i++) {
                        irmaE.setvInfo(irmaE.getTl(), folha.getvInfo(i));
                        irmaE.setvPos(irmaE.getTl(), folha.getvPos(i));
                        irmaE.setvLig(irmaE.getTl(), folha.getvLig(i));
                        irmaE.setTl(irmaE.getTl()+1);
                    }
                    irmaE.setvLig(irmaE.getTl(), folha.getvLig(folha.getTl()));

                    folha.setTl(0);
                    pai.remanejarExclusao(posPai-1);
                    pai.setTl(pai.getTl()-1);
                    pai.setvLig(posPai-1, irmaE);
                }
                else if(irmaD != null) {
                    folha.setvInfo(folha.getTl(), pai.getvInfo(posPai));
                    folha.setvPos(folha.getTl(), pai.getvPos(posPai));
                    folha.setTl(folha.getTl() + 1);

                    for (int i = 0; i < irmaD.getTl(); i++) {
                        folha.setvInfo(folha.getTl(), irmaD.getvInfo(i));
                        folha.setvPos(folha.getTl(), irmaD.getvPos(i));
                        folha.setvLig(folha.getTl(), irmaD.getvLig(i));
                        folha.setTl(folha.getTl() + 1);
                    }
                    folha.setvLig(folha.getTl(), irmaD.getvLig(irmaD.getTl()));

                    irmaD.setTl(0);
                    pai.remanejarExclusao(posPai);
                    pai.setTl(pai.getTl()-1);
                    pai.setvLig(posPai, folha);
                }

                if (pai == raiz && pai.getTl() == 0) {
                    if (irmaE != null) {
                        raiz = irmaE;
                    } else {
                        raiz = folha;
                    }
                }
                else if (pai != raiz && pai.getTl() < Math.ceil(No.n / 2.0) - 1) {
                    redestribuirConcatenar(pai, pai.getvInfo(0));
                }
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
