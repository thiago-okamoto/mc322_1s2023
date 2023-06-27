import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ContaCorrente extends Conta {
	
	public ContaCorrente(Cliente titular, String agencia, String numero, float saldo) throws Exception {
		super(titular, agencia, numero, saldo);
	}

	public void transfere(ContaCorrente contaRecebedora, float valorTransferencia) throws IOException, Exception {
		if (super.getSaldo() >= valorTransferencia) {
			super.saldo -= valorTransferencia;
			contaRecebedora.saldo += valorTransferencia;
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			
			bw.write("Transferencia bancaria");
			bw.newLine();
			bw.write("Valor transferido com sucesso!");
			bw.newLine();
			bw.write("Titular da transferencia: " + this.getTitular().getPessoa().getNome() + " " + this.getTitular().getPessoa().getSobrenome());
			bw.newLine();
			bw.write("Valor transferido: R$ " + valorTransferencia);
			bw.newLine();
			bw.write("Recebedor: " + contaRecebedora.getTitular().getPessoa().getNome() + " " + contaRecebedora.getTitular().getPessoa().getSobrenome());
			bw.newLine();
			bw.write("CPF do Recebedor: " + contaRecebedora.getTitular().getPessoa().getCpf());
			bw.newLine();
			bw.write("--------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new Excecao("Saldo insuficiente para fazer a transferencia!");
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
			bw.write("Tipo de conta: Corrente");
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
		return super.toString() + "\nTipo de Conta: Corrente";
	}
}
