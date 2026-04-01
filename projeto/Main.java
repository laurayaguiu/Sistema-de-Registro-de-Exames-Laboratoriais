import java.util.*;
/*
Laura Rocha Yaguiu RA: 10736399
Stephanie Soares Dias Ra: 10223952
*/
public class Main {

    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Laboratorio lab = new Laboratorio();
        int opcao;
         
        do{
            mostrartMenu();
            System.out.println("Qual opção você deseja acessar?");
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Aplicação encerrada!\n Integrantes: Laura Yaguiu e Stephanie Dias");
                    break;

            } while (opcao!= 6);
        Scanner.close();
        }






    }

    public satic void mostrarMenu(){
        System.out.println("1. Carregar dados de exames.");
        System.out.println("2. Novo paciente.");
        System.out.println("3. Consultar paciente.");
        System.out.println("4. Finalização dos Atendiemntos.");
        System.out.println("5. Estatísticas.");
        System.out.println("6. Sair.");


    }
}