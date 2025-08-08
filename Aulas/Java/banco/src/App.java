public class App {
    public static void main(String[] args) throws Exception {
        Pessoa fulano = new Pessoa("12378123", "Fulano da Silva");

        Agencia a = new Agencia("Goiabeiras");

        Conta c1 = new Conta("123456-0",fulano);
        Conta c2 = new Conta("567567-8",fulano);

        a.adicionaConta(c1);
        a.adicionaConta(c2);

        String nome = a.getContas().get(0).getTitular().getNome();

        for (Conta c : a.getContas())
        {
            System.out.println(c.getIdentificador());
            System.out.println(c.getTitular().getCpf());
            System.out.println(c.getTitular().getNome());
        }

        // imprime os números das contas de fulano
        System.out.println("Contas de fulano:");
        for (Conta c : a.getContasTitular(fulano))
        {
            System.out.println(c.getIdentificador());
        }

    }
}
