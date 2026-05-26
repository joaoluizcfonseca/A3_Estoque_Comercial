package A3_Estoque_Comercial;

import javax.swing.JOptionPane;

/**
 * Módulo de Movimentação do Sistema de Controle de Estoque.
 *
 * <p>Responsável pelas operações de entrada e saída de produtos no estoque.
 * Esta é a versão final completa, implementando as Telas 1.2, 1.2.1 e 1.2.2.</p>
 *
 * <p>Sub-rotinas implementadas:</p>
 * <ul>
 *   <li>{@link #menuMovimentacao()} - menu principal de movimentação (Tela 1.2)</li>
 *   <li>{@link #menuEntrada()} - gerencia o fluxo completo de entrada (Tela 1.2.1)</li>
 *   <li>{@link #menuSaida()} - gerencia o fluxo completo de saída (Tela 1.2.2)</li>
 *   <li>{@link #registrarEntrada(int, int)} - efetiva a entrada no estoque</li>
 *   <li>{@link #registrarSaida(int, int)} - efetiva a saída no estoque</li>
 *   <li>{@link #montarTextoEntrada(int, int, int)} - monta texto de confirmação de entrada</li>
 *   <li>{@link #montarTextoSaida(int, int, int)} - monta texto de confirmação de saída</li>
 *   <li>{@link #lerNomeProduto(String)} - lê e valida o nome do produto</li>
 *   <li>{@link #lerQuantidadePositiva(String)} - lê e valida quantidade maior que zero</li>
 *   <li>{@link #lerQuantidadeSaida(String, int)} - lê e valida quantidade respeitando estoque</li>
 *   <li>{@link #buscarProduto(String)} - busca produto pelo nome e retorna índice</li>
 *   <li>{@link #confirmarOperacao(String)} - exibe janela de confirmação Sim/Não</li>
 *   <li>{@link #perguntarNova(String, String)} - pergunta se deseja nova operação</li>
 *   <li>{@link #exibirOpcoes(String, String, String[])} - exibe janela de opções</li>
 *   <li>{@link #exibirMensagem(String, String, int)} - exibe mensagens ao usuário</li>
 * </ul>
 *
 * @author Davi Schuchowsky Boscarino de Medeiros
 * @version 3.0
 */
public class Movimentacao {

    /** Vetor com os nomes dos produtos cadastrados. */
    private String[] nomes;

    /** Vetor com os preços unitários dos produtos. */
    private double[] precos;

    /** Vetor com as unidades de medida dos produtos. */
    private String[] unidades;

    /** Vetor com as quantidades em estoque de cada produto. */
    private int[] quantidades;

    /** Número total de produtos cadastrados no sistema. */
    private int totalProdutos;

    // =========================================================================
    // Construtor
    // =========================================================================

    /**
     * Cria o módulo de movimentação recebendo os vetores compartilhados do sistema.
     *
     * @param nomes         vetor com os nomes dos produtos
     * @param precos        vetor com os preços unitários
     * @param unidades      vetor com as unidades de medida
     * @param quantidades   vetor com as quantidades em estoque
     * @param totalProdutos número de produtos cadastrados
     */
    public Movimentacao(String[] nomes, double[] precos, String[] unidades,
                        int[] quantidades, int totalProdutos) {
        this.nomes         = nomes;
        this.precos        = precos;
        this.unidades      = unidades;
        this.quantidades   = quantidades;
        this.totalProdutos = totalProdutos;
    }

    // =========================================================================
    // Procedimento público: atualiza total de produtos
    // =========================================================================

    /**
     * Atualiza o número de produtos cadastrados.
     *
     * <p>Deve ser chamado pelo Main sempre que um produto for incluído
     * ou excluído no módulo de Cadastro, para manter a sincronia.</p>
     *
     * @param total novo total de produtos cadastrados
     */
    public void setTotalProdutos(int total) {
        this.totalProdutos = total;
    }

    // =========================================================================
    // TELA 1.2 - Menu de Movimentação
    // =========================================================================

    /**
     * Sub-rotina principal do módulo de movimentação (Tela 1.2).
     *
     * <p>Exibe o menu com as opções Entrada, Saída e Retornar.
     * Permanece em loop até que o usuário selecione Retornar,
     * momento em que o controle volta ao menu principal.</p>
     */
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

    // =========================================================================
    // TELA 1.2.1 - Entrada de Produto
    // =========================================================================

