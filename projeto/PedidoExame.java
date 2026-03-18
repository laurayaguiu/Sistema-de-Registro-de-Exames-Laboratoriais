import java.util.*;
import java.time.*;

public class PedidoExame {
    private String cpf;
    private String nomeP;
    private localDate dataRealz;
    private localDate dataEntreg;
    private ArrayList<String> exames; 

    public PedidoExame(String cpf, String nomeP, localDate dataRealz, localDate dataEntreg) {
        this.cpf = cpf;
        this.nomeP = nomeP;
        this.dataRealz = dataRealz;
        this.dataEntreg = dataEntreg;
        exames = new ArrayList<String>();
    }
}