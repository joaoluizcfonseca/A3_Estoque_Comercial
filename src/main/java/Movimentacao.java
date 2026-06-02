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
                case 1:
                    menuSaida();
                    break;
                case 2:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;
            }
        }
    }

    private void menuEntrada() {
        boolean novaEntrada;

        do {
            String nomeProduto = lerNomeProduto("MOVIMENTAÇÃO - ENTRADA DE PRODUTO");
            if (nomeProduto == null) return;

            int indice = buscarProduto(nomeProduto);

            if (indice == -1) {
                exibirMensagem(
                    "Produto \"" + nomeProduto + "\" não encontrado no estoque!",
                    "Produto não encontrado", JOptionPane.WARNING_MESSAGE);
                novaEntrada = perguntarNova("Deseja realizar uma nova entrada?", "Nova Entrada");
                continue;
            }

            int qtdeEntrada = lerQuantidadePositiva(
                "MOVIMENTAÇÃO - ENTRADA DE PRODUTO\n\n"
                + "Produto   : " + nomes[indice] + "\n"
                + "Qtde Atual: " + quantidades[indice] + " " + unidades[indice] + "\n\n"
                + "Informe a quantidade de entrada:"
            );
            if (qtdeEntrada == -1) return;

            int qtdeFinal = quantidades[indice] + qtdeEntrada;

            if (confirmarOperacao(montarTextoEntrada(indice, qtdeEntrada, qtdeFinal))) {
                registrarEntrada(indice, qtdeFinal);
            } else {
                exibirMensagem("Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }

            novaEntrada = perguntarNova("Deseja realizar uma nova entrada?", "Nova Entrada");

        } while (novaEntrada);
    }

    private String montarTextoEntrada(int indice, int qtdeEntrada, int qtdeFinal) {
        return "MOVIMENTAÇÃO - ENTRADA DE PRODUTO\n\n"
            + "Produto     : " + nomes[indice] + "\n"
            + "Qtde Atual  : " + quantidades[indice] + " " + unidades[indice] + "\n"
            + "Qtde Entrada: " + qtdeEntrada + "\n"
            + "Qtde Final  : " + qtdeFinal + "\n\n"
            + "Confirma a entrada?";
    }

    private void registrarEntrada(int indice, int qtdeFinal) {
        quantidades[indice] = qtdeFinal;
        exibirMensagem(
            "Entrada registrada com sucesso!\n\n"
            + "Produto        : " + nomes[indice] + "\n"
            + "Nova Quantidade: " + qtdeFinal + " " + unidades[indice],
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private int exibirOpcoes(String mensagem, String titulo, String[] opcoes) {
        return JOptionPane.showOptionDialog(
            null, mensagem, titulo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null, opcoes, opcoes[0]
        );
    }

    private void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }

    private boolean confirmarOperacao(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(
            null, mensagem, "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private boolean perguntarNova(String mensagem, String titulo) {
        int resposta = JOptionPane.showConfirmDialog(
            null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    private String lerNomeProduto(String titulo) {
        while (true) {
            String nome = JOptionPane.showInputDialog(
                null,
                titulo + "\n\nDigite o nome do produto:",
                "Movimentação",
                JOptionPane.PLAIN_MESSAGE
            );

            if (nome == null) return null;

            if (!nome.trim().isEmpty()) return nome.trim().toUpperCase();

            exibirMensagem(
                "O nome do produto não pode ser vazio!",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int lerQuantidadePositiva(String mensagem) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                null, mensagem, "Movimentação", JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try {
                int qtde = Integer.parseInt(input.trim());
                if (qtde > 0) return qtde;
                exibirMensagem(
                    "A quantidade deve ser maior que zero!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                exibirMensagem(
                    "Digite um número inteiro válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int lerQuantidadeSaida(String mensagem, int estoqueDisponivel) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                null, mensagem, "Movimentação", JOptionPane.PLAIN_MESSAGE);

            if (input == null) return -1;

            try {
                int qtde = Integer.parseInt(input.trim());
                if (qtde <= 0) {
                    exibirMensagem(
                        "A quantidade deve ser maior que zero!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } else if (qtde > estoqueDisponivel) {
                    exibirMensagem(
                        "Estoque insuficiente!\nQuantidade disponível: " + estoqueDisponivel,
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    return qtde;
                }
            } catch (NumberFormatException e) {
                exibirMensagem(
                    "Digite um número inteiro válido!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int buscarProduto(String nome) {
        for (int i = 0; i < totalProdutos; i++) {
            if (nomes[i].equalsIgnoreCase(no
