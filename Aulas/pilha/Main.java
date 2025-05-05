public class Main {
    public static void main(String[] args) {
        Pilha p = new Pilha();
        
        p.empilha("oi1");
        p.empilha("oi2");
        p.empilha("oi3");
        p.empilha("oi4");
        p.empilha("oi5");
        System.out.println(p.getTamanho());
        while(!p.isVazia())
        {
            System.out.println(p.desempilha());
        }
        System.out.println(p.getTamanho());
        System.out.println("Passou!");
    }
}
