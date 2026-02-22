public class Lista {
    private ListaGeneralizada inicio;

    public Lista() {
        this.inicio = null;
    }

    public void stringPraLista(String s)
    {
        if(!s.isBlank())
        {
            int pos = 0;
            char atualC;
            String info;
            Pilha p = new Pilha();
            p.inicializa();
            ListaGeneralizada atual = null;
            ListaGeneralizada novaLista;
            ListaGeneralizada novoNo;

            while(pos < s.length())
            {
                atualC = s.charAt(pos);
                if(atualC == '[') {
                    novaLista = new ListaGeneralizada();

                    if(inicio == null)
                    {
                        inicio = novaLista;
                    }
                    else {
                        atual.setCabeca(novaLista);
                    }
                    p.push(novaLista);
                    atual = novaLista;
                    pos++;
                }
                else
                    if(atualC == ',') {
                        novoNo = new ListaGeneralizada();
                        atual.setCauda(novoNo);
                        atual = novoNo;
                        pos++;
                    }
                    else
                        if (atualC == ']') {
                            p.pop();
                            if(!p.pilhaVazia())
                            {
                                atual = (ListaGeneralizada) p.getTopo().getInfo();

                                while(atual.getCauda() != null)
                                    atual = (ListaGeneralizada) atual.getCauda();
                            }
                            pos++;
                        }
                        else if(atualC == ' ')
                                pos++;
                        else {
                            int fimAtomo = pos;
                            while(fimAtomo < s.length() && Character.isLetter(s.charAt(fimAtomo)))
                                fimAtomo++;
                            info = s.substring(pos, fimAtomo);

                            atual.setCabeca(new Atomo(info));

                            pos = fimAtomo;
                        }
            }
        }
    }

}