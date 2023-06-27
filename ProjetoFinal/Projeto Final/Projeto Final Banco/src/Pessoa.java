public class Pessoa {
    private String nome;
	private String sobrenome;
	private String dataNascimento;
	private String cpf;
	private String rg;
	private String nacionalidade;
	
	public Pessoa(String nome, String sobrenome, String dataNascimento, String cpf, String rg, String nacionalidade) {
		if (nome.length() > 0 && sobrenome.length() > 0 && dataNascimento.length() > 0 && cpf.length() > 0 && rg.length() > 0 && nacionalidade.length() > 0) {
			this.nome = nome;
			this.sobrenome = sobrenome;
			this.dataNascimento = dataNascimento;
			this.cpf = cpf;
			this.rg = rg;
			this.nacionalidade = nacionalidade;
		} else {
			throw new NullPointerException("Insira todas as informacoes pessoais!");
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
}
