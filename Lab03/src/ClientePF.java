import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {

    private final String cpf;

    private String genero;

    private Date dataLicenca;

    private String educacao;
    
    private Date dataNascimento;

    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento){
        super(nome, endereco, listaVeiculos);
        this.dataLicenca=dataLicenca;
        this.educacao=educacao;
        this.genero = genero;
        this.classeEconomica=classeEconomica;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

      // Getters
    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }
        
        // Setters
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    //Metodo para transformar Classe em formato de String
    @Override
    public String toString() {
        return "ClientePF: { \nNome: " + getNome() + "\nEndereco: " + getEndereco() + "\nLista de Veiculos: " + getListaVeiculos() + "\nCPF: " + cpf + "\nGenero: " + genero + "\nData da Licença: " + dataLicenca + "\nEducação: " + educacao + "\nData de Nascimento: " + dataNascimento + "\nClasse Economica: " + classeEconomica + "\n }";
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
