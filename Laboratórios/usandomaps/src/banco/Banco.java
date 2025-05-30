package banco;

import java.util.HashMap;
import java.util.Map;

public class Banco {

    private String nome;
    private Map<String,Agencia> agencias = new HashMap<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Agencia criaAgencia(String nome) {
        Agencia a = new Agencia(nome);
        agencias.put(nome,a);
        return a;
    }

    public Agencia obtemAgencia(String nome){
        return agencias.get(nome);
    }

    public void removeAgencia(String nome){
        
        this.agencias.remove(nome);
    }
}
