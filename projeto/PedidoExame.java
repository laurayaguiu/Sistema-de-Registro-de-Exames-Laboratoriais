import java.util.*;
import java.time.*;

public class PedidoExame {
    String cpf;
    String nome;
    LocalDate dataRealz;
    LocalDate dataEntreg;
    ArrayList<Exame> examesSolicitados; 

    public PedidoExame(String cpf, String nome, LocalDate dataRealz, LocalDate dataEntreg, ArrayList<Exame> examesSolicitados) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataRealz = dataRealz;
        this.dataEntreg = dataEntreg;
        this.examesSolicitados = new ArrayList<Exame>();
    }

    public void mostrarPedido(){
        System.out.println("CPF: " +cpf+ "\nNOME: " + nome + " DATA REALIZAÇÃO: " + dataRealz);
        for (Exame e : examesSolicitados) {
            System.out.println(e.toString());
        }
        System.out.println("DATA LIBERAÇÃO RESULTADOS: " + dataEntreg);
    }
}