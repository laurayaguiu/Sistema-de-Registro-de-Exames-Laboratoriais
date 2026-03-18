public class ListaLinear {
    Exame[] A; // vetor de obj do tipo exame , precisa trocar pra generico(pesquisar)
    int capacity; // capacidade do vetor
    int size; // elementos no vetor,quant tem

    public ListaLinear (int capacity) {
        A = new Exame[capacity]; //flexibilidade 
        this.size = 0;
        this.capacity = capacity;
    }
    public boolean isEmpty() {
        // verificar se vetor está vazio
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
    public int size() {
        // retorna quant de elem na lista, serve d limitador
        /*for (String a : A) {
            size ++
        }*/
       return size;
    }
    public Exame get(Exame i) throws Exception {
        // lança exception, mostra oq tem na posicao i
        if (i >= size) {
            throw new Exception ("Posição inválida");
            
        }
        return A[i];
        
    }
    public void set(int i, Exame n) throws Exception {
        // altera o conteudo da posicao i por n, substituicao
        if (i >= size) {
            throw new Exception ("Posição inválida");
            
        }
        A[i] = n;
    }
    public void add(int i, Exame n) throws Exception {
        // insere novo element na posicao esp
        if (size == capacity) {
            throw new Exception ("A lista está cheia!");
        }
        else if (i>size) {
            throw new Exception ("Posição de inseção Inválida!");
        }
        for (int z = size; z > i; z--) {
            A[z] = A[z-1];
        }
        A[i] = n;
        size++;

    }
    public void remove(int i) throws Exception {
        // remove conteudo posi i
        if (isEmpty()) {
            throw new Exception("A lista está vazia !");
        } else if (i >= size) {
            throw new Exception("Posição inválida");
        }
        for (int z = i; z < size-1; z++){
            A[z]=A[z+1];
        }
        size--;
    }
    public int search(Exame n) {
        for(int i =0; i<size; i++){
            // se o nome q ta no vetor é igual ao parametro
            if (A[i].nome.equals(n.nome)){
                return i;
            }
            
        }
        return -1;
    }
    public void mostrarLista() {
        System.out.println("\nElementos da lista");
        System.out.println("=================");
        for (int i =0; i<size; i++){
            System.out.println(A[1].abrev);
            System.out.println(A[1].nome);
            System.out.println(A[1].qtDias);
        }
    }
}