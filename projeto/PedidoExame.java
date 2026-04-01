import java.util.*;
import java.time.*;

public class PedidoExame {
    String cpf;
    String nomeP;
    LocalDate dataRealz;
    LocalDate dataEntreg;
    ArrayList<String> exames; 

    public PedidoExame(String cpf, String nomeP, LocalDate dataRealz, LocalDate dataEntreg) {
        this.cpf = cpf;
        this.nomeP = nomeP;
        this.dataRealz = dataRealz;
        this.dataEntreg = dataEntreg;
        exames = new ArrayList<String>();
    }
}