import java.util.HashMap;

public class Municipio {
    private String nome;
    private UF uf;
    private HashMap<String, Pessoa> eleitores;

    public UF getUF() {
        return uf;
    }

    public void setUF(UF uf){
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public Municipio(String nome) {
        this.nome = nome;
        this.eleitores = new HashMap<>();
    }

    public HashMap<String, Pessoa> getEleitores(){
        return this.eleitores;
    }

    public void adicionaEleitor(Pessoa eleitor){
        if(!this.eleitores.containsKey(eleitor.getCpf())){
            this.eleitores.put(eleitor.getCpf(), eleitor);
            eleitor.setDomicilioEleitoral(this);
        }
        
        
    }

    public void removeEleitor(Pessoa eleitor){
        this.eleitores.remove(eleitor.getCpf());
    }
}
