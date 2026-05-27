void main() {
    Tree tree = new Tree();

    tree.inserir(1,1);
    tree.inserir(4,1);
    tree.inserir(7,1);
    tree.inserir(10,1);
    tree.inserir(17,1);
    tree.inserir(21,1);
    tree.inserir(31,1);
    tree.inserir(25,1);
    tree.inserir(19,1);
    tree.inserir(20,1);
    tree.inserir(28,1);
    tree.inserir(42,1);


    tree.excluir(28);
    tree.excluir(31);
    tree.excluir(21);

    System.out.println("PÓS EXCLUSAO 1");

    tree.inOrdem();
    tree.exibirFolhas();

    tree.excluir(25);
    tree.excluir(19);

    System.out.println("Pós exclusao 2");

    tree.inOrdem();
    tree.exibirFolhas();
}