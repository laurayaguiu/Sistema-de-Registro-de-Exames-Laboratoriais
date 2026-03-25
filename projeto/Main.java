/*
Laura Rocha Yaguiu RA: 10736399
Stephanie Soares Dias Ra: 10223952
*/
public class Main {

    public static void main (String [] args) throws Exception {
        ListaLinear<Exame> listaExamesDisponiveis = new ListaLinear<>(10);
        // contrucao menu e chamada de operacoes
        //Exame e1 = new Exame("HDL", "Colesterol", 5);
        Exame e2 = new Exame("COL", "Colesterol total", 5);
        Exame e1 = new Exame("BHC", "Beta HCG", 6);
        

       listaExamesDisponiveis.add(0, e2);
       listaExamesDisponiveis.add(1, e1);

        System.out.println(listaExamesDisponiveis.search(e1));
    }
}