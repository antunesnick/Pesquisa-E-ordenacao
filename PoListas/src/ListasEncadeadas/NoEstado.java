package ListasEncadeadas;

public class NoEstado extends No{
    ListaCidade cidades;

    public NoEstado(String nome, No prox) {
        super(nome, prox);
        this.cidades = new ListaCidade();
        this.cidades.inicializa();
    }


    public ListaCidade getCidades() {
        return cidades;
    }

    public void setCidades(ListaCidade cidades) {
        this.cidades = cidades;
    }
}
