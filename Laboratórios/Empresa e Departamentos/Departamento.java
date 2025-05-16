
import java.util.HashSet;

public class Departamento {
    private String nome;
    private HashSet<Pessoa> pessoas;

    public Departamento(String nome){
        this.nome = nome;
        this.pessoas = new HashSet<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void addPessoa(Pessoa pessoa){
        this.pessoas.add(pessoa);
    }

    public void removePessoa(Pessoa pessoa){
        this.pessoas.remove(pessoa);
    }

    public HashSet<Pessoa> getPessoas(){
        return pessoas;
    }

    public int getNumeroPessoas(){
        return this.pessoas.size();
    }
}
