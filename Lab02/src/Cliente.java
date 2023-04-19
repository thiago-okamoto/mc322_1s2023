public class Cliente {
    private String nome;

    private String cpf;

    private String dataNascimento;

    private int idade;

    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

        // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public String getEndereco() {
        return endereco;
    }
        
        // Setters
    public void setNome( String nome ) {
        this.nome = nome;
    }

    public void setCpf( String cpf ) {
        this.cpf = cpf;
    }

    public void setDataNascimento( String dataNascimento ) {
        this.dataNascimento = dataNascimento;
    }

    public void setIdade( int idade ) {
        this.idade = idade;
    }

    public void setEndereco( String endereco ) {
        this.endereco = endereco;
    }

    //Metodo para transformar Classe em formato de String
    public String toString() {
        return "Cliente: { Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Idade: " + idade + ", Endereço: " + endereco + " }";
    }

    //Método para validação de cpf
    public boolean validarCPF( String cpf ) {
        
        //Declaracao das variaveis para o digito unico do cpf, digito verificador 1, digito verificador 2, somador para o digito 1, somador para o digito 2 e resto das divisioes, respectivamente
        int digito = 0 , digitoV1 = 0, digitoV2 = 0, d1 = 0, d2 = 0, resto1 = 0, resto2 = 0;
        //variavel do digito verificador recebido e variavel do digito verificador calculado
        String digitoVerRecebido = "", digitoVerCalculado = "";

        //Checar se o cpf e valido
        if(cpf == null || cpf.isEmpty()) {
            return false;
        }

        //Remover caracteres especiais
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        //validar se o cpf tem 11 digitos
        if(cpf.length() != 11){
            return false;
        }

        // Multiplicar da direita para esquerda cada digito por uma sequencia crescente a partir de 2
        for(int count = 1; count <= cpf.length()-1; count++ ){
            
            //Pegar o digito 
            digito = Integer.valueOf(cpf.substring(count - 1, count)).intValue();

            //somar digito multiplicado ao numero correspondente da sequencia para o digito verificador 1
            d1 = d1 + (11 - count)*digito;
            
            //somar digito multiplicado ao numero correspondente da sequencia para o digito verificador 2
            d2 = d2 + (12 - count)*digito; 
        }

       //Calcular resto
       resto1 = d1%11;

       //Verificar resto seguindo as regras de calculo do digito verificador 1
       if(resto1 < 2){
            digitoV1 = 0;
       }else{
            digitoV1 = 11 - resto1;
       }

       //Adicionar digito verificado 1 no digito verificado 2 seguindo a regra de calculo
       d2 = digitoV1*2;

       //pegar novo resto para o calculo do digito verificado 2
       resto2 = d2%11;

       //Verificar resto seguindo as regras de calculo do digito verificador 2
       if(resto2 < 2){
        digitoV2 = 0;
       }else{
        digitoV2 = 11 - resto2;
       }

       //separar digito verificador
       digitoVerRecebido = cpf.substring(cpf.length() -2, cpf.length());

       //Concatenando digitos verificadores calculados
       digitoVerCalculado = String.valueOf(digitoV1) + String.valueOf(digitoV2);

       //comparar digitos verificados calculados com recebidos
       if(digitoVerRecebido.equals(digitoVerCalculado)){
        return true;
       }
       else{
        return false;
       }

    }

}
