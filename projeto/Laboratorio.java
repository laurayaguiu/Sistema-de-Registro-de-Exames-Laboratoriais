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

     public static void mostrarMenu(){
        System.out.println("1. Carregar dados de exames.");
        System.out.println("2. Novo paciente.");
        System.out.println("3. Consultar paciente.");
        System.out.println("4. Finalização dos Atendiemntos.");
        System.out.println("5. Estatísticas.");
        System.out.println("6. Sair.");
    }
        
    public void adicionarPaciente(String cpf, String nome, ArrayList<Exame> examesSolicitados) {

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

       // int contPedido = 0;
        try {
            PedidoExame pE = new PedidoExame(cpf, nome, dataRealz, dataEntrega, examesSolicitados);
            pedidosDia.add(pedidosDia.size(), pE);//pedidosDia.add(contPedido, pE);
            //contPedido++;
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());;
        }
    }
    public Exame buscarExame(String abrev) {
        for (int i = 0; i < listaExamesDisponiveis.size(); i++) {
            try {
                Exame e = listaExamesDisponiveis.get(i);
                if (e.abrev.equals(abrev)) {
                    return e;
                }
            }catch (Exception e) {
                System.out.println("erro");
            }
        }
        return null;
    }

    public PedidoExame procurarPaciente(String cpf){
        for (int i = 0; i<pedidosDia.size(); i++) {
            try {
                PedidoExame e = pedidosDia.get(i);
                if (e.cpf.equals(cpf)) {
                    return e;
                } 
            } catch (Exception e) {
                    System.out.println("erro");
                }
        }
        return null;
    }

    public void consultarPaciente (PedidoExame e) {
        e.mostrarPedido();
    }

    public void finalizarAtendimentos() {
        try {
        
            LocalDate hoje = LocalDate.now();
            //nome arquivo com data atual
            String nomeArquivo = hoje.toString().replace("-", "") + ".txt";

            //criando arquivo do dia
            FileWriter fw = new FileWriter(nomeArquivo);
            BufferedWriter wrt = new BufferedWriter(fw);

            // pega paciente
            for (int i = 0; i < pedidosDia.size(); i++) {
                PedidoExame p = pedidosDia.get(i);
                // formato da linha
                String linha = p.cpf + ";" +
                            p.nome + ";" +
                            p.dataRealz + ";" +
                            p.dataEntreg;

                    //adciona na linha
                for (int j = 0; j < p.examesSolicitados.size(); j++) {
                    linha += ";" + p.examesSolicitados.get(j).abrev;
                }

        
                wrt.write(linha);
                wrt.newLine();
            }

            wrt.close();
            System.out.println("Arquivo gerado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao gerar arquivo");
        }
    }   

    public void verEstatisticas() {
        // qnts vezes os exame aparece
        int[] contagem = new int[listaExamesDisponiveis.size()];
        // contador exames
        int totalExames = 0;

        try {
            for (int i = 0; i < pedidosDia.size(); i++) {
                PedidoExame p = pedidosDia.get(i);
                // soma exames do paciente
                totalExames += p.examesSolicitados.size();
                // percorre exames
                for (int j = 0; j < p.examesSolicitados.size(); j++) {
                    Exame ex = p.examesSolicitados.get(j);
                    for (int k = 0; k < listaExamesDisponiveis.size(); k++) {
                        Exame exLista = listaExamesDisponiveis.get(k);
                        if (exLista.abrev.equals(ex.abrev)) {
                            contagem[k]++;
                        }
                    }
                }
            }

            int totalPacientes = pedidosDia.size();
            double media = 0;
            if (totalPacientes > 0) {
                media = (double) totalExames / totalPacientes;
            }

            System.out.println("=== ESTATÍSTICAS ===");

            for (int i = 0; i < listaExamesDisponiveis.size(); i++) {
                Exame ex = listaExamesDisponiveis.get(i);
                if (contagem[i] > 0) {
                    double percentual = (double) contagem[i] / totalExames * 100;
                    System.out.printf(
                        "%s - %s: %d vezes (%.2f%%) dos Exames Solicitados.\n",
                        ex.abrev,
                        ex.nome,
                        contagem[i],
                        percentual
                    );
                }
            }


            System.out.printf("\nMédia de exames por paciente: %.2f\n", media);

        } catch (Exception e) {
            System.out.println("Erro ao calcular estatísticas");
        }
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
