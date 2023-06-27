import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CartaoDebito extends Cartao {
	
	private float valorExtrato;

	public CartaoDebito(String numero, String validade, int codigo, int senha, Conta titular) {
		super(numero, validade, codigo, senha, titular);
	}

     // Implementacao do metodo abstrato para esse tipo de cartao
	@Override
	public void comprarComCartao(String produto, float valor) throws Exception, IOException {
		if (super.getTitular().getSaldo() > valor) {
			super.getTitular().setSaldo(super.getTitular().getSaldo() - valor);
			super.getProdutos().add(produto);
			super.getValores().add(valor);
			this.valorExtrato += valor;
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Compra com cartao de debito");
			bw.newLine();
			bw.write("Compra realizada com sucesso!");
			bw.newLine();
			bw.write("Produto: " + produto);
			bw.newLine();
			bw.write("Valor: R$ " + valor);
			bw.newLine();
			bw.write("-------------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new Excecao("Saldo insuficiente para comprar com cartao de debito!");
		}
	}
	
	public void exibirExtrato() throws IOException {
		if (super.getProdutos().size() > -1) {
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Extrato");
			bw.newLine();
			bw.write("Extrato do cartao de debito");
			bw.newLine();
			for (int i = 0; i < super.getProdutos().size(); i++) {
				bw.write("Produto: " + super.getProdutos().get(i));
				bw.newLine();
				bw.write("Valor: R$ " + super.getValores().get(i));
				bw.newLine();
			}
			bw.write("Valor total do extrato: " + this.valorExtrato);
			bw.newLine();
			bw.write("-------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new ArrayIndexOutOfBoundsException("Nao houve compras no cartao de debito!");
		}
	}
	
	public void exibirDebito() throws Exception {
		OutputStream fos = new FileOutputStream("banco.txt");
		Writer wt = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(wt);
		bw.write("Informacoes cartao de debito");
		bw.newLine();
		bw.write("Nome do titular: " + this.getTitular().getTitular().getPessoa().getNome() + " " + this.getTitular().getTitular().getPessoa().getSobrenome());
		bw.newLine();
		bw.write("Agencia: " + this.getTitular().getAgencia());
		bw.newLine();
		bw.write("Numero: " + this.getNumero());
		bw.newLine();
		bw.write("Saldo: R$ " + super.getTitular().getSaldo());
		bw.newLine();
		bw.write("--------------------------------------------------");
		bw.close();
		LerArquivo.lerArquivo();
        System.out.println();
	}

}
