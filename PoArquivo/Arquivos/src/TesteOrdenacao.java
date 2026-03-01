import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;

public class TesteOrdenacao {
    public static void main(String[] args) {
        String nomeArquivo = "dados.dat";

        // Deleta o arquivo se já existir para começar do zero
        new File(nomeArquivo).delete();

        try (RandomAccessFile arquivo = new RandomAccessFile(nomeArquivo, "rw")) {
            // Criando registros desordenados (Código, Nome, Idade)
            Registro[] registros = {
                    new Registro(45, "Carlos", 30),
                    new Registro(12, "Ana", 22),
                    new Registro(89, "Beto", 45),
                    new Registro(23, "Bruna", 19),
                    new Registro(7, "Zeca", 55)
            };

            System.out.println("Gravando registros desordenados...");
            for (Registro r : registros) {
                r.gravaNoArq(arquivo);
            }

            System.out.println("Arquivo 'dados.dat' criado com sucesso!");

            // Exibir como o arquivo está antes da ordenação
            System.out.println("\n--- Conteúdo Atual do Arquivo ---");
            arquivo.seek(0);
            Registro temp = new Registro();
            long totalRegistros = arquivo.length() / Registro.length();

            for (int i = 0; i < totalRegistros; i++) {
                temp.leDoArq(arquivo);
                temp.exibirReg();
            }

        } catch (IOException e) {
            System.err.println("Erro ao manipular o arquivo: " + e.getMessage());
        }
    }
}