import java.util.LinkedList;

/**
 * Uma pilha é uma coleção em que os últimos elementos
 * adicionados são os primeiros a serem removidos
 * (Last-In-First-Out). Esta classe implementa uma pilha
 * de strings.
 */
public class Pilha
{
    /** 
    * A classe Pilha funciona como um wrapper para a classe 
    * LinkedList<String>. Ela fornece uma interface mais 
    * restrita e especializada (de pilha LIFO) para uma 
    * estrutura de dados mais genérica. Ao fazer isso, 
    * oculta os detalhes da implementação e expõe apenas 
    * os métodos relevantes (empilha, desempilha, etc.).
    */
    private LinkedList<String> items;

    public Pilha()
    {
        this.items=new LinkedList<>();
    }

    /**
     * Empilha uma string s no topo da pilha.
     */
    public void empilha(String s)
    {
        items.addLast(s);
    }

    /**
     * Retorna o elemento no topo da pilha, retirando-o
     * da pilha.
     * 
     * Pré-condição: pilha não está vazia
     */
    public String desempilha()
    {
        return items.removeLast();
    }

    /**
     * Indica se a pilha está vazia.
     */
    public boolean isVazia()
    {
        return items.isEmpty();
    }

    /*
     * Retorna o número de elementos na pilha.
     */
    public int getTamanho()
    {
        return items.size();
    }
}