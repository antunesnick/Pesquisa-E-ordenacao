public class Tree {
    No raiz;

    public Tree() {
        raiz = new No();
        raiz.setDif(0); // A raiz sempre inicia avaliando a posição 1
    }


    public No buscarPai(No filho, String info) {
        No pai = raiz;
        int idxDif = pai.getDif();

        while(pai.getvLig(info.charAt(idxDif) - 'a') != filho) {
            pai = pai.getvLig(info.charAt(idxDif) - 'a');
            idxDif = pai.getDif();
        }

        return pai;
    }


    public void inserir(String info) {
        No pai, atual;
        int idxAlfa;
        int posString;
        pai = raiz;
        atual = raiz;

        boolean flag = true;
        while(flag) {
            posString = atual.getDif();
            if (posString < info.length()) {
                idxAlfa = info.charAt(posString) - 'a';

                if(atual.getvLig(idxAlfa) != null) {
                    pai = atual;
                    atual = atual.getvLig(idxAlfa);
                }
                else {
                    flag = false;
                }
            }
            else
                flag = false;
        }

        posString = atual.getDif();
        if(atual.getPalavra() != null && atual != raiz) {
            String palFolha = atual.getPalavra();

            int posDifPals = atual.buscaPos(info);
            No novoNo = new No(info);
            No inter = new No();

            if(posDifPals == palFolha.length()) {
               String tempFolha = atual.getPalavra();

               atual.setPalavra(info);

               pai.setPalavra(tempFolha);
               atual = pai;
               pai = buscarPai(atual, atual.getPalavra());

               inter.setDif(tempFolha.length()-1);
               inter.setvLetras(tempFolha.charAt(inter.getDif()) - 'a', tempFolha.charAt(inter.getDif()));
               inter.setvLig(tempFolha.charAt(inter.getDif()) - 'a', atual);
               pai.setvLig(tempFolha.charAt(pai.getDif()) - 'a', inter);
            }
            else if(posDifPals == info.length()) {
                int posUltimaLetra = info.length()-1;
                inter.setDif(posUltimaLetra);
                atual.setPalavra(info);
            }

            else {
                inter.setDif(posDifPals);
                inter.setvLig(palFolha.charAt(posDifPals) - 'a', atual);
                inter.setvLig(info.charAt(posDifPals) - 'a', novoNo);
                inter.setvLetras(palFolha.charAt(posDifPals) - 'a', palFolha.charAt(posDifPals));
                inter.setvLetras(info.charAt(posDifPals) - 'a', info.charAt(posDifPals));
                pai.setvLig(info.charAt(pai.getDif()) - 'a', inter);
            }
        } else if(posString < info.length()) {
            idxAlfa = info.charAt(atual.getDif()) - 'a';
            No novoNo = new No(info);
            atual.setvLig(idxAlfa, novoNo);
            atual.setvLetras(idxAlfa, info.charAt(atual.getDif()));
        } else { // prefixo
            int posUltimaLetra = info.length()-1;
            atual.setPalavra(info);
            No novoNo = new No();
            novoNo.setvLetras(info.charAt(posUltimaLetra) - 'a', info.charAt(posUltimaLetra));
            novoNo.setvLig(info.charAt(posUltimaLetra) - 'a', atual);
            novoNo.setDif(posUltimaLetra);

            pai.setvLig(info.charAt(pai.getDif()) - 'a', novoNo);
        }
    }


    public void exibirPalavras() {
        Pilha p = new Pilha();
        No atual = raiz;
        p.push(raiz);
        while(!p.isEmpty()) {
            atual = p.pop();

            if(atual.getPalavra() != null) {
                System.out.println(atual.getPalavra());
            }
            for (int i = 25; i >= 0; i--) {
                if(atual.getvLig(i) != null) {
                    p.push(atual.getvLig(i));
                }
            }
        }
    }

    public void buscaPalavra(String info) {
        int posDif;
        No atual = raiz;
        boolean flag = true;
        boolean achou = false;

        while(atual != null && flag) {
            if(atual.getPalavra() != null && atual.getPalavra().equalsIgnoreCase(info)) {
                    achou = true;
                    flag = false;
            }
            else {
                posDif = atual.getDif();
                if(posDif < info.length()) {
                    atual = atual.getvLig(info.charAt(posDif) - 'a');
                }
                else {
                    flag = false;
                }
            }
        }
        if(achou)
            System.out.println(info + " - Palavra encontrada!");
        else
            System.out.println(info + " - Palavra nao encontrada!");
    }

    public void exibeNivel() {

        No atual = raiz;
        Fila f= new Fila();
        f.inserir(atual);

        while(!f.isEmpty()) {
            atual = f.retirar();
            System.out.println(atual);

            for(int i = 0; i < 26; i++) {
                if(atual.getvLig(i) != null) {
                    f.inserir(atual.getvLig(i));
                }
            }
        }
    }


}