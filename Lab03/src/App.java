import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean isCPF = false;
        boolean isCNPJ = false;
        boolean hasSinistro = false;

        String name = lerNome();

        ClientePF cliente1 = new ClientePF("Thiago",  "Rua Teste, 123", new Date(), "Ensino Superior", "Masculino", "Media", null, "494.747.070-49", new Date());
        ClientePJ cliente2 = new ClientePJ("Tony",  "Rua Teste, 456", null, "27.365.528/0001-10", new Date());
        ClientePF cliente3 = new ClientePF("Matheus",  "Rua Teste, 789", new Date(), "Ensino Medio", "Masculino", "Alta", null, "651.890.480-12", new Date());
        Cliente cliente4 = new Cliente("Andre", "Rua Teste, 234", null);

        Seguradora seguradora = new Seguradora("Seguradora 322", "(11) 91234-5678", "lab2@gmail.com", "Rua Teste Seguradora, 123");
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Toyota", "Etios", 2015);
        Veiculo veiculo2 = new Veiculo("DEF-1234", "Nissan", "March", 2018);
        Sinistro sinistro = new Sinistro("28/03/2023", "Rua Acidente, 456", seguradora, veiculo2, cliente2);
        

        seguradora.cadastrarCliente(cliente1);
        seguradora.cadastrarCliente(cliente2);
        seguradora.cadastrarCliente(cliente3);

        isCPF = cliente1.validarCPF(cliente1.getCpf());
        if(isCPF){
            System.out.print("O CPF é valido!\n");
        }else{
            System.out.print("O CPF é invalido!\n");
        }

        isCNPJ = cliente2.validarCNPJ(cliente2.getCnpj());
        if(isCNPJ){
            System.out.print("O CNPJ é valido!\n");
        }else{
            System.out.print("O CNPJ é invalido!\n");
        }

        cliente1.adicionarVeiculo(veiculo1);
        cliente2.adicionarVeiculo(veiculo2);
        cliente3.adicionarVeiculo(veiculo1);
        cliente4.adicionarVeiculo(veiculo2);

        // Esse metodo utiliza a funçao System.In para leitura de dados para gerar o Sinistro
        seguradora.gerarSinistro();

        seguradora.removerCliente(name);

        System.out.println(cliente1.toString());
        System.out.println(cliente2.toString());
        System.out.println(cliente4.toString());
        System.out.println(seguradora.toString());
        System.out.println(sinistro.toString());
        System.out.println(veiculo1.toString());

        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        hasSinistro = seguradora.visualizarSinistro(cliente1.getNome());
        if(hasSinistro){
            System.out.println("O cliente " + cliente1.getNome() + " tem o Sinistro!");
        }else {
            System.out.println("O cliente " + cliente1.getNome() + " não tem o Sinistro!");
        }
        
        seguradora.listarSinistros();

    }

    // Metodo para leitura do nome do cliente digitado
    public static String lerNome(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do Cliente: ");
        String name = scanner.nextLine();
        System.out.println("O nome do cliente digitado foi: " + name);

        return name;
    }
}
