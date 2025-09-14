
import java.util.Comparator;

public class PartidoCandidatoComparator implements Comparator<Partido> {
    @Override
    public int compare(Partido p1, Partido p2) {
        // pega os dois candidatos mais votados de cada partido;
        // retorna quem tem o candidato mais votado;
        return p2.getCandidatoMaisVotado().getQtdVotos() - p1.getCandidatoMaisVotado().getQtdVotos();
    }
}
