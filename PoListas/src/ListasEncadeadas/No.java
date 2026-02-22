package ListasEncadeadas;

public abstract class No {
    String nome;
    No prox;

    public No(String nome, No prox) {
        this.nome = nome;
        this.prox = prox;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
