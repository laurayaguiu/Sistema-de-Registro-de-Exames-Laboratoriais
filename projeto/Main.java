import java.util.*;
/*
Laura Rocha Yaguiu RA: 10736399
Stephanie Soares Dias Ra: 10223952
*/
public class Main {

    public static void mostrarMenu(){
        System.out.println("1. Carregar dados de exames.");
        System.out.println("2. Novo paciente.");
        System.out.println("3. Consultar paciente.");
        System.out.println("4. Finalização dos Atendiemntos.");
        System.out.println("5. Estatísticas.");
        System.out.println("6. Sair.");
    }



    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Laboratorio lab = new Laboratorio();
        int opcao;
        boolean carregarExames = false;
        String cpf;
        String nome;
         
        do {
            mostrarMenu();
            System.out.println("Qual opção você deseja acessar?");
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    lab.lerArquivo();
                    carregarExames = true;
                    break;
                case 2:
                    if (!carregarExames) {
                        System.out.println("Carregue exames primeiro!");
                        break;
                    }
                    ArrayList<Exame> examesSolicitados = new ArrayList<>();
                    sc.nextLine(); 
                    System.out.print("CPF: ");
                    cpf = sc.nextLine();

                    System.out.print("NOME: ");
                    nome = sc.nextLine();

                    boolean maisExames = true;

                    while (maisExames) {
                        System.out.println("Abreviação Exames (XXX para sair):");
                        String abrev = sc.nextLine().toUpperCase();

                        if (abrev.equals("XXX")) {
                            break;
                        }

                        Exame e = lab.buscarExame(abrev);

                        if (e != null) {
                            examesSolicitados.add(e);
                        } else {
                            System.out.println("Exame não encontrado!");
                        }
                    }
                    lab.adcionarPaciente(cpf, nome, examesSolicitados);
                    break;
                case 3:
                    if (!carregarExames) {
                        System.out.println("Carregue exames primeiro!");
                        break;
                    }
                    sc.nextLine(); 
                    System.out.println("Informe CPF de consulta:");
                    cpf = sc.nextLine();

                    PedidoExame e = lab.procurarPaciente(cpf);
                    if (e != null) {
                            lab.consultarPaciente(e);
                        } else {
                            System.out.println("Paciente não encontrado!");
                        }
                    break;
                case 4:
                    if (!carregarExames) {
                        System.out.println("Carregue exames primeiro!");
                        break;
                    }
                    break;
                case 5:
                    if (!carregarExames) {
                        System.out.println("Carregue exames primeiro!");
                        break;
                    }
                    break;
                case 6:
                    System.out.println("Aplicação encerrada!\n Integrantes: Laura Yaguiu e Stephanie Dias");
                    break;

            } 
        } while (opcao!= 6);
        //sc.close();
    }
}