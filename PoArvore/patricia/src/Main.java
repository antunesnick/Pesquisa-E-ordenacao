void main() {

    Tree tree = new Tree();


    tree.inserir("galo");
    tree.inserir("sola");
    tree.inserir("gel");
    tree.inserir("solo");
    tree.inserir("sol");
    tree.inserir("gelo");


    tree.exibirPalavras();

    tree.exibeNivel();

    System.out.println("\n\n");

    tree.buscaPalavra("gelo");


}