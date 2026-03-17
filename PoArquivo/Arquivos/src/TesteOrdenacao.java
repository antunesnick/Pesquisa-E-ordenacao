import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.util.Random;

public class TesteOrdenacao {
    public static void main(String[] args) {
        String nomeArquivo = "dados.dat";

        // Deleta o arquivo se já existir para começar do zero com novos dados
        new File(nomeArquivo).delete();

        // Quantidade de registros que queremos gerar (mude para 100, 1000, etc.)
        int quantidadeRegistros = 15;

        // Banco de nomes para sortear
        String[] nomes = {"Carlos", "Ana", "Beto", "Bruna", "Zeca", "Maria",
                "Joao", "Pedro", "Lucas", "Julia", "Fernanda",
                "Rafael", "Aline", "Marcos", "Camila", "Diego", "Luiza"};

        Random gerador = new Random();

        try (RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "rw")) {

            System.out.println("Gerando " + quantidadeRegistros + " registros desordenados no disco...");

            // Laço para gerar e gravar os registros aleatórios
            for (int i = 0; i < quantidadeRegistros; i++) {
                // Sorteia um código entre 1 e 999
                int codigo = gerador.nextInt(15) + 1;
                // Escolhe um nome aleatório do vetor
                String nome = nomes[gerador.nextInt(nomes.length)];
                // Sorteia uma idade entre 18 e 80 anos
                int idade = gerador.nextInt(63) + 18;

                Registro r = new Registro(codigo, nome, idade);
                r.gravaNoArq(arquivo);
            }

            System.out.println("Arquivo '" + nomeArquivo + "' criado com sucesso!");

            // Exibir os 15 primeiros registros para você ver a bagunça gerada
            System.out.println("\n--- Amostra do Arquivo Desordenado (Primeiros 15) ---");
            arquivo.seek(0);
            Registro temp = new Registro();
            int limiteExibicao = Math.min(quantidadeRegistros, 15);

            for (int i = 0; i < limiteExibicao; i++) {
                temp.leDoArq(arquivo);
                temp.exibirReg();
            }

            System.out.println("\nIniciando a ordenacao...");


            Arquivo_Java meuArq = new Arquivo_Java(nomeArquivo);


            meuArq.countingSort();

            System.out.println("\nOrdenacao concluida! Exibindo os 15 primeiros ordenados:");

            for(int i = 0; i < limiteExibicao; i++){
                meuArq.exibirUmRegistro(i);
            }

        } catch (IOException e) {
            System.err.println("Erro ao manipular o arquivo: " + e.getMessage());
        }
    }
}