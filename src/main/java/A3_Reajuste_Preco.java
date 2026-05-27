package com.mycompany.a3_reajuste_preco;

import javax.swing.JOptionPane;

/**
 * Modulo de reajuste de precos do sistema de estoque comercial.
 *
 * <p>Permite aplicar reajuste percentual de precos de forma geral
 * (todos os produtos) ou especifica (um produto por nome).
 * Utiliza arrays estaticos paralelos para representar o estoque.</p>
 *
 * <p><b>Estrutura do estoque:</b> cada indice {@code i} representa
 * o mesmo produto nos arrays {@code nomes}, {@code unidades},
 * {@code precos} e {@code quantidades}.</p>
 *
 * @author Mateus Pauli Stahnke
 * @version 1.0
 */
public class A3_Reajuste_Preco {

    /** Capacidade maxima de produtos no estoque. */
    public static final int MAX = 100;

    /** Array com os nomes dos produtos cadastrados. */
    public static String[] nomes = new String[MAX];

    /** Array com as unidades de medida dos produtos (ex: KG, UN, L). */
    public static String[] unidades = new String[MAX];

    /** Array com os precos dos produtos em reais. */
    public static double[] precos = new double[MAX];

    /** Array com as quantidades em estoque de cada produto. */
    public static int[] quantidades = new int[MAX];

    /** Quantidade de produtos atualmente cadastrados no estoque. */
    public static int total = 0;

    /**
     * Metodo principal. Insere dados de teste e executa o modulo de reajuste.
     *
     * <p>Este metodo existe apenas para testar {@link #reajuste()} de forma
     * isolada, sem depender do menu principal do sistema.</p>
     *
     * @param args argumentos de linha de comando (nao utilizados)
     */
    public static void main(String[] args) {
        nomes[0] = "Arroz";
        unidades[0] = "KG";
        precos[0] = 10;
        quantidades[0] = 50;
        total = 1;
        reajuste();
    }

    /**
     * Exibe o menu de reajuste e executa o tipo escolhido pelo usuario.
     *
     * <p>Apresenta tres opcoes:</p>
     * <ul>
     *   <li><b>Geral</b> — aplica o percentual em todos os produtos do estoque.</li>
     *   <li><b>Especifico</b> — busca um produto pelo nome e aplica o percentual apenas nele.</li>
     *   <li><b>Retornar</b> — encerra o metodo sem realizar alteracoes.</li>
     * </ul>
     *
     * <p>A formula de reajuste aplicada e:</p>
     * <pre>
     *   novoPreco = precoAtual + (precoAtual * percentual / 100)
     * </pre>
     *
     * <p>Valores negativos reduzem o preco. O loop {@code do-while} permite
     * realizar multiplos reajustes em sequencia.</p>
     */
    public static void reajuste() {
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
            // reajuste geral
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
            // reajuste especifico
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

    /**
     * Busca um produto no estoque pelo nome, ignorando maiusculas e minusculas.
     *
     * @param nome o nome do produto a ser localizado
     * @return o indice do produto no array, ou {@code -1} se nao for encontrado
     */
    public static int buscar(String nome) {
        for (int i = 0; i < total; i++) {
            if (nomes[i].equalsIgnoreCase(nome)) return i;
        }
        return -1;
    }

    /**
     * Exibe uma caixa de dialogo para entrada de texto pelo usuario.
     *
     * @param mensagem o texto exibido na janela de entrada
     * @return a {@code String} digitada pelo usuario, ou {@code null} se cancelar
     */
    public static String input(String mensagem) {
        return JOptionPane.showInputDialog(null, mensagem, "Entrada", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de erro em uma caixa de dialogo.
     *
     * @param mensagem o texto do erro a ser exibido
     */
    public static void erro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe uma mensagem de sucesso em uma caixa de dialogo.
     *
     * @param mensagem o texto de confirmacao a ser exibido
     */
    public static void sucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de aviso em uma caixa de dialogo.
     *
     * @param mensagem o texto do aviso a ser exibido
     */
    public static void aviso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Exibe uma caixa de confirmacao com opcoes Sim e Nao.
     *
     * @param mensagem a pergunta a ser exibida ao usuario
     * @return {@code true} se o usuario clicar em Sim, {@code false} caso contrario
     */
    public static boolean confirmar(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(null, mensagem, "Confirmacao", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }
}
