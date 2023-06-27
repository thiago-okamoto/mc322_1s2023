public enum MenuOperacoes {
    CADASTRAR_CLIENTE(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA(6),
    SAIR(0);

    private final int operacao;

    MenuOperacoes(int operacao){
        this.operacao = operacao;
    }

    public int getOperacao(){
        return this.operacao;
    }
}
