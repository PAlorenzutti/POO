public class Pessoa {
    private String nome;
    private String cpf;
    private Municipio domicilioEleitoral;
    
    public Municipio getDomicilioEleitoral() {
        return domicilioEleitoral;
    }

    public void setDomicilioEleitoral(Municipio municipio){
        if(municipio != this.domicilioEleitoral){
            this.domicilioEleitoral.removeEleitor(this);
            this.domicilioEleitoral = municipio;
            municipio.adicionaEleitor(this);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa(String nome, String cpf, Municipio municipio) {
        this.nome = nome;
        this.cpf = cpf;
        this.domicilioEleitoral = municipio;
        municipio.adicionaEleitor(this);
    }
}
