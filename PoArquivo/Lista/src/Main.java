public class Main {

    static void main() {
        ListaD lista = new ListaD();
        lista.inserirNoFim(45);
        lista.inserirNoFim(34);
        lista.inserirNoFim(22);
        lista.inserirNoFim(1);
        lista.inserirNoFim(4);
        lista.inserirNoFim(12);
        lista.inserirNoFim(22);
        lista.inserirNoFim(89);
        lista.inserirNoFim(23);
        lista.inserirNoFim(7);



        lista.exibir();
        lista.mergeSortBottomUp();
        lista.exibir();
    }

}