public class Application {

    static void main() {
        Arquivo_Java arq = new Arquivo_Java("C:/Users/user/Documents/P.O/Pesquisa-E-ordenacao/PoArquivo/Arquivos/dados.dat");


        arq.exibirArq();

        System.out.println("\n\n\n\n\n");

        arq.shakeSort();
        arq.exibirArq();
    }


}
