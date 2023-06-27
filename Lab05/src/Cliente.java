import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;

    private String endereco;
    
    private List<Veiculo> listaVeiculos;

    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos, double valorSeguro) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.valorSeguro = valorSeguro;
    }

        // Getters
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
        
        // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    //Metodo para transformar Classe em formato de String
    @Override
    public String toString() {
        return "Cliente: { \nNome: " + nome + "\nEndereco: " + endereco + "\nLista de Veiculos: " + listaVeiculos + "\nValor do Seguro: " + valorSeguro + "\n }";
    }

    public void adicionarVeiculo(Veiculo veiculo){
        this.listaVeiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        this.listaVeiculos.remove(veiculo);
    }

    public double calculaScore(){
        CalcSeguro base = CalcSeguro.VALOR_BASE;
        double valorBase = base.getValor();

        return valorBase;
    }

}
