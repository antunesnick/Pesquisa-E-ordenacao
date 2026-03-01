public class Application {

    static void main() {
        Arquivo_Java arq = new Arquivo_Java("C:/Users/Raphel/Documents/nickolas/PoArquivo/Arquivos/dados.dat");


        arq.exibirArq();

        System.out.println("\n\n\n\n\n");

        arq.binaryInsertionSort();
        arq.exibirArq();
    }


}
