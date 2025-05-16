import java.util.HashSet;


public class Empresa {
    private String nome;

    private HashSet<Departamento> departamentos;

    public Empresa(String nome){
        this.nome = nome;

        departamentos = new HashSet<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void addDepartamento(Departamento departamento){
        this.departamentos.add(departamento);
    }

    public void removeDepartamento(Departamento departamento){
        this.departamentos.remove(departamento);
    }  

    public float getMediaIdadeEmpresa(){
        int totalPessoas = 0;
        int totalIdade = 0;

        for(Departamento departamento : this.departamentos){
            totalPessoas += departamento.getNumeroPessoas();
            
            for(Pessoa pessoa : departamento.getPessoas()){
                totalIdade += pessoa.getIdade();
            }
        }

        if(totalPessoas != 0){
            return (float) totalIdade / totalPessoas;
        }else{
            return 0;
        }
    }
}
