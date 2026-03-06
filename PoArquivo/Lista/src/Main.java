public class Main {

    static void main() {
        ListaD lista = new ListaD();
        lista.inserirNoFim(45);
        lista.inserirNoFim(12);
        lista.inserirNoFim(89);
        lista.inserirNoFim(23);
        lista.inserirNoFim(7);


        lista.exibir();
        lista.shellSort();
        lista.exibir();
    }

}