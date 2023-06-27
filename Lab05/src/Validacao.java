public class Validacao {
    private Validacao() {} // Construtor privado para impedir instância da classe

    //Método para validação de cpf
    public static boolean validarCPF( String cpf ) {
        
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
