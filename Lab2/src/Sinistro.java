public class Sinistro {
    private static int counter = 0;

    private int sinistroId;

    private String data;

    private String endereco;

    // Construtor
    public Sinistro(String data, String endereco) {
        this.sinistroId = counter++;
        this.data = data;
        this.endereco = endereco;
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
        
        // Setters
    public void setData( String data ) {
        this.data = data;
    }
        
    public void setEndereco( String endereco ) {
        this.endereco = endereco;
    }
}
