package ListasEncadeadas;

public class ListaEstado {
    NoEstado inicio;

    public void inicializa()
    {
        this.inicio = null;
    }

    public void inserir(String nome)
    {
        if(inicio == null)
            inicio = new NoEstado(nome, null);
        else {
            if(buscaEstado(nome) == null)
            {
                No aux = inicio;
                No novoNo = new NoEstado(nome, null);
                while(aux.getProx() != null)
                    aux = aux.getProx();
                aux.setProx(novoNo);
            }
            else
                System.out.println("Estado já existente.");
        }
    }

    public NoEstado buscaEstado(String estado)
    {
        NoEstado aux = inicio;

        while(aux != null && !aux.getNome().equalsIgnoreCase(estado))
            aux = (NoEstado) aux.getProx();

        return aux;
    }

    public void ordena() {
        NoEstado atual = (NoEstado) inicio.getProx();
        NoEstado busca;
        String temp;

        while (atual != null) {
            busca = inicio;

            while (busca != atual) {
                if (busca.getNome().compareToIgnoreCase(atual.getNome()) > 0) {
                    temp = atual.getNome();
                    atual.setNome(busca.getNome());
                    busca.setNome(temp);
                }
                busca = (NoEstado) busca.getProx();
            }
            atual = (NoEstado) atual.getProx();
        }
    }

    public NoEstado getInicio() {
        return inicio;
    }

    public void setInicio(NoEstado inicio) {
        this.inicio = inicio;
    }
}
