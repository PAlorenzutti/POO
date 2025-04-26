import java.util.LinkedList;

public class Agencia {
    private LinkedList<Conta> contas;  // exemplo de reuso de lista encadeada
    private String identificador;

    public Agencia(String identificador)
    {
        this.contas = new LinkedList<Conta>();
        this.identificador = identificador;
    }

    public String getIdentificador()
    {
        return identificador;
    }    
    public void adicionaConta(Conta c)
    {
        contas.add(c);
    }
    public LinkedList<Conta> getContas()
    {
        return new LinkedList<Conta>(this.contas);
    }
    public LinkedList<Conta> getContasTitular(Pessoa titular)
    {
        LinkedList<Conta> contasDoTitular = new LinkedList<Conta>();
        for (Conta c : this.contas)
        {
            if (c.getTitular()==titular) 
            {
                contasDoTitular.add(c);
            }
        }
        return contasDoTitular;
    }
}
