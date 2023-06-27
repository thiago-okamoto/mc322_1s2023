import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CartaoCredito extends Cartao {
	
	private float credito = 0.0f;
	private float limite;
	private float valorFatura;

	public CartaoCredito(String numero, String validade, int codigo, int senha, float limite, Conta titular) {
		super(numero, validade, codigo, senha, titular);
		this.limite = limite;
	}
	
    // Implementacao do metodo abstrato para esse tipo de cartao
	@Override
	public void comprarComCartao(String produto, float valor) throws Exception, IOException{
		if (this.credito < limite) {
			this.credito += valor;
			this.limite -= valor;
			this.getProdutos().add(produto);
			this.getValores().add(valor);
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Compra realizada com cartao");
			bw.newLine();
			bw.write("Compra realizada com sucesso!");
			bw.newLine();
			bw.write("Produto: " + produto);
			bw.newLine();
			bw.write("Valor: R$ " + valor);
			bw.newLine();
			bw.write("------------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
			System.out.println();
		} else {
			throw new Excecao("Limite do cartao ultrapassado!");
		}
	}
	
	public void exibirFatura() throws IOException {
		if (this.getProdutos().size() > -1) {
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Fatura do cartao de credito");
			bw.newLine();
			for (int i = 0; i < this.getProdutos().size(); i++) {
				this.valorFatura += this.getValores().get(i);
				bw.write("Produto: " + this.getProdutos().get(i));
				bw.newLine();
				bw.write("Valor: R$ " + this.getValores().get(i));
				bw.newLine();
			}
			bw.write("Valor total: R$ " + this.valorFatura);
			bw.newLine();
			bw.write("------------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new ArrayIndexOutOfBoundsException("Nao foi comprado nada no cartao de credito!");
		}
	}
	
	public void pagarFatura() throws IOException, Excecao {
		if (this.getTitular().getSaldo() >= this.valorFatura) {
			super.getTitular().setSaldo(super.getTitular().getSaldo() - this.valorFatura);
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Fatura paga");
			bw.newLine();
			bw.write("Fatura paga com sucesso!");
			bw.newLine();
			bw.write("Valor total da fatura: R$ " + this.valorFatura);
			bw.newLine();
			bw.write("---------------------------------");
			this.valorFatura = 0.0f;
			this.getProdutos().clear();
			this.getValores().clear();
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new Excecao("Saldo insuficiente para pagar a fatura!");
		}
	}
	
	public float getCredito() {
		return credito;
	}
	
	public float getLimite() {
		return limite;
	}
	
	public float getValorFatura() {
		return valorFatura;
	}
}