    /**
     * Sub-rotina: gerencia o fluxo completo de entrada de produto (Tela 1.2.1).
     *
     * <p>Lê o nome do produto, verifica se está cadastrado, solicita a quantidade
     * de entrada, calcula a quantidade final, pede confirmação e registra a operação.
     * Ao final pergunta se o usuário deseja realizar uma nova entrada.</p>
     */
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

    /**
     * Função: monta o texto de resumo para a janela de confirmação de entrada.
     *
     * @param indice      índice do produto no vetor
     * @param qtdeEntrada quantidade informada para entrada
     * @param qtdeFinal   quantidade resultante após a entrada
     * @return            texto formatado pronto para exibição na janela de confirmação
     */
    private String montarTextoEntrada(int indice, int qtdeEntrada, int qtdeFinal) {
        return "MOVIMENTAÇÃO - ENTRADA DE PRODUTO\n\n"
            + "Produto     : " + nomes[indice] + "\n"
            + "Qtde Atual  : " + quantidades[indice] + " " + unidades[indice] + "\n"
            + "Qtde Entrada: " + qtdeEntrada + "\n"
            + "Qtde Final  : " + qtdeFinal + "\n\n"
            + "Confirma a entrada?";
    }

    /**
     * Procedimento: efetiva a entrada do produto no estoque e exibe mensagem de sucesso.
     *
     * <p>Atualiza o vetor de quantidades com o novo valor e informa o usuário.</p>
     *
     * @param indice    índice do produto no vetor
     * @param qtdeFinal nova quantidade após a entrada ser registrada
     */
    private void registrarEntrada(int indice, int qtdeFinal) {
        quantidades[indice] = qtdeFinal;
        exibirMensagem(
            "Entrada registrada com sucesso!\n\n"
            + "Produto        : " + nomes[indice] + "\n"
            + "Nova Quantidade: " + qtdeFinal + " " + unidades[indice],
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // =========================================================================
    // TELA 1.2.2 - Saída de Produto
    // =========================================================================

    /**
     * Sub-rotina: gerencia o fluxo completo de saída de produto (Tela 1.2.2).
     *
     * <p>Lê o nome do produto, verifica se está cadastrado, solicita a quantidade
     * de saída validando que não ultrapasse o estoque disponível, calcula a
     * quantidade final, pede confirmação e registra a operação.
     * Ao final pergunta se o usuário deseja realizar uma nova saída.</p>
     */
    private void menuSaida() {
        boolean novaSaida;

        do {
            String nomeProduto = lerNomeProduto("MOVIMENTAÇÃO - SAÍDA DE PRODUTO");
            if (nomeProduto == null) return;

            int indice = buscarProduto(nomeProduto);

            if (indice == -1) {
                exibirMensagem(
                    "Produto \"" + nomeProduto + "\" não encontrado no estoque!",
                    "Produto não encontrado", JOptionPane.WARNING_MESSAGE);
                novaSaida = perguntarNova("Deseja realizar uma nova saída?", "Nova Saída");
                continue;
            }

            int qtdeSaida = lerQuantidadeSaida(
                "MOVIMENTAÇÃO - SAÍDA DE PRODUTO\n\n"
                + "Produto   : " + nomes[indice] + "\n"
                + "Qtde Atual: " + quantidades[indice] + " " + unidades[indice] + "\n\n"
                + "Informe a quantidade de saída:",
                quantidades[indice]
            );
            if (qtdeSaida == -1) return;

            int qtdeFinal = quantidades[indice] - qtdeSaida;

            if (confirmarOperacao(montarTextoSaida(indice, qtdeSaida, qtdeFinal))) {
                registrarSaida(indice, qtdeFinal);
            } else {
                exibirMensagem("Operação cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }

            novaSaida = perguntarNova("Deseja realizar uma nova saída?", "Nova Saída");

        } while (novaSaida);
    }

    /**
     * Função: monta o texto de resumo para a janela de confirmação de saída.
     *
     * @param indice    índice do produto no vetor
     * @param qtdeSaida quantidade informada para saída
     * @param qtdeFinal quantidade resultante após a saída
     * @return          texto formatado pronto para exibição na janela de confirmação
     */
    private String montarTextoSaida(int indice, int qtdeSaida, int qtdeFinal) {
        return "MOVIMENTAÇÃO - SAÍDA DE PRODUTO\n\n"
            + "Produto   : " + nomes[indice] + "\n"
            + "Qtde Atual: " + quantidades[indice] + " " + unidades[indice] + "\n"
            + "Qtde Saída: " + qtdeSaida + "\n"
            + "Qtde Final: " + qtdeFinal + "\n\n"
            + "Confirma a saída?";
    }

    /**
     * Procedimento: efetiva a saída do produto no estoque e exibe mensagem de sucesso.
     *
     * <p>Atualiza o vetor de quantidades com o novo valor e informa o usuário.</p>
     *
     * @param indice    índice do produto no vetor
     * @param qtdeFinal nova quantidade após a saída ser registrada
     */
    private void registrarSaida(int indice, int qtdeFinal) {
        quantidades[indice] = qtdeFinal;
        exibirMensagem(
            "Saída registrada com sucesso!\n\n"
            + "Produto        : " + nomes[indice] + "\n"
            + "Nova Quantidade: " + qtdeFinal + " " + unidades[indice],
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // =========================================================================
    // SUB-ROTINAS AUXILIARES
    // =========================================================================

    /**
     * Função: lê e valida o nome do produto digitado pelo usuário via JOptionPane.
     *
     * <p>Repete a solicitação enquanto o campo estiver vazio.
     * Converte o nome para letras maiúsculas para padronizar a busca.</p>
     *
     * @param titulo texto do título exibido na janela de entrada
     * @return       nome do produto em maiúsculas, ou {@code null} se o usuário cancelou
     */
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

    /**
     * Função: lê e valida uma quantidade inteira positiva via JOptionPane.
     *
     * <p>Repete a solicitação enquanto o valor digitado for inválido
     * (não numérico ou menor/igual a zero).</p>
     *
     * @param mensagem texto da janela de entrada exibido ao usuário
     * @return         quantidade inteira válida (maior que zero),
     *                 ou {@code -1} se o usuário cancelou
     */
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

    /**
     * Função: lê e valida a quantidade de saída via JOptionPane.
     *
     * <p>Repete a solicitação enquanto o valor for inválido. Garante que a
     * quantidade seja maior que zero e não ultrapasse o estoque disponível,
     * exibindo mensagens de erro específicas para cada situação.</p>
     *
     * @param mensagem           texto da janela de entrada exibido ao usuário
     * @param estoqueDisponivel  quantidade atual disponível em estoque
     * @return                   quantidade inteira válida para saída,
     *                           ou {@code -1} se o usuário cancelou
     */
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

    /**
     * Função: busca um produto pelo nome no vetor de produtos cadastrados.
     *
     * <p>A comparação é feita sem distinção entre letras maiúsculas e minúsculas.</p>
     *
     * @param nome nome do produto a ser buscado
     * @return     índice do produto no vetor, ou {@code -1} se não encontrado
     */
    private int buscarProduto(String nome) {
        for (int i = 0; i < totalProdutos; i++) {
            if (nomes[i].equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Função: exibe uma janela de confirmação com os botões Sim e Não.
     *
     * @param mensagem texto da pergunta a ser confirmada
     * @return         {@code true} se o usuário clicou em Sim,
     *                 {@code false} se clicou em Não ou fechou a janela
     */
    private boolean confirmarOperacao(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(
            null, mensagem, "Confirmação", JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    /**
     * Função: pergunta ao usuário se deseja realizar uma nova operação.
     *
     * @param mensagem texto da pergunta exibida
     * @param titulo   título da janela de pergunta
     * @return         {@code true} se o usuário clicou em Sim,
     *                 {@code false} se clicou em Não ou fechou a janela
     */
    private boolean perguntarNova(String mensagem, String titulo) {
        int resposta = JOptionPane.showConfirmDialog(
            null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        return resposta == JOptionPane.YES_OPTION;
    }

    /**
     * Função auxiliar: exibe uma janela com botões de opções e retorna a escolha.
     *
     * @param mensagem texto descritivo exibido na janela
     * @param titulo   título da janela
     * @param opcoes   array com os textos dos botões de opção
     * @return         índice da opção escolhida, ou {@code JOptionPane.CLOSED_OPTION} se fechou
     */
    private int exibirOpcoes(String mensagem, String titulo, String[] opcoes) {
        return JOptionPane.showOptionDialog(
            null, mensagem, titulo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null, opcoes, opcoes[0]
        );
    }

    /**
     * Procedimento auxiliar: exibe uma janela de mensagem ao usuário.
     *
     * @param mensagem texto a ser exibido na janela
     * @param titulo   título da janela
     * @param tipo     tipo visual da mensagem:
     *                 {@code JOptionPane.INFORMATION_MESSAGE},
     *                 {@code JOptionPane.WARNING_MESSAGE} ou
     *                 {@code JOptionPane.ERROR_MESSAGE}
     */
    private void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }
}
