import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {

    private final String cpf;

    private String genero;

    private Date dataLicenca;

    private String educacao;
    
    private Date dataNascimento;

    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento, double valorSeguro){
        super(nome, endereco, listaVeiculos, valorSeguro);
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

    public int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    //Metodo para transformar Classe em formato de String
    @Override
    public String toString() {
        return "ClientePF: { \nNome: " + getNome() + "\nEndereco: " + getEndereco() + "\nLista de Veiculos: " + getListaVeiculos() + "\nValor do Seguro: " + getValorSeguro() + "\nCPF: " + cpf + "\nGenero: " + genero + "\nData da Licença: " + dataLicenca + "\nEducação: " + educacao + "\nData de Nascimento: " + dataNascimento + "\nClasse Economica: " + classeEconomica + "\n }";
    }

    public double calculaScore(){
        List<Veiculo> veiculos = getListaVeiculos();
        double tam = veiculos.size();
        CalcSeguro base = CalcSeguro.VALOR_BASE;
        double valorBase = base.getValor();

        LocalDate localDateNascimento = this.dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();       
        if(18 <= this.calcularIdade(localDateNascimento) && this.calcularIdade(localDateNascimento) < 30 ){
            CalcSeguro fator1 = CalcSeguro.FATOR_18_30;
            double valorFator1 = fator1.getValor();
            return  valorBase * valorFator1 * tam;
        }else if(30 <= this.calcularIdade(localDateNascimento) && this.calcularIdade(localDateNascimento) < 60 ){
            CalcSeguro fator2 = CalcSeguro.FATOR_30_60;
            double valorFator2 = fator2.getValor();
            return  valorBase * valorFator2 * tam;
        }if(60 <= this.calcularIdade(localDateNascimento) && this.calcularIdade(localDateNascimento) < 90 ){
            CalcSeguro fator3 = CalcSeguro.FATOR_60_90;
            double valorFator3 = fator3.getValor();
            return  valorBase * valorFator3 * tam;
        }else {
            return valorBase;
        }


    }
}
