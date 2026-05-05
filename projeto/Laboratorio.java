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
        System.out.println("---MENU---");
        System.out.println("1. Carregar dados de exames");
        System.out.println("2. Novo paciente");
        System.out.println("3. Consultar paciente");
        System.out.println("4. Finalização dos Atendiemntos");
        System.out.println("5. Estatísticas");
        System.out.println("6. Sair");
        System.out.println("----------");
    }
    
    //cadastra um paciente, calcula data de entrega, cria o pedido    
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

        System.out.println("Os exames estarão disponíveis no dia " + dataEntrega.toString().replace("-", "/") + " a partir das 17h.");

       
        try {
            PedidoExame pE = new PedidoExame(cpf, nome, dataRealz, dataEntrega, examesSolicitados);
            pedidosDia.add(pedidosDia.size(), pE);
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //busca exame na lista linear por abreviação 
    public Exame buscarExame(String abrev) {
        for (int i = 0; i < listaExamesDisponiveis.size(); i++) {
            try {
                Exame e = listaExamesDisponiveis.get(i);
                if (e.abrev.equals(abrev)) {
                    return e;
                }
            }catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return null;
    }

   //buscar paciente pelo cpf
    public PedidoExame procurarPaciente(String cpf){
        for (int i = 0; i<pedidosDia.size(); i++) {
            try {
                PedidoExame e = pedidosDia.get(i);
                if (e.cpf.equals(cpf)) {
                    return e;
                } 
            } catch (Exception e) {
                    System.out.println("Erro ao encontrar paciente: " + e.getMessage());
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

            
            for (int i = 0; i < pedidosDia.size(); i++) {//percorre pacientes
                
                PedidoExame p = pedidosDia.get(i); 
               
                // formato da linha
                String linha = p.cpf + ";" +p.nome + ";" +p.dataRealz.toString().replace("-", "/") + ";" +p.dataEntreg.toString().replace("-", "/");

                    
                for (int j = 0; j < p.examesSolicitados.size(); j++) { // percorre exames do paciente
                    linha += ";" + p.examesSolicitados.get(j).abrev; 
                }

        
                wrt.write(linha);
                wrt.newLine();
            }

            wrt.close();
            System.out.println("Arquivo gerado com sucesso!");

        } catch (Exception e) { // exception mais especifica
            System.out.println("Erro ao gerar arquivo: " + e.getMessage());
        }
    }   

    public void verEstatisticas() {
        // quantas vezes o exame aparece
        int[] contagem = new int[listaExamesDisponiveis.size()];
        int totalExames = 0;

        try {
            for (int i = 0; i < pedidosDia.size(); i++) { // percorre pacientes
                
                PedidoExame p = pedidosDia.get(i); 
                
                
                // percorre exames
                for (int j = 0; j < p.examesSolicitados.size(); j++) { 
                    
                    totalExames++;
                    
                    Exame ex = p.examesSolicitados.get(j);

                    //String abrev = listaExamesDisponiveis.get(j).abrev;
                    String abrev = ex.abrev;
                    
                    for (int k = 0; k < listaExamesDisponiveis.size(); k++) { // correspondencia abrev
                        if (listaExamesDisponiveis.get(k).abrev.equals(abrev)) {
                            contagem[k]++; // conta q esse exame foi solicitado por um paciente
                            break; // achou parou                        
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
                        ex.abrev,ex.nome,contagem[i],percentual);
                }
            }


            System.out.printf("\nMédia de exames por paciente: %.2f\n", media);

        } catch (Exception e) {
            System.out.println("Erro ao calcular estatísticas: " + e.getMessage());
        }
    }
    
    
    public void lerArquivo(){
        try {
            FileReader arq = new FileReader("exames.txt");
            BufferedReader linha = new BufferedReader(arq); 

            //ignora cabeçalho
            String aux = linha.readLine();
            aux = linha.readLine();

            int pos = 0; 

            while(aux != null) {
                // separa dentro de vetor sempre que encontra virgula
                String[] dados = aux.split(",");
                Exame ex = new Exame(dados[0],dados[1],Integer.parseInt(dados[2]));
                listaExamesDisponiveis.add(pos,ex);
                pos++;
                // proximo exame
                aux = linha.readLine();
            }
            arq.close();
        }
        catch(Exception e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
        }
        
    }
}
