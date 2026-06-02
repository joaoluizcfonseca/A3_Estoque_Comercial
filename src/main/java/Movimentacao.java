package A3_Estoque_Comercial;

import javax.swing.JOptionPane;

public class Movimentacao {

    private String[] nomes;
    private double[] precos;
    private String[] unidades;
    private int[] quantidades;
    private int totalProdutos;

    public Movimentacao(String[] nomes, double[] precos, String[] unidades,
                        int[] quantidades, int totalProdutos) {
        this.nomes         = nomes;
        this.precos        = precos;
        this.unidades      = unidades;
        this.quantidades   = quantidades;
        this.totalProdutos = totalProdutos;
    }

    public void setTotalProdutos(int total) {
        this.totalProdutos = total;
    }

    public void menuMovimentacao() {
        boolean continuar = true;

        while (continuar) {
            int escolha = exibirOpcoes(
                "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                + "________________________________\n\n"
                + "           MOVIMENTAÇÃO\n\n"
                + "Escolha uma opção:",
                "Movimentação",
                new String[]{"Entrada", "Saída", "Retornar"}
            );

            switch (escolha) {
                case 0:
                    menuEntrada();
                    break;
