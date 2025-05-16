import java.util.HashSet;


public class Empresa {
    private String nome;

    private HashSet<Departamento> departamentos;

    Empresa(String nome){
        this.nome = nome;

        departamentos = new HashSet<>();
    }

    String getNome(){
        return this.nome;
    }

    void addDepartamento(Departamento departamento){
        this.departamentos.add(departamento);
    }

    void removeDepartamento(Departamento departamento){
        this.departamentos.remove(departamento);
    }  

    float getMediaIdadeEmpresa(){
        int totalPessoas = 0;
        int totalIdade = 0;

        for(Departamento departamento : this.departamentos){
            totalPessoas += departamento.getSizePessoas();
            totalIdade += departamento.totalIdade();
        }

        return (float) totalIdade / totalPessoas;
    }
}
