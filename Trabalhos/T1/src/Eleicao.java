import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Eleicao {
    private final String eleicaoPath; // caminho do arquivo votacao_secao;
    private final String candidatosPath; // caminho do arquivo consulta_cand;
    private final int codigoCidade; // código da cidade;
    private final LocalDate dataAtual; // data de referência da eleição;
    private final Map<Integer, Partido> partidos = new HashMap<>(); // hashmap de todos os partidos envolvidos
                                                                    // na eleição da cidade em questão;

    public Eleicao(int codigoCidade, String candidatosPath, String eleicaoPath, String dataAtual) {
        this.eleicaoPath = eleicaoPath;
        this.candidatosPath = candidatosPath;
        this.codigoCidade = codigoCidade;

        // formata a data recebida em string para LocalDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataAtual = LocalDate.parse(dataAtual, formatter);
    }

    // função para ler uma linha (e seus respectivos valores) do arquivo .csv;
    private List<String> parseCSVLine(String line) {
        List<String> valores = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ';' && !inQuotes) {
                valores.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        valores.add(sb.toString());
        return valores;
    }

    // leitura do arquivo consulta_cand;
    public void leArquivoCandidatos() {
        String filePath = this.candidatosPath;
        String linha;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "ISO-8859-1"))) {
            br.readLine(); // lê e descarta o cabeçalho;

            while ((linha = br.readLine()) != null) {
                List<String> valores = parseCSVLine(linha);

                Partido partido;
                // se o partido não existir, cria um novo partido
                // se existir, pega o partido existente
                if (!partidos.containsKey(Integer.valueOf(valores.get(25)))) {
                    partido = new Partido(Integer.parseInt(valores.get(25)), valores.get(26));
                    partidos.put(partido.getNumero(), partido);

                } else {
                    partido = partidos.get(Integer.valueOf(valores.get(25)));
                }

                // se a linha for referente ao município que estamos procurando e for referente
                // ao cargo de vereador;
                if (codigoCidade == Integer.parseInt(valores.get(11).trim())
                        && ("VEREADOR".equals(valores.get(14).trim())) && (Integer.parseInt(valores.get(48)) != -1)) {
                    // cria o candidato e adiciona ao partido em questão;
                    partido.criaCandidato(
                            Integer.parseInt(valores.get(16)),
                            Integer.parseInt(valores.get(11)),
                            Integer.parseInt(valores.get(13)),
                            valores.get(18),
                            Integer.parseInt(valores.get(28)),
                            valores.get(36),
                            Integer.parseInt(valores.get(22)),
                            Integer.parseInt(valores.get(38)),
                            Integer.parseInt(valores.get(48)));

                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // leitura do arquivo votacao_secao;
    public void leArquivoVotacao() {
        String filePath = this.eleicaoPath;
        String linha;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "ISO-8859-1"))) {
            br.readLine(); // lê e descarta o cabeçalho;

            while ((linha = br.readLine()) != null) {
                List<String> valores = parseCSVLine(linha);

                // pega a lista de todos os candidatos;
                LinkedHashMap<Integer, Candidato> candidatos = (LinkedHashMap<Integer, Candidato>) this.getCandidatos();

                // se a linha for referente ao município que estamos procurando e for referente
                // ao cargo de vereador;
                if (codigoCidade == Integer.parseInt(valores.get(11).trim())
                        && Integer.parseInt(valores.get(17).trim()) == 13
                        && !(Integer.parseInt(valores.get(19).trim()) >= 95
                                && Integer.parseInt(valores.get(19).trim()) <= 98)) {

                    // para votos em candidatos;
                    if (valores.get(19).trim().length() > 2) {
                        // pega o candidato por meio do número votável;
                        Candidato candidato = candidatos.get(Integer.valueOf(valores.get(19)));

                        // adiciona a quantidade de votos ao candidato e ao partido dele;
                        candidato.adicionaVotosCandidato(Integer.parseInt(valores.get(21)));
                        // para votos de legenda;
                    } else {
                        // pega o partido em questão;
                        int numeroPartido = Integer.parseInt(valores.get(19).trim());
                        Partido partido = partidos.get(numeroPartido);

                        // adiciona o votos de legenda ao partido;
                        partido.adicionaVotosLegendaPartido(Integer.parseInt(valores.get(21).trim()));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // método para pegar todos os candidatos da eleição;
    public Map<Integer, Candidato> getCandidatos() {
        LinkedHashMap<Integer, Candidato> candidatos = new LinkedHashMap<>();

        // para cada partido, pega os candidatos;
        for (Partido partido : partidos.values()) {
            candidatos.putAll(partido.getCandidatos());
        }

        return candidatos;
    }

    // método para pegar o número de candidatos eleitos;
    public int getQtdCandidatosEleitos() {
        int qtd = 0;

        // para cada partido, pega o número de candidatos eleitos do mesmo;
        for (Partido p : this.partidos.values()) {
            qtd += p.getQtdCandidatosEleitos();
        }

        return qtd;
    }

    // gera relatório dos vereadores eleitos;
    public void geraRelatorioCandidatosEleitos() {
        String s = "";

        // instancia o NumberFormat a fim de imprimir a quantidade de votos da forma
        // 0.000 (ex: 1.389 votos);
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));

        // pega a lista de candidatos eleitos em cada partido e ordena conforme o
        // compareTo de Candidato;
        ArrayList<Candidato> candidatosEleitos = new ArrayList<>();

        for (Partido p : this.partidos.values()) {
            candidatosEleitos.addAll(p.getCandidatosEleitos());
        }

        Collections.sort(candidatosEleitos);

        s += "Número de vagas: " + this.getQtdCandidatosEleitos() + "\n\n";
        s += "Vereadores eleitos:\n";

        // variável de controle de índices do for;
        int i = 1;

        // imprime nome, partido e quantidade de votos de cada variável eleito em sua
        // respectiva posição;
        for (Candidato c : candidatosEleitos) {
            String linha = (i++ + " - ");
            if (c.getNumeroFederacao() != -1)
                linha += "*";
            linha += c.getNome() + " (" + c.getPartido().getSigla() + ", " + numberFormat.format(c.getQtdVotos())
                    + " votos)";
            s += linha + "\n";
        }
        System.out.println(s);
    }

    // gera relatório dos candidatos mais votados;
    public void geraRelatorioCandidatosTotal() {
        String s = "";

        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));

        s += "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):\n";

        // pega a lista de todos os candidatos presentes na eleição;
        ArrayList<Candidato> candidatosTotais = new ArrayList<>();
        candidatosTotais.addAll(this.getCandidatos().values());
        Collections.sort(candidatosTotais);

        int i = 1;

        // imprime nome, partido e quantidade de votos de todos os candidatos em ordem
        // de quantidade de votos;
        for (Candidato c : candidatosTotais) {
            String linha = (i++ + " - ");
            if (c.getNumeroFederacao() != -1)
                linha += "*";
            linha += c.getNome() + " (" + c.getPartido().getSigla() + ", " + numberFormat.format(c.getQtdVotos())
                    + " votos)";
            s += linha + "\n";
            if (i == this.getQtdCandidatosEleitos() + 1)
                break;
        }
        System.out.println(s);
    }

    // gera relatório dos que teriam sido eleitos se a votação fosse majoritária e
    // dos que se beneficiaram da eleição majoritária;
    public void geraRelatorioMajoritariaProporcional() {
        String s = "";

        s += "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n";
        s += "(com sua posição no ranking de mais votados)\n";

        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));

        ArrayList<Candidato> candidatosTotais = new ArrayList<>();
        candidatosTotais.addAll(this.getCandidatos().values());
        Collections.sort(candidatosTotais);

        int i = 1;

        // imprime nome, partido e quantidade de votos dos candidatos que teriam sido
        // eleitos;
        for (Candidato c : candidatosTotais) {
            String linha = (i++ + " - ");
            if (!c.getFoiEleito()) {
                if (c.getNumeroFederacao() != -1)
                    linha += "*";
                linha += c.getNome() + " (" + c.getPartido().getSigla() + ", " + numberFormat.format(c.getQtdVotos())
                        + " votos)";
                s += linha + "\n";
            }
            if (i == this.getQtdCandidatosEleitos() + 1)
                break;
        }

        s += "\nEleitos, que se beneficiaram do sistema proporcional:\n";
        s += "(com sua posição no ranking de mais votados)\n";

        // imrpime nome, partido e quantidade de votos dos candidatos que se
        // beneficiaram do sistema majoritário;
        for (Candidato c : candidatosTotais.subList(this.getQtdCandidatosEleitos(), candidatosTotais.size())) {
            String linha = (i++ + " - ");
            if (c.getFoiEleito()) {
                if (c.getNumeroFederacao() != -1)
                    linha += "*";
                linha += c.getNome() + " (" + c.getPartido().getSigla() + ", " + numberFormat.format(c.getQtdVotos())
                        + " votos)";
                s += linha + "\n";
            }
            if (i == this.getQtdCandidatosEleitos() + 1)
                break;
        }
        System.out.println(s);
    }

    // gera relatório geral dos partidos;
    public void geraRelatorioPartidos() {
        String s = "";

        s += "Votação dos partidos e número de candidatos eleitos:\n";

        // pega a lista de partidos em um array para ordenar (em ordem decrescente de
        // votos e crescente de número votável;
        ArrayList<Partido> partidosRelatorio = new ArrayList<>();
        partidosRelatorio.addAll(this.partidos.values());
        Collections.sort(partidosRelatorio);

        // define o formato de saída dos números = 0.000;
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));

        // controle de índices;
        int i = 1;
        for (Partido p : partidosRelatorio) {
            s += String.format("%d - %s - %d, ", i, p.getSigla(), p.getNumero());

            if (p.getQtdVotos() > 1) {
                s += String.format("%s votos ", numberFormat.format(p.getQtdVotos()));
            } else {
                s += String.format("%s voto ", numberFormat.format(p.getQtdVotos()));
            }

            if (p.getQtdVotosNominais() > 1) {
                s += String.format("(%s nominais e %s de legenda), ", numberFormat.format(p.getQtdVotosNominais()),
                        numberFormat.format(p.getQtdVotosLegenda()));
            } else {
                s += String.format("(%s nominal e %s de legenda), ", numberFormat.format(p.getQtdVotosNominais()),
                        numberFormat.format(p.getQtdVotosLegenda()));
            }

            if (p.getQtdCandidatosEleitos() > 1) {
                s += String.format("%d candidatos eleitos\n", p.getQtdCandidatosEleitos());
            } else {
                s += String.format("%d candidato eleito\n", p.getQtdCandidatosEleitos());
            }

            i++;
        }

        System.out.println(s);
    }

    // gera relatório do primeiro e último candidato de cada partido;
    public void geraRelatorioPrimeiroUltimoPartidos() {
        String s = "";

        s += "Primeiro e último colocados de cada partido:\n";

        ArrayList<Partido> partidosPrimeiroUltimo = new ArrayList<>();

        // adiciona apenas partidos com candidatos que concorreram às eleições;
        for (Partido p : this.partidos.values()) {
            if (!p.getCandidatos().isEmpty()) {
                partidosPrimeiroUltimo.add(p);
            }
        }

        // ordena em função dos candidatos mais votados de cada partido;
        Collections.sort(partidosPrimeiroUltimo, new PartidoCandidatoComparator());

        // controle de índices
        int i = 1;
        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));
        for (Partido p : partidosPrimeiroUltimo) {
            // pega a lista de candidatos com votos de cada partido e ordena em função da
            // quantidade de votos;
            ArrayList<Candidato> candidatos = new ArrayList<>();
            candidatos.addAll(p.getCandidatos().values());
            Collections.sort(candidatos);

            String votosPrimeiro = numberFormat.format(candidatos.getFirst().getQtdVotos());
            String palavraVotoPrimeiro = candidatos.getFirst().getQtdVotos() > 1 ? "votos" : "voto";
            String primeiro = String.format("%s (%d, %s %s)",
                    candidatos.getFirst().getNome(),
                    candidatos.getFirst().getNumeroVotavel(),
                    votosPrimeiro,
                    palavraVotoPrimeiro);

            String votosUltimo = numberFormat.format(candidatos.getLast().getQtdVotos());
            String palavraVotoUltimo = candidatos.getLast().getQtdVotos() > 1 ? "votos" : "voto";
            String ultimo = String.format("%s (%d, %s %s)",
                    candidatos.getLast().getNome(),
                    candidatos.getLast().getNumeroVotavel(),
                    votosUltimo,
                    palavraVotoUltimo);

            if (candidatos.size() > 1) {
                s += String.format("%d - %s - %d, %s / %s\n", i, p.getSigla(), p.getNumero(), primeiro, ultimo);

                i++;
            }
        }

        System.out.println(s);

    }

    // gera relatório da faixa etária dos candidatos eleitos;
    public void geraRelatorioFaixaEtaria() {
        String s = "";

        s += "Eleitos, por faixa etária (na data da eleição):\n";

        int trinta = 0;
        int trintaQuarenta = 0;
        int quarentaCinquenta = 0;
        int cinquentaSessenta = 0;
        int sessenta = 0;

        // pega todos os candidatos eleitos;
        ArrayList<Candidato> candidatos = new ArrayList<>();

        for (Partido p : this.partidos.values()) {
            candidatos.addAll(p.getCandidatosEleitos());
        }

        // faz o levantamento da quantidade de cada idade;
        for (Candidato c : candidatos) {
            int idade = c.getIdade(this.dataAtual);

            if (idade < 30) {
                trinta++;
            } else if (idade >= 30 && idade < 40) {
                trintaQuarenta++;
            } else if (idade >= 40 && idade < 50) {
                quarentaCinquenta++;
            } else if (idade >= 50 && idade < 60) {
                cinquentaSessenta++;
            } else {
                sessenta++;
            }
        }

        // printa o quadro
        s += String.format(Locale.forLanguageTag("pt-BR"), "      Idade < 30: %d (%.2f%%)\n", trinta, ((double) trinta / candidatos.size()) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "30 <= Idade < 40: %d (%.2f%%)\n", trintaQuarenta,
                ((double) trintaQuarenta / candidatos.size()) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "40 <= Idade < 50: %d (%.2f%%)\n", quarentaCinquenta,
                ((double) quarentaCinquenta / candidatos.size()) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "50 <= Idade < 60: %d (%.2f%%)\n", cinquentaSessenta,
                ((double) cinquentaSessenta / candidatos.size()) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "60 <= Idade     : %d (%.2f%%)\n", sessenta, ((double) sessenta / candidatos.size()) * 100);

        System.out.println(s);

    }

    // gera relatório do gênero dos candidatos eleitos;
    public void geraRelatorioGenero() {
        String s = "";
        
        s += "Eleitos, por gênero:\n";

        int masculino = 0;
        int feminino = 0;

        // pega todos os candidatos eleitos;
        ArrayList<Candidato> candidatos = new ArrayList<>();

        for (Partido p : this.partidos.values()) {
            candidatos.addAll(p.getCandidatosEleitos());
        }

        // faz o levantamento da quantidade de eleitos por gênero
        for (Candidato c : candidatos) {
            if (c.getGenero() == Genero.MASCULINO) {
                masculino++;
            } else if (c.getGenero() == Genero.FEMININO) {
                feminino++;
            }
        }

        // calcula e adiciona ao relatório as porcentagens
        s += String.format(Locale.forLanguageTag("pt-BR"), "Feminino:  %d (%.2f%%)\n", feminino, ((double) feminino / candidatos.size()) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "Masculino: %d (%.2f%%)\n", masculino, ((double) masculino / candidatos.size()) * 100);

        System.out.println(s);

    }

    // gera relatório do levantamento de votos (total, nominal e de legenda).
    public void geraRelatorioVotos() {
        String s = "";

        int votosValidos = 0;
        int votosNominais = 0;
        int votosLegenda = 0;

        for (Partido p : this.partidos.values()) {
            votosLegenda += p.getQtdVotosLegenda();
            votosNominais += p.getQtdVotosNominais();
        }

        votosValidos += votosLegenda + votosNominais;

        NumberFormat numberFormat = NumberFormat.getInstance(java.util.Locale.of("pt", "BR"));

        s += String.format("Total de votos válidos:    %s\n", numberFormat.format(votosValidos));
        s += String.format(Locale.forLanguageTag("pt-BR"), "Total de votos nominais:   %s (%.2f%%)\n", numberFormat.format(votosNominais),
                ((double) votosNominais / votosValidos) * 100);
        s += String.format(Locale.forLanguageTag("pt-BR"), "Total de votos de legenda: %s (%.2f%%)\n", numberFormat.format(votosLegenda),
                ((double) votosLegenda / votosValidos) * 100);

        System.out.println(s);
    }
}
