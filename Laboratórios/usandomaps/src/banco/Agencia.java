package banco;

import java.util.HashMap;
import java.util.Map;

public class Agencia {
    private String nome;
    private Map<String, Conta> contas = new HashMap<>();

    public Agencia(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Conta adicionaConta(String nomeCliente, float saldoInicial){
        Conta c = new Conta(nomeCliente, saldoInicial);
        contas.put(nomeCliente, c);
        return c;
    }

    public Map<String, Conta> getContas(){
        return this.contas;
    }

    public Conta obtemConta(String nomeCliente){
        return this.contas.get(nomeCliente);
    }

    public void removeConta(String nomeCliente){
        this.contas.remove(nomeCliente);
    }
}
