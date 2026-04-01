import java.io.*;
import java.time.LocalDate;

public class Laboratorio {
    ListaLinear <Exame> listaExamesDisponiveis; // de exames
    ListaLinear <PedidoExame> pedidosDia; // pacientes (nome, cpf...)

    public Laboratorio() {
        listaExamesDisponiveis = ListaLinear <Exame>(150);
        pedidosDia = ListaLinear <PedidoExame>(100);
    }

    
    public adcionarPaciente(String nome, String cpf){
        int cont;
        int num = 1;
        PedidoExame p1 = new PedidoExame(nome,cpf);
        pedidosDia.add(p1);
        System.out.print("CPF: ");
        System.out.println("\nNOME: ");
        for(int i; i<exames.size();i++){
            System.out.print("EXAME " + num + ":" + exames[i]+ );
            num++;
        }
        dataRealz = LocalDate.now();
        dataEntreg = dataRealz.plusDays(maiorQtdDias);
        System.out.println("\nOs exames estarão disponiveís no dia" + dataEntrega +" a partir das 17h.")
        

        
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
            System.out.println("Erro")
        }
        
    }
}