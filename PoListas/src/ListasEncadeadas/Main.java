package ListasEncadeadas;

public class Main {

    ListaCidade listaCidade = new ListaCidade();
    ListaEstado listaEstado = new ListaEstado();

    public void inicializaListas()
    {
        listaEstado.inicializa();
        listaCidade.inicializa();
    }

    public void inserirPar(String estado, String cidade)
    {
        NoEstado aux;
        listaEstado.inserir(estado);
        aux = listaEstado.buscaEstado(estado);
        aux.getCidades().inserir(cidade);
    }

    public void listarCidadesEstado(String estado)
    {
        NoEstado aux = listaEstado.buscaEstado(estado);
        if(aux != null)
            aux.getCidades().exibir();
    }

    public boolean buscaCidadeEstado(String estado, String cidade)
    {
        NoEstado aux = listaEstado.buscaEstado(estado);
        NoCidade auxC;

        if(aux == null)
            return false;

        auxC = aux.getCidades().buscaCidade(cidade);

        if(auxC == null)
            return false;

        return true;
    }
}
