import java.util.Arrays;

public class No {
    private char[] vLetras;
    private No[] vLig;
    private int dif;
    private String palavra;


    public No() {
        vLetras = new char[26];
        vLig = new No[26];
        palavra = null;
        dif = 0;
    }

    public No(String palavra) {
        this();
        this.palavra = palavra;
    }






    public char getvLetras(int pos) {
        return vLetras[pos];
    }

    public void setvLetras(int pos ,char letra) {
        this.vLetras[pos] = letra;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No vLig) {
        this.vLig[pos] = vLig;
    }

    public int getDif() {
        return dif;
    }

    public void setDif(int dif) {
        this.dif = dif;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int buscaPos(String palavra) {
        int pos = 0;
        while(pos < palavra.length() && pos < this.palavra.length() && palavra.charAt(pos) == this.palavra.charAt(pos))
            pos++;

        return pos;
    }

    public boolean isFolha() {
        boolean folha = true;
        int i = 0;
        while (i < 26 && folha) {
            if (vLig[i] != null) folha = false;
            i++;
        }
        return folha;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Dif: ").append(dif);
        if (palavra != null) {
            sb.append(" | Palavra: ").append(palavra);
        }
        sb.append(" | Letras ativas: ");
        for (int i = 0; i < 26; i++) {
            if (vLig[i] != null) {
                sb.append(vLetras[i]).append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
