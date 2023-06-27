public class ClientePJ extends Cliente{
    private String cnpj;
	private String empresa;
	private String ramo;

	public ClientePJ(Pessoa pessoa, Endereco endereco, String cnpj, String empresa, String ramo) {
		super(pessoa, endereco);
		this.cnpj = cnpj;
		this.empresa = empresa;
		this.ramo = ramo;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public String getRamo() {
		return ramo;
	}

    //Método para validação de cnpj (Feito utilizando pesquisa na internet pois a logica que eu criei nao estava funcionando)
    public static boolean validarCNPJ( String cnpj ) {

        //Checar se o cnpj e valido
        if(cnpj == null || cnpj.isEmpty() ) {
            return false;
        }

        //Remover caracteres especiais
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        //validar se o cnpj tem 14 digitos
        if(cnpj.length() != 14){
            return false;
        }

        char dig13, dig14;
		int sm, i, r, num, peso;
		
        // Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
            // converte o i-ésimo caractere do CNPJ em um número:
            // por exemplo, transforma o caractere '0' no inteiro 0
            // (48 eh a posição de '0' na tabela ASCII)
            num = (int) (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig13 = '0';
        else
            dig13 = (char) ((11 - r) + 48);

        // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
            num = (int) (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso + 1;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            dig14 = '0';
        else
            dig14 = (char) ((11 - r) + 48);

        // Verifica se os dígitos calculados conferem com os dígitos informados.
        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
            return (true);
        else
            return (false);

    }

    public static boolean validarNome(String nome) {
        // Verifica se o nome é nulo ou vazio
        if (nome == null || nome.isEmpty()) {
            return false;
        }
    
        // Verifica se o nome tem apenas letras e espaços
        for (int i = 0; i < nome.length(); i++) {
            char c = nome.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                //Se o nome nao tiver so letras e espacos, nome invalido
                return false;
            }
        }
    
        // Nome valido
        return true;
    }
}
