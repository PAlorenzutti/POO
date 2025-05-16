import java.time.LocalDate;

public class Pessoa{
    private String nome;
    private LocalDate nascimento;

    public Pessoa(String nome, int year, int month, int day){
        this.nome = nome;
        this.nascimento = LocalDate.of(year, month, day);
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade(){
        LocalDate atual = LocalDate.now();

        int idade = atual.getYear() - this.nascimento.getYear();

        if(atual.getMonthValue() < this.nascimento.getMonthValue()){
            idade--;
        }else if(atual.getMonthValue() == this.nascimento.getMonthValue()){
            if(atual.getDayOfMonth() < this.nascimento.getDayOfMonth()){
                idade--;
            }
        }

        return idade;
    }
}