public class Conta {
    private float saldo;
    private String identificador;
    private Pessoa titular;
    
    public Conta(String identificador, Pessoa titular)
    {
        this.titular=titular;
        this.identificador=identificador;
    }
    public String getIdentificador()
    {
        return identificador;
    }

    public void depositar(float valor)
    {
        if (valor<0.0) return;
        this.saldo=this.saldo+valor;
    }
    public void sacar(float valor)
    {
        if (valor<=this.saldo)
        {
            this.saldo-=valor;
        }
    }

    public float getSaldo()
    {
        return this.saldo;
    }
    public Pessoa getTitular()
    {
        return this.titular;
    }

}
