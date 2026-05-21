package com.mycompany.a3_reajuste_preco;
import javax.swing.JOptionPane;

public class A3_Reajuste_Preco {

    static final int MAX = 100;
    static String[] nomes = new String[MAX];
    static String[] unidades = new String[MAX];
    static double[] precos = new double[MAX];
    static int[] quantidades = new int[MAX];
    static int total = 0;

    public static void main(String[] args) {
        nomes[0] = "Arroz";
        unidades[0] = "KG";
        precos[0] = 10.50;
        quantidades[0] = 50;
        total = 1;
        reajuste();
    }

    static void reajuste() {
        String[] opcoes = {"Geral", "Especifico", "Retornar"};
        int escolha = JOptionPane.showOptionDialog(
                null,
                "REAJUSTE DE PRECOS\n\nEscolha o tipo de reajuste:",
                "Reajuste",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, opcoes, opcoes[0]
        );

        if (escolha == 2 || escolha == JOptionPane.CLOSED_OPTION) return;

        if (escolha == 0) {
            do {
                double prctg = 0;
                while (true) {
                    String entrada = input("REAJUSTE GERAL\n\nDigite o percentual de reajuste:\n(use valor negativo para reduzir, ex: -5)");
                    if (entrada == null) return;
                    try {
                        prctg = Double.parseDouble(entrada.trim().replace(",", "."));
                        break;
                    } catch (NumberFormatException e) {
                        erro("Valor invalido! Use o formato: 10.5");
                    }
                }
                if (confirmar("Confirma o reajuste de " + prctg + "% em todos os produtos?")) {
                    for (int i = 0; i < total; i++) {
                        precos[i] = precos[i] * prctg / 100 + precos[i];
                    }
                    sucesso("Reajuste geral de " + prctg + "% aplicado com sucesso!");
                } else {
                    aviso("Reajuste cancelado.");
                }
            } while (confirmar("Deseja fazer novo reajuste?"));

        } else {
            do {
                String nome = input("REAJUSTE ESPECIFICO\n\nNome do produto:");
                if (nome == null) return;

                int i = buscar(nome.trim());

                if (i == -1) {
                    erro("Produto nao encontrado!");
                    continue;
                }

                JOptionPane.showMessageDialog(null,
                        "PRODUTO ENCONTRADO\n\n"
                        + "Nome      : " + nomes[i] + "\n"
                        + "Unidade   : " + unidades[i] + "\n"
                        + "Preco atual: R$ " + String.format("%.2f", precos[i]),
                        "Dados do Produto", JOptionPane.INFORMATION_MESSAGE);

                double prctg = 0;
                while (true) {
                    String entrada = input("REAJUSTE: " + nomes[i] + "\n\nDigite o percentual de reajuste:\n(use valor negativo para reduzir, ex: -5)");
                    if (entrada == null) return;
                    try {
                        prctg = Double.parseDouble(entrada.trim().replace(",", "."));
                        break;
                    } catch (NumberFormatException e) {
                        erro("Valor invalido! Use o formato: 10.5");
                    }
                }

                if (confirmar("Confirma o reajuste de " + prctg + "% no produto " + nomes[i] + "?")) {
                    precos[i] = precos[i] * prctg / 100 + precos[i];
                    sucesso("Novo preco de " + nomes[i] + ": R$ " + String.format("%.2f", precos[i]));
                } else {
                    aviso("Reajuste cancelado.");
                }

            } while (confirmar("Deseja fazer novo reajuste?"));
        }
    }

    static int buscar(String nome) {
        for (int i = 0; i < total; i++) {
            if (nomes[i].equalsIgnoreCase(nome)) return i;
        }
        return -1;
    }

    static String input(String mensagem) {
        return JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
    }

    static void erro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    static void sucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    static void aviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    static boolean confirmar(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Confirmacao", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }
}