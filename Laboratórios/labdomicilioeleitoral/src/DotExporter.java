import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class DotExporter {
    public static void exportToDot(Collection<UF> ufs, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("digraph DomicilioEleitoral {\n");
            // Comentários para tornar o DOT mais legível
            writer.write("    // Nós dos Estados (UFs)\n");
            // UFs
            for (UF uf : ufs) {
                String ufId = "UF" + uf.hashCode();
                writer.write("    " + ufId + " [label=\"UF: " + uf.getNome() + "\"];\n");
                writer.write("    " + ufId + " [style=filled, fillcolor=lightblue];\n");

                // Municípios
                for (Municipio m : uf.getMunicipios()) {
                    String mId = "M" + m.hashCode() + "_UF" + uf.hashCode();
                    writer.write("    " + mId + " [label=\"Município: " + m.getNome() + "\"];\n");
                    writer.write("    " + ufId + " -> " + mId + ";\n");

                    // Eleitores
                    for (Pessoa p : m.getEleitores().values()) {
                        String eId = "E" + p.hashCode();
                        writer.write("    " + eId + " [label=\"Eleitor: " + p.getNome() + "\"];\n");
                        // Relação Município -> Eleitor
                        writer.write("    " + mId + " -> " + eId + " [label=\"eleitor\"];\n");
                        // Relação Eleitor -> Município (domicílio eleitoral)
                        writer.write("    " + eId + " -> M" + p.getDomicilioEleitoral().hashCode() +"_UF" +
                            p.getDomicilioEleitoral().getUF().hashCode() + " [label=\"domicilioEleitoral\"];\n");
                    }
                }
            }
            writer.write("}\n");
        }
    }
}