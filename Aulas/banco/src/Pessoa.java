public class Pessoa
{
    private String cpf;     // cpf imutável, não terá setter
    private String nome;    
    
    /* Construtor que obriga a definir cpf e nome na criação */
    public Pessoa(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}