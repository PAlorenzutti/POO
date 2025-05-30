import banco.Banco;
import banco.Agencia;
import banco.Conta;

// não altere este arquivo na primeira etapa

public class App {

	public static void main(String[] args) {

		Banco banco = new Banco("BB");
		Agencia agencia = banco.criaAgencia("Jardim da Penha");

		String comando = "";
		while (!comando.equals("9")) {
			System.out.println("Entre com a opção desejada: ");
			System.out.println("1 - Cadastrar conta");
			System.out.println("2 - Consultar conta");
			System.out.println("3 - Excluir conta");
			System.out.println("4 - Printar todas as contas da agência");
			System.out.println("9 - Sair");
			comando = System.console().readLine();
			switch (comando) {
				case "1":
					cadastraConta(banco, agencia);
					break;
				case "2":
					consultaConta(banco, agencia);
					break;
				case "3":
					excluiConta(banco, agencia);
					break;
				case "4":
					printaContas(agencia);
					break;
					
			}
		}
	}

	private static void cadastraConta(Banco banco, Agencia agencia) {
		System.out.println("Entre com o nome do cliente: ");
		String nomeCliente = System.console().readLine();
		System.out.println("Entre com o saldo inicial: ");
		String saldoInicialStr = System.console().readLine();
		float saldoInicial = Float.parseFloat(saldoInicialStr);
		Conta c = agencia.adicionaConta(nomeCliente, saldoInicial);
		System.out.printf("Conta criada para %s com saldo inicial R$ %.2f\n", c.getNome(), c.getSaldo());
	}

	private static void consultaConta(Banco banco, Agencia agencia) {
		System.out.println("Entre com o nome do cliente: ");
		String nomeCliente = System.console().readLine();

		if(agencia.getContas().containsKey(nomeCliente)){
			Conta c = agencia.obtemConta(nomeCliente);
			System.out.printf("O saldo é: R$ %.2f\n", c.getSaldo());
		}else{
			System.out.println("Conta não existe");
		}
	}

	private static void excluiConta(Banco banco, Agencia agencia) {
		System.out.println("Entre com o nome do cliente: ");
		String nomeCliente = System.console().readLine();
		
		if(agencia.getContas().containsKey(nomeCliente)){
			agencia.removeConta(nomeCliente);
			System.out.println("Conta removida.");
		}else{
			System.out.println("Conta não existe");
		}
	}

	private static void printaContas(Agencia agencia){
		for(Conta c : agencia.getContas().values()){
			System.out.println("Cliente: " + c.getNome());
			System.out.printf("O saldo é: R$ %.2f\n", c.getSaldo());
		}	
	}

}
