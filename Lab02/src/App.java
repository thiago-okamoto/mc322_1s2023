public class App {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente("Thiago", "123.456.789-10", "01/01/1998", 20, "Rua Teste, 123");
        Seguradora seguradora = new Seguradora("Seguradora 322", "(11) 91234-5678", "lab2@gmail.com", "Rua Teste Seguradora, 123");
        Sinistro sinistro = new Sinistro("28/03/2023", "Rua Acidente, 456");
        Veiculo veiculo = new Veiculo("ABC-1234", "Toyota", "Etios");

        System.out.println(cliente);
        System.out.println(seguradora);
        System.out.println(sinistro);
        System.out.println(veiculo);
    }
}
