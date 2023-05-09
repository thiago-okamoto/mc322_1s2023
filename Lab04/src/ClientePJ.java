import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente{

    private final String cnpj;

    private Date dataFundacao;

    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, List<Veiculo> listaVeiculos, double valorSeguro, String cnpj, Date dataFundacao, int qtdeFuncionarios){
        super(nome, endereco, listaVeiculos, valorSeguro);
        this.cnpj=cnpj;
        this.dataFundacao=dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

        // Getters
    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

        // Setters
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    //Metodo para transformar Classe em formato de String
    @Override
    public String toString() {
        return "ClientePJ: { \nNome: " + getNome() + "\nEndereco: " + getEndereco() + "\nLista de Veiculos: " + getListaVeiculos() + "\nValor do Seguro: " + getValorSeguro() + "\nCNPJ: " + cnpj + "\nData da Fundacao: " + dataFundacao + "\n }";
    }

    public double calculaScore(){
        List<Veiculo> veiculos = getListaVeiculos();
        double tam = veiculos.size();
        CalcSeguro base = CalcSeguro.VALOR_BASE;
        double valorBase = base.getValor();

        return valorBase * (1 + (this.qtdeFuncionarios)/100) * tam ;
    }
    
}
