import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Laboratorio {
    ListaLinear<Exame> listaExamesDisponiveis;
    ListaLinear<PedidoExame> pedidosDia;

    public Laboratorio() {
        listaExamesDisponiveis = new ListaLinear<Exame>(150);
        pedidosDia = new ListaLinear<PedidoExame>(100);
    }

    public void adcionarPaciente(String nome, String cpf, ArrayList<Exame> examesSolicitados) {

        int cont = 1;
        int qtDiasMaior = 0;

        System.out.println("CPF: " + cpf);
        System.out.println("NOME: " + nome);

        for (int i = 0; i < examesSolicitados.size(); i++) {
            Exame e = examesSolicitados.get(i);

            if (e.qtDias > qtDiasMaior) {
                qtDiasMaior = e.qtDias;
            }

            System.out.println("EXAME " + cont + ": " + e);
            cont++;
        }

        LocalDate dataRealz = LocalDate.now();
        LocalDate dataEntrega = dataRealz.plusDays(qtDiasMaior);

        System.out.println("Os exames estarão disponíveis no dia " + dataEntrega + " a partir das 17h.");
    }

    public Exame buscarExame(String abrev) {
        for (int i = 0; i < listaExamesDisponiveis.size(); i++) {
            try {
                 Exame e = listaExamesDisponiveis.get(i);

                if (e.abrev.equalsIgnoreCase(abrev)) {
                    return e;
                }
            }catch (Exception e) {
                System.out.println("erro");
            }
        }
        return null;
    }



    // throws Exception só funciona no mai, aqui usa try catch
    public void lerArquivo(){
        try {
            FileReader arq = new FileReader("exames.txt");
            BufferedReader linha = new BufferedReader(arq); // leitura linha a linha do txt

            //ler a linha de cabeçalho txt mas nao mostrar
            String aux = linha.readLine();

            //ler a 1 linha (1 exame) e comecar a mostrar
            aux = linha.readLine();

            int pos = 0; //contador

            //enquanto ainda tem exame para ler ou pelo menos 1
            while(aux != null) {
                // sempre que encontrar uma virgula entende q é uma informacao
                // separa dentro de vetor
                String[] dados = aux.split(",");

                // cria obj do tipo exame
                // integer.parseInt pq o vetor é do tipo string mas qtDias é int
                Exame ex = new Exame(dados[0],dados[1],Integer.parseInt(dados[2]));
                //precisa colocar ele dentro da lista examesdispo
                listaExamesDisponiveis.add(pos,ex);
                pos++; // sempre colocando em uma posicao na frente

                // proximo exame
                aux = linha.readLine();
            }
            arq.close();
        }
        catch(Exception e) {
            System.out.println("Erro");
        }
        
    }
}
