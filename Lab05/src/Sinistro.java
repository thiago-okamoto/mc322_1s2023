public class Sinistro {
    // contador de instancias da classe
    private static int counter = 0;

    private final int sinistroId;

    private String data;

    private String endereco;

    private Seguradora seguradora;

    private Veiculo veiculo;

    private Cliente cliente;

    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.sinistroId = counter++;
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // Getters
    public int getId() {
        return sinistroId;
    }

    public String getData() {
        return data;
    }

    public String getEndereco() {
        return endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }
        
        // Setters
    public void setData( String data ) {
        this.data = data;
    }
        
    public void setEndereco( String endereco ) {
        this.endereco = endereco;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //Metodo para transformar Classe em formato de String
    @Override
    public String toString() {
        return "Sinistro: { \nSinistro Id: " + sinistroId + ",\nData: " + data + ",\nEndereco: " + endereco
                + ",\nSeguradora: " + seguradora.getNome() + ",\nVeiculo: " + veiculo.getModelo()
                + " - " + veiculo.getPlaca() + ",\nCliente: " + cliente.getNome() + "\n }";
    }

}
