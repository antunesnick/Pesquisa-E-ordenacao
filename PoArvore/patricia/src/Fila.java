public class Fila {
    NoFila ini;
    NoFila fim;



    public Fila() {
        ini = fim = null;
    }

    public boolean isEmpty() {
        return ini == null;
    }

    public void inserir(No info) {
        NoFila novoNo = new NoFila(info);

        if(ini == null) {
            ini = fim = novoNo;
        }
        else {
            fim.setProx(novoNo);
            fim = novoNo;
        }
    }

    public No retirar() {
        No info = ini.getInfo();
        if(ini == fim) {
            ini = fim = null;
        }
        else {
            ini = ini.getProx();
        }
        return info;
    }

}