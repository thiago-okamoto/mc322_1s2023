import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Seguradora {
    private String nome;

    private String telefone;

    private String email;

    private String endereco;

    private List<Sinistro> listaSinistros;

    private List<Cliente> listaClientes;

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

        // Getters
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public List<Sinistro>  getListaSinistros() {
        return listaSinistros;
    }

    public List<Cliente>  getListaClientes() {
        return listaClientes;
    }

        // Setters
    public void setNome( String nome ) {
        this.nome = nome;
    }

    public void setTelefone( String telefone ) {
        this.telefone = telefone;
    }
        
    public void setEmail( String email ) {
        this.email = email;
    }

    public void setEndereco( String endereco ) {
        this.endereco = endereco;
    }

    public void setListaSinistros( List<Sinistro> listaSinistros ) {
        this.listaSinistros = listaSinistros;
    }

    public void setListaClientes( List<Cliente> listaClientes ) {
        this.listaClientes = listaClientes;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (!listaClientes.contains(cliente)) {
            listaClientes.add(cliente);
            return true;
        }
        return false;
    }

    public boolean removerCliente(String nomeCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equals(nomeCliente)) {
                listaClientes.remove(cliente);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipoCliente) {
        List<Cliente> listaCliente = null;
        if(tipoCliente == "PF"){
            listaCliente = this.listaClientes.stream().filter(c -> c instanceof ClientePF).collect(Collectors.toList());
        }else if(tipoCliente == "PJ"){
            listaCliente = this.listaClientes.stream().filter(c -> c instanceof ClientePJ).collect(Collectors.toList());
        }else {
            listaCliente = new ArrayList<>();
        }
        for (Cliente cliente : listaCliente) {
            System.out.println(cliente.toString());
        }
    }

    public boolean gerarSinistro() {
        // cria um novo objeto Sinistro
        Sinistro sinistro = new Sinistro(null, null, null, null, null);
        
        try (// pede ao usuário as informações necessárias para preencher os atributos do objeto Sinistro
        Scanner scanner = new Scanner(System.in)) {
            System.out.println("Data do Sinistro:");
            String data = scanner.nextLine();
            sinistro.setData(data);

            System.out.println("Endereço do Sinistro:");
            String endereco = scanner.nextLine();
            sinistro.setEndereco(endereco);

            sinistro.setSeguradora(this);

            scanner.close();
        }
        
        
        // valida se as informaçoes necessarias foram preenchidas para gerar o sinistro
        if(sinistro.getData() == null && sinistro.getEndereco() == null){
            return false;
        }

        for (Cliente cliente : listaClientes) {
            if (cliente != null) {
                sinistro.setCliente(cliente);
                if(cliente.getListaVeiculos() != null){
                    sinistro.setVeiculo(cliente.getListaVeiculos().get(0));
                }
            }
        }


        // adiciona o objeto Sinistro à lista listaSinistros da Seguradora
        listaSinistros.add(sinistro);
        
        return true;
    }
    
    public boolean visualizarSinistro(String cliente) {
        for (Sinistro sinistro : listaSinistros) {
            //Vizualizar Sinistro do cliente especifico
            if (sinistro.getCliente().getNome().equals(cliente)) {
                System.out.println(sinistro.toString());
                return true;
            }
        }
        return false;
    }

    public void listarSinistros() {
        // Percorrer a lista de sinistros da seguradora
        for (Sinistro sinistro : listaSinistros) {
           // Exibir as informações do sinistro na tela
           System.out.println(sinistro.toString());
        }
     }
    
}
