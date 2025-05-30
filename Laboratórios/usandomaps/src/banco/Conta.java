package banco;

public class Conta {
    private String nome;
    
    public String getNome() {
        return nome;
    }

    private float saldo;
    
    public float getSaldo() {
        return saldo;
    }

    public Conta(String nome, float saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }



}
