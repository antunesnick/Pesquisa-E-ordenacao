void main() {
    Tree tree = new Tree();

    for(int i = 0; i < 999; i++) {
        tree.inserir(i, 1);
    }

    tree.inOrdem();

    tree.exibirFolhas();
}