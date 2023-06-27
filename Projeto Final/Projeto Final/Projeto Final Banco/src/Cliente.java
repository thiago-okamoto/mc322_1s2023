public abstract class Cliente {

	private Pessoa pessoa;
	private Endereco endereco;
	
	public Cliente(Pessoa pessoa, Endereco endereco) {
		this.pessoa = pessoa;
		this.endereco = endereco;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
}
