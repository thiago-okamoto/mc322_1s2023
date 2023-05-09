import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean isCPF = false;
        boolean isCNPJ = false;
        boolean hasSinistro = false;
        double receita = 0.0;

        String name = lerNome();

        // Novas instancias
        ClientePF cliente1 = new ClientePF("Thiago",  "Rua Teste, 123", new Date(), "Ensino Superior", "Masculino", "Media", null, "494.747.070-49", new Date(), 100);
        ClientePJ cliente2 = new ClientePJ("Tony",  "Rua Teste, 456", null, 500, "27.365.528/0001-10", new Date(), 10);
        ClientePF cliente3 = new ClientePF("Matheus",  "Rua Teste, 789", new Date(), "Ensino Medio", "Masculino", "Alta", null, "651.890.480-12", new Date(), 200);
        Cliente cliente4 = new Cliente("Andre", "Rua Teste, 234", null, 0);

        Seguradora seguradora = new Seguradora("Seguradora 322", "(11) 91234-5678", "lab2@gmail.com", "Rua Teste Seguradora, 123");
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Toyota", "Etios", 2015);
        Veiculo veiculo2 = new Veiculo("DEF-1234", "Nissan", "March", 2018);
        Sinistro sinistro = new Sinistro("28/03/2023", "Rua Acidente, 456", seguradora, veiculo2, cliente2);
        
        // Cadastrar clientes na seguradora
        seguradora.cadastrarCliente(cliente1);
        seguradora.cadastrarCliente(cliente2);
        seguradora.cadastrarCliente(cliente3);


        // Validacoes
        isCPF = Validacao.validarCPF(cliente1.getCpf());
        if(isCPF){
            System.out.print("O CPF é valido!\n");
        }else{
            System.out.print("O CPF é invalido!\n");
        }

        isCNPJ = Validacao.validarCNPJ(cliente2.getCnpj());
        if(isCNPJ){
            System.out.print("O CNPJ é valido!\n");
        }else{
            System.out.print("O CNPJ é invalido!\n");
        }

        // Adicionando veiculos
        cliente1.adicionarVeiculo(veiculo1);
        cliente2.adicionarVeiculo(veiculo2);
        cliente3.adicionarVeiculo(veiculo1);
        cliente4.adicionarVeiculo(veiculo2);

        // Gerando sinistros
        // Esse metodo utiliza a funçao System.In para leitura de dados para gerar o Sinistro
        System.out.println("Gerando primeiro sinistro:\n");
        seguradora.gerarSinistro();
        //System.out.println("Gerando segundo sinistro:\n");
        //seguradora.gerarSinistro();

        seguradora.removerCliente(name);

        System.out.println(cliente1.toString());
        System.out.println(cliente2.toString());
        System.out.println(cliente4.toString());
        System.out.println(seguradora);
        System.out.println(sinistro.toString());
        System.out.println(veiculo1.toString());

        // Chamando metodos da seguradora
        seguradora.listarClientes("PF");
        seguradora.listarClientes("PJ");
        hasSinistro = seguradora.visualizarSinistro(cliente1.getNome());
        if(hasSinistro){
            System.out.println("O cliente " + cliente1.getNome() + " tem o Sinistro!");
        }else {
            System.out.println("O cliente " + cliente1.getNome() + " não tem o Sinistro!");
        }
        seguradora.listarSinistros();
        receita = seguradora.calcularReceita();
        System.out.println("A receita total da seguradora é: " + receita + "\n");
        
        //Atualizar o valor do seguro de cada cliente cadastrado
        seguradora.getListaClientes().forEach(c -> {
            double valorSeguro = seguradora.calcularPrecoSeguroCliente(c);
            c.setValorSeguro(valorSeguro);
        });

        criarMenuOperacoes();

        
    }

    // Metodo para leitura do nome do cliente digitado
    public static String lerNome(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do Cliente: ");
        String name = scanner.nextLine();
        System.out.println("O nome do cliente digitado foi: " + name);
        return name;
    }

    // Metodo para criar o menu de operacoes
    public static void criarMenuOperacoes() throws ParseException{
        Scanner scanner = new Scanner(System.in);
        List<Seguradora> seguradoraList = new ArrayList<>();
        Seguradora seguradora = new Seguradora("Seguradora 0", "(11) 91234-5678", "lab4@gmail.com", "Rua Teste Seguradora, 456");
        Cliente cliente = new Cliente("Thiago", "Rua Teste, 123", null, 0);
        List<Cliente> clienteList = new ArrayList<>();
        List<Veiculo> veiculoList = new ArrayList<>();
        clienteList.add(cliente);
        seguradoraList.add(seguradora);

        System.out.print("Menu de operacoes\n Escolha a ação:\n");
        System.out.print(" 1 - Cadastros\n 2 - Listar\n 3 - Excluir\n 4 - Gerar Sinistro\n 5 - Transferir Seguro\n 6 - Calcular Receita Seguradora\n 0 - Sair\n");
        String primeiraAcaoString = scanner.nextLine();
        int primeiraAcao = Integer.parseInt(primeiraAcaoString);

        if(primeiraAcao == MenuOperacoes.CADASTRAR_CLIENTE.getOperacao()){
            System.out.print("Cadastrar:\n");
            System.out.print(" 1 - Cadastrar Cliente PF/PJ\n 2 - Cadastrar Veiculo\n 3 - Cadastrar Seguradora\n 4 - Voltar\n");
            String cadastrarString = scanner.nextLine();
            int cadastrar = Integer.parseInt(cadastrarString);
            if(cadastrar == 1){
                System.out.print("Cliente PF ou PJ ?:\n");
                String tipoCliente = scanner.nextLine();
                if(tipoCliente == "PF"){
                    System.out.print("Nome do cliente:\n");
                    String nomePF = scanner.nextLine();
                    System.out.print("Endereço:\n");
                    String enderecoPF = scanner.nextLine();
                    System.out.print("Data da licença:\n");
                    String dataLicenca = scanner.nextLine();
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataLicencaDate = formatoData.parse(dataLicenca);
                    System.out.print("Educação:\n");
                    String educacao = scanner.nextLine();
                    System.out.print("Genero:\n");
                    String genero = scanner.nextLine();
                    System.out.print("Classe Economica:\n");
                    String classeEconomica = scanner.nextLine();
                    System.out.print("CPF:\n");
                    String cpf = scanner.nextLine();
                    System.out.print("Data de Nascimento:\n");
                    String dataNascimento = scanner.nextLine();
                    Date data = formatoData.parse(dataNascimento);
                    ClientePF clientePf = new ClientePF(nomePF, enderecoPF, dataLicencaDate, educacao, genero, classeEconomica, null, cpf, data, 0.0);
                    seguradora.cadastrarCliente(clientePf);
                    clienteList.add(clientePf);
                }else if(tipoCliente == "PJ"){
                    System.out.print("Nome do cliente:\n");
                    String nomePF = scanner.nextLine();
                    System.out.print("Endereço:\n");
                    String enderecoPF = scanner.nextLine();
                    System.out.print("CNPJ:\n");
                    String cnpj = scanner.nextLine();
                    System.out.print("Data da fundação:\n");
                    String dataFundacao = scanner.nextLine();
                    System.out.print("Quantidade de Funcionarios:\n");
                    String qtdeFuncionarios = scanner.nextLine();
                    int numFunc = Integer.parseInt(qtdeFuncionarios);
                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = formatoData.parse(dataFundacao);
                    Cliente clientePJ = new ClientePJ(nomePF, enderecoPF, null, 0.0, cnpj, data, numFunc);
                    seguradora.cadastrarCliente(clientePJ);
                    clienteList.add(clientePJ);
                }
            }if(cadastrar == 2){
                System.out.print("Digite a placa:\n");
                String placa = scanner.nextLine();
                System.out.print("Digite a marca:\n");
                String marca = scanner.nextLine();
                System.out.print("Digite o modelo:\n");
                String modelo = scanner.nextLine();
                System.out.print("Digite o ano de fabricaçao:\n");
                String anoFabricacaoString = scanner.nextLine();
                int anoFabricacao = Integer.parseInt(anoFabricacaoString);
                Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
                cliente.adicionarVeiculo(veiculo);
                veiculoList.add(veiculo);
            }if(cadastrar == 3){
                System.out.print("Digite o nome da Seguradora:\n");
                String nome = scanner.nextLine();
                System.out.print("Digite o telefone da Seguradora:\n");
                String telefone = scanner.nextLine();
                System.out.print("Digite o email da Seguradora:\n");
                String email = scanner.nextLine();
                System.out.print("Digite o endereço da Seguradora:\n");
                String endereco = scanner.nextLine();
                Seguradora seguradoraNova = new Seguradora(nome, telefone, email, endereco);
                seguradoraList.add(seguradoraNova);
            }if(cadastrar == 4){
                criarMenuOperacoes();
            }
            System.out.print("Fim do Cadastro!\n");
        }if(primeiraAcao == MenuOperacoes.LISTAR.getOperacao()){
            System.out.print("Listar:\n");
            System.out.print(" 1 - Listar Cliente\n 2 - Listar Sinistros por Seguradora\n 3 - Listar Sinistros por cliente\n 4 - Listar Veiculos por Cliente\n 5 - Listar Veiculos por Seguradora\n 6 - Voltar\n");
            String listarString = scanner.nextLine();
            int listar = Integer.parseInt(listarString);
            if(listar == 1){
                seguradora.listarClientes("PF");
                seguradora.listarClientes("PJ");
            }if(listar == 2){
                seguradora.listarSinistros();
            }if(listar == 3){
                seguradora.getListaSinistros().forEach(s -> {
                    System.out.print("Cliente:\n" + s.getCliente().toString()+ "\n");
                    System.out.print("Sinistro:\n" + s.toString()+ "\n");
                });
            }if(listar == 4){
                seguradora.getListaClientes().forEach(c -> {
                    System.out.print("Cliente:\n" + c.toString()+ "\n");
                    System.out.print("Veiculos:\n" + c.getListaVeiculos()+ "\n");
                });
            }if(listar == 5){
                seguradora.getListaClientes().forEach(c -> {
                    System.out.print("Veiculos:\n" + c.getListaVeiculos()+ "\n");
                });
            }if(listar == 6){
                criarMenuOperacoes();
            }
            System.out.print("Fim do Listar!\n");
        }if(primeiraAcao == MenuOperacoes.EXCLUIR.getOperacao()){
            System.out.print("Excluir:\n");
            System.out.print(" 1 - Excluir Cliente\n 2 - Excluir Veiculo\n 3 - Excluir Sinistro\n 4 - Voltar\n");
            String excluirString = scanner.nextLine();
            int excluir = Integer.parseInt(excluirString);
            if(excluir == 1){
                System.out.print("Qual o nome do cliente para excluir?\n");
                String nomeCliente = scanner.nextLine();
                seguradora.removerCliente(nomeCliente);
            }if(excluir == 2){
                System.out.print("Qual a placa do Veiculo para excluir?\n");
                String placa = scanner.nextLine();
                cliente.getListaVeiculos().forEach( v -> {
                    if(v.getPlaca().equals(placa)){
                        cliente.removerVeiculo(v);
                    }
                });
            }if(excluir == 3){
                System.out.print("Qual a data do Sinistro para excluir?\n");
                String dataSinistro = scanner.nextLine();
                seguradora.getListaSinistros().forEach(s -> {
                    if(dataSinistro.equals(s.getData())){
                        seguradora.getListaSinistros().remove(s);
                    }
                });
            }if(excluir == 4){
                criarMenuOperacoes();
            }
            System.out.print("Fim do Excluir!\n");
        }if(primeiraAcao == MenuOperacoes.GERAR_SINISTRO.getOperacao()){
            seguradora.gerarSinistro();
            System.out.print("Sinistro Gerado!\n");
        }if(primeiraAcao == MenuOperacoes.TRANSFERIR_SEGURO.getOperacao()){
            System.out.print("Qual o nome do antigo cliente do Sinistro?\n");
            String nomeClienteAntigo = scanner.nextLine();
            System.out.print("Qual o nome do novo cliente do Sinistro?\n");
            String nomeClienteNovo = scanner.nextLine();
            
            Cliente cliente1 = seguradora.getListaClientes().get(0);
            Cliente cliente2 = seguradora.getListaClientes().get(1);
            seguradora.transferirSeguro(cliente1, cliente2);
            System.out.print("Seguro transferido!\n");
        }if(primeiraAcao == MenuOperacoes.CALCULAR_RECEITA.getOperacao()){
            seguradora.calcularReceita();
        }if(primeiraAcao == MenuOperacoes.SAIR.getOperacao()){
            scanner.close();
            return;
        }
        scanner.close();
    }

}
