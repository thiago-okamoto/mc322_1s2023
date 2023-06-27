import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ContaPoupanca extends Conta {

	private double taxa = 0.04;
	
	public ContaPoupanca(Cliente titular, String agencia, String numero, float saldo) throws Exception {
		super(titular, agencia, numero, saldo);
	}
	
	public void investir(float valor, int meses) throws Exception {
		if (super.saldo >= valor) {
			if (meses > 0) {
				float valorRetornado = (float) (valor * meses * this.taxa);
				super.saldo += valorRetornado;
				OutputStream fos = new FileOutputStream("banco.txt");
				Writer osw = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write("Investimento");
				bw.newLine();
				bw.write("Valor investido: R$ " + valor);
				bw.newLine();
				bw.write("Meses investidos: " + meses);
				bw.newLine();
				bw.write("Valor retornado: R$ " + valorRetornado);
				bw.newLine();
				bw.write("----------------------------------");
				bw.close();
				LerArquivo.lerArquivo();
                System.out.println();
			} else {
				throw new IllegalArgumentException("Digite um mes existente, diferente de zero");
			}
		} else {
			throw new Excecao("Saldo insuficiente para fazer investimento!");
		}
	}
	
    // Implementacao do metodo abstrato para esse tipo de conta
	@Override
	public void exibirConta() throws IOException, Excecao {
		if (this.isEstaAtiva() == true) {
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Informacoes da conta");
			bw.newLine();
			bw.write("Nome do titular: " + this.getTitular().getPessoa().getNome() + " " + this.getTitular().getPessoa().getSobrenome());
			bw.newLine();
			bw.write("CPF do titular: " + this.getTitular().getPessoa().getCpf());
			bw.newLine();
			bw.write("Saldo: " + this.getSaldo());
			bw.newLine();
			bw.write("Tipo de conta: Poupanca");
			bw.newLine();
			bw.write("-------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new Excecao("A conta esta inativa! Ative para poder visualizar informacoes sobre ela.");
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nTipo de Conta: Poupanca";
	}

}
