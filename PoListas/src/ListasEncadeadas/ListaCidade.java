package ListasEncadeadas;

public class ListaCidade {
   NoCidade inicio;

    public void inicializa()
    {
        this.inicio = null;
    }

    public void inserir(String nome)
    {
        if(inicio == null)
        {
            inicio = new NoCidade(nome, null);
        }
        else
        {
            if(buscaCidade(nome) == null)
            {
                No aux =inicio;
                while(aux.getProx() != null)
                    aux = aux.getProx();
                aux.setProx(new NoCidade(nome, null));
            }
            else
                System.out.println("Cidade já existente.");
        }

    }

    public void exibir()
    {
        No aux = inicio;
        while(aux != null)
        {
            System.out.println(aux.getNome());
            aux = aux.getProx();
        }
    }

    public NoCidade buscaCidade(String cidade)
    {
        NoCidade aux = inicio;

        while(aux != null && !aux.getNome().equalsIgnoreCase(cidade))
            aux = (NoCidade) aux.getProx();

        return aux;
    }

    public void ordena()
    {
        NoCidade atual = (NoCidade) inicio.getProx();
        NoCidade busca;
        String temp;

        while(atual != null)
        {
            busca = inicio;

            while(busca != atual)
            {
                if(busca.getNome().compareToIgnoreCase(atual.getNome()) > 0)
                {
                    temp = atual.getNome();
                    atual.setNome(busca.getNome());
                    busca.setNome(temp);
                }
                busca = (NoCidade) busca.getProx();
            }
            atual = (NoCidade) atual.getProx();
        }
    }

}
