import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Partido implements Comparable<Partido> {
    private final int numero; // número do partido do candidato -> NR_PARTIDO;
    private final String sigla; // sigla do partido do candidato -> SG_PARTIDO;
    private int qtdVotosLegenda; // quantidade de votos de legenda do partido;
    private int qtdVotosNominais; // quantidade de votos de todos os candidatos do partido somados;
    private final Map<Integer, Candidato> candidatos = new LinkedHashMap<>(); // lista de candidatos do partido;

    public Partido(int numero, String sigla) {
        this.numero = numero;
        this.sigla = sigla;
        this.qtdVotosLegenda = 0;
    }

    // retorna a sigla do partido;
    public String getSigla() {
        return sigla;
    }

    // retorna o número do partido;
    public int getNumero() {
        return numero;
    }

    // retorna a quantidade de votos de legenda do partido;
    public int getQtdVotosLegenda() {
        return qtdVotosLegenda;
    }

    // retorna a quantidade de votos nominais do partido;
    public int getQtdVotosNominais() {
        return qtdVotosNominais;
    }

    // retorna a quantidade total de votos do partido;
    public int getQtdVotos() {
        return qtdVotosLegenda + qtdVotosNominais;
    }

    // cria e adiciona um candidato ao partido;
    public Candidato criaCandidato(int numeroVotavel, int codigoMuncipio, int codigoCargo,
            String nome, int numeroFederacao, String dataNascimento,
            int situacaoCandidatura, int genero, int foiEleito) {
        Candidato c = new Candidato(numeroVotavel, codigoMuncipio, codigoCargo, nome, this, numeroFederacao,
                dataNascimento, situacaoCandidatura, genero, foiEleito);
        candidatos.put(c.getNumeroVotavel(), c);
        return c;
    }

    // adiciona votos nominais ao partido;
    public void adicionaVotosNominaisPartido(int qtdVotos) {
        this.qtdVotosNominais += qtdVotos;
    }

    // adiciona votos de legenda ao partido;
    public void adicionaVotosLegendaPartido(int qtdVotos) {
        this.qtdVotosLegenda += qtdVotos;
    }

    // retorna todos os candidatos do partido;
    public Map<Integer, Candidato> getCandidatos() {
        return new LinkedHashMap<>(candidatos);
    }

    // retorna o candidato mais votado do partido;
    public Candidato getCandidatoMaisVotado() {
        ArrayList<Candidato> candidatosComVotos = new ArrayList<>();
        candidatosComVotos.addAll(this.candidatos.values());
        Collections.sort(candidatosComVotos);
        return candidatosComVotos.getFirst();
    }

    // retorna o conjunto de candidatos eleitos do partido;
    public Set<Candidato> getCandidatosEleitos() {
        Set<Candidato> candidatosEleitos = new HashSet<>();
        for (Candidato c : this.getCandidatos().values()) {
            if (c.getFoiEleito())
                candidatosEleitos.add(c);
        }
        return candidatosEleitos;
    }

    // retorna a quantidade de candidatos eleitos do partido;
    public int getQtdCandidatosEleitos() {
        return this.getCandidatosEleitos().size();
    }

    // compara partidos por votos totais e, em caso de empate, pelo número;
    @Override
    public int compareTo(Partido p) {
        int diffVotos = p.getQtdVotos() - this.getQtdVotos();
        if (diffVotos == 0) {
            return this.getNumero() - p.getNumero();
        }
        return diffVotos;
    }
}
