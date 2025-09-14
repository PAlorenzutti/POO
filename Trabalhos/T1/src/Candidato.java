import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Candidato implements Comparable<Candidato> {
    private final int numeroVotavel; // número do candidato -> NR_VOTAVEL;
    private final int codigoMuncipio; // código do município -> SG_UE = CD_MUNICIPIO;
    private int codigoCargo; // código do cargo (13) -> CD_CARGO;
    private final String nome; // nome de urna do candidato -> NM_URNA_CANDIDATO;
    private final Partido partido; // partido do candidato;
    private final int numeroFederacao; // número da federação (-1 se for partido isolado) -> NR_FEDERACAO;
    private final LocalDate dataNascimento; // data de nascimento do candidato -> DT_NASCIMENTO;
    private final int situacaoCandidatura; // 2 ou 3 se for eleito, -1 se for candidatura inválida -> CD_SIT_TOT_TURNO;
    private final Genero genero; // 2 = masculino, 4 = feminino -> CD_GENERO;
    private int qtdVotos; // quantidade de votos do candidato -> QT_VOTOS;
    private final boolean foiEleito; // foi eleito ou não;

    public Candidato(
            int numeroVotavel,
            int codigoMuncipio,
            int codigoCargo,
            String nome,
            Partido partido,
            int numeroFederacao,
            String dataNascimento,
            int situacaoCandidatura,
            int genero, int foiEleito) {

        this.numeroVotavel = numeroVotavel;
        this.codigoMuncipio = codigoMuncipio;
        this.nome = nome;
        this.partido = partido;
        this.numeroFederacao = numeroFederacao;
        this.situacaoCandidatura = situacaoCandidatura;
        this.genero = Genero.fromValue(genero);
        this.qtdVotos = 0;
        this.foiEleito = (foiEleito >= 0 && foiEleito <= 3);

        // converte a data passada em String para LocalDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimento, formatter);
    }

    // método para retornar código do cargo;
    public int getCodigoCargo() {
        return codigoCargo;
    }

    // método para retornar código do município;
    public int getCodigoMuncipio() {
        return codigoMuncipio;
    }

    // retorna o número da federação do candidato;
    public int getNumeroFederacao() {
        return numeroFederacao;
    }

    // retorna se o candidato foi eleito;
    public boolean getFoiEleito() {
        return foiEleito;
    }

    // retorna o partido do candidato;
    public Partido getPartido() {
        return partido;
    }

    // retorna o gênero do candidato;
    public Genero getGenero() {
        return genero;
    }

    // retorna o número votável do candidato;
    public int getNumeroVotavel() {
        return numeroVotavel;
    }

    // retorna o nome do candidato;
    public String getNome() {
        return nome;
    }

    // retorna a data de nascimento do candidato;
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // retorna a quantidade de votos do candidato;
    public int getQtdVotos() {
        return qtdVotos;
    }

    // retorna a situação da candidatura;
    public int getSituacaoCandidatura() {
        return situacaoCandidatura;
    }

    // adiciona votos ao candidato e ao partido;
    public void adicionaVotosCandidato(int qtdVotos) {
        this.qtdVotos += qtdVotos;
        partido.adicionaVotosNominaisPartido(qtdVotos);
    }

    // calcula a idade do candidato na data informada;
    public int getIdade(LocalDate dataAtual) {
        // calcula idade;
        int idade = dataAtual.getYear() - dataNascimento.getYear();

        if (dataAtual.getMonthValue() < dataNascimento.getMonthValue()) {
            idade--;
        } else if (dataAtual.getMonthValue() == dataNascimento.getMonthValue()) {
            if (dataAtual.getDayOfMonth() < dataNascimento.getDayOfMonth()) {
                idade--;
            }
        }

        return idade;
    }

    @Override
    public int compareTo(Candidato outroCandidato) {
        if (this.qtdVotos > outroCandidato.qtdVotos) {
            return -1;
        } else if (this.qtdVotos < outroCandidato.qtdVotos) {
            return 1;
        } else {
            // desempate: mais velho primeiro (data de nascimento mais antiga);
            return this.dataNascimento.compareTo(outroCandidato.getDataNascimento());
        }
    }
}
