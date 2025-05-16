
import java.util.HashSet;

public class Departamento {
    private String nome;
    private HashSet<Pessoa> pessoas;

    Departamento(String nome){
        this.nome = nome;
        this.pessoas = new HashSet<>();
    }

    String getNome(){
        return this.nome;
    }

    void addPessoa(Pessoa pessoa){
        this.pessoas.add(pessoa);
    }

    void removePessoa(Pessoa pessoa){
        this.pessoas.remove(pessoa);
    }

    int totalIdade(){
        int total = 0;

        for(Pessoa pessoa : this.pessoas){
            total += pessoa.getIdade();
        }

        return total;
    }

    int getSizePessoas(){
        return this.pessoas.size();
    }
}
