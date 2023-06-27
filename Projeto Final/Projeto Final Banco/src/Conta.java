import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public abstract class Conta {

	private Cliente titular;
	protected float saldo;
	private String agencia;
	private String numero;
	private boolean estaAtiva;
	
	public Conta(Cliente titular, String agencia, String numero, float saldo) throws Exception {
		if (agencia.length() == 2 && numero.length() == 5 && saldo >= 0) {
			this.titular = titular;
			this.agencia = agencia;
			this.numero = numero;
			this.saldo = saldo;
			this.estaAtiva = true;
		} else {
			throw new Excecao("Agencia, numero ou saldo informados sao invalidos!\nAgencia tem que ter apenas dois digitos, numero apenas cinco digitos e saldo tem que ser maior ou igual a zero.");
		}
	}

	public void depositar(float valor) throws IOException, Excecao {
		if (this.estaAtiva == true) {
			if (valor > 0) {
				this.saldo += valor;
				OutputStream fos = new FileOutputStream("banco.txt");
				Writer wt = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(wt);
				bw.write("Deposito");
				bw.newLine();
				bw.write("Deposito concluido com sucesso!");
				bw.newLine();
				bw.write("Valor: R$ " + valor);
				bw.newLine();
				bw.write("-----------------------------");
				bw.close();
				LerArquivo.lerArquivo();
                System.out.println();
			} else {
				throw new IllegalArgumentException("Valor invalido para deposito!");
			}
		} else {
			throw new Excecao("A conta esta inativa, preencha as informacoes corretamente para ativar a conta!");
		}
		
	}
	
    // Metodo abstrato para exibir a conta
	public abstract void exibirConta() throws IOException, Excecao;
	
	public void desativar() throws IOException, Excecao {
		if (this.isEstaAtiva() == true) {
			this.estaAtiva = false;
			OutputStream fos = new FileOutputStream("banco.txt");
			Writer wt = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(wt);
			bw.write("Desativar conta");
			bw.newLine();
			bw.write("Conta desativa com sucesso!");
			bw.newLine();
			bw.write("Nome do titular: " + this.getTitular().getPessoa().getNome() + " " + this.getTitular().getPessoa().getSobrenome());
			bw.newLine();
			bw.write("Agencia: " + this.getAgencia());
			bw.newLine();
			bw.write("Numero: " + this.numero);
			bw.newLine();
			bw.write("------------------------------------------");
			bw.close();
			LerArquivo.lerArquivo();
            System.out.println();
		} else {
			throw new Excecao("A conta ja esta desativada!");
		}
	}
	
	public Cliente getTitular() {
		return titular;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public String getAgencia() {
		return agencia;
	}
	
	public String getNumero() {
		return numero;
	}

	public boolean isEstaAtiva() {
		return estaAtiva;
	}
	
	@Override
	public boolean equals(Object obj) {
		Conta outraConta = (Conta) obj;
		if (this.numero == outraConta.numero && this.agencia == outraConta.agencia) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Informacoes da Conta\nAgencia: " + this.agencia + "\nNumero: " + this.numero + "\nSaldo: " + this.saldo + "\nTitular: " + this.titular.getPessoa().getNome() + " " + this.titular.getPessoa().getSobrenome();
	}
}
