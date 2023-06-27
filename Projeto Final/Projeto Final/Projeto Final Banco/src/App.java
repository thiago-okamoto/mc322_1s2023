// Projeto Final de Banco
// Thiago Yuji Okamoto              187641
// Vitor Henrique Miranda Ribeiro   206988

public class App {
    public static void main(String[] args) throws Exception {
        
        // Instancias
		Pessoa ip1 = new Pessoa("Thiago", "Okamoto", "26/08/1998", "111.222.333-44", "11.222.333-4", "brasileiro");
		Endereco ed1 = new Endereco("01001-222", "Sao Paulo", "Sao Paulo", "Vila Mariana", "Rua Vergueiro", 1111, "Proximo ao metro", "apto 11");
		Cliente cf1 = new ClientePF(ip1, ed1, "Media");
		ContaCorrente cc1 = new ContaCorrente(cf1, "11", "12345", 100.00f);
		ContaPoupanca cp1 = new ContaPoupanca(cf1, "22", "54321", 100.00f);
		
		Pessoa ip2 = new Pessoa("Vitor", "Ribeiro", "23/02/1992", "222.333.444-55", "22.333.444-5", "brasileiro");
		Endereco ed2 = new Endereco("22312-777", "Rio de Janeiro", "Sao Paulo", "Paraiso", "Avenida Paulista", 222, "Proximo ao MASP", "apto 22");
		Cliente cf2 = new ClientePF(ip2, ed2, "Media");
		ContaCorrente cc2 = new ContaCorrente(cf2,"33", "67890", 200.00f);
        ContaPoupanca cp2 = new ContaPoupanca(cf1, "44", "09876", 5000.00f);
		
        // Teste da primeira conta corrente (Thiago)
		cc1.exibirConta();
		cc1.depositar(100.00f);
		cc1.transfere(cc2, 50.00f);
		cc1.exibirConta();

        // Teste da primeira poupanca (Thiago)
		cp1.investir(100.00f, 2);
		
        // Teste da segunda conta corrente (Vitor)
		cc2.exibirConta();
		cc2.depositar(200.00f);
		cc2.transfere(cc1, 100.00f);
		cc2.exibirConta();

        // Teste da segunda poupanca (Vitor)
		cp2.investir(150.00f, 2);
        
        // Teste do banco
		Banco b = new Banco();
		b.adicionaConta(cc1);
		b.adicionaConta(cc2);
		b.exibeContas();

        // Teste cartao credito para a primeira conta
		CartaoCredito cdc = new CartaoCredito("1111 2222 3333 4444", "26/06/2029", 123, 1234, 1000.00f, cc1);
		cdc.comprarComCartao("Livro", 50.00f);
		cdc.comprarComCartao("cadeira", 300.00f);
		cdc.exibirFatura();
		cdc.pagarFatura();

        // Teste cartao debito para a segunda conta
		CartaoDebito cdd = new CartaoDebito("5555 6666 7777 8888", "26/06/2030", 456, 5678, cc1);
		cdd.comprarComCartao("Refeicao", 40.0f);
		cdd.comprarComCartao("Mercado", 250.0f);
		cdd.exibirExtrato();
		cdd.exibirDebito();

		// Teste de interface
	}
}
