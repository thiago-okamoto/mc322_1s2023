import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Banco {
    private String nome;
	private Endereco endereco;
	private ArrayList<Conta> contas = new ArrayList<Conta>();
	
	public void adicionaConta(Conta conta) throws Excecao {
		if (!this.contas.contains(conta)) {
			this.contas.add(conta);
		} else {
			throw new Excecao("Contas iguais!\nNao foi possivel adicionar mais de uma conta com o mesmo numero de agencia e de conta.");
		}
	}
	
	public void exibeContas() throws IOException {
		
		if (this.getContas().size() > -1) {
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Contas do banco");
			bw.newLine();
			for (int i = 0; i < this.getContas().size(); i++) {
				bw.write(this.contas.toString());
				bw.newLine();
			}
			bw.write("-------------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public ArrayList<Conta> getContas() {
		return contas;
	}
}
