import java.util.Collection;
import java.util.HashSet;

public class UF {
    private String nome;
    private String sigla;
    private Collection<Municipio> municipios = new HashSet<>();
    
    public UF(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Municipio criarMunicipio(String nome){
        Municipio municipio = new Municipio(nome);
        this.municipios.add(municipio);
        municipio.setUF(this);
        return municipio;
    }

    public Collection<Municipio> getMunicipios(){
        return new HashSet<>(this.municipios);
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }
}
