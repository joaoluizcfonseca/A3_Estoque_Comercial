import javax.swing.JOptionPane;

/**
 * Modulo de Relatorios do Sistema de Controle de Estoque.
 *
 * <p>Responsavel por exibir os dois relatorios do sistema:</p>
 * <ul>
 *   <li>Lista de Precos: exibe todos os produtos em ordem alfabetica com preco e unidade.</li>
 *   <li>Balanco Fisico-Financeiro: exibe todos os produtos com quantidade, preco total
 *       e valor total do estoque.</li>
 * </ul>
 *
 * @author Joao Luiz Candaten Fonseca
 * @version 4.0
 */
public class Relatorios {

    /** Vetor com os nomes dos produtos cadastrados. */
    private String[] nomes;

    /** Vetor com os precos unitarios dos produtos. */
    private double[] precos;

    /** Vetor com as unidades de medida dos produtos. */
    private String[] unidades;

    /** Vetor com as quantidades em estoque dos produtos. */
    private int[] quantidades;

    /** Numero total de produtos cadastrados. */
    private int totalProdutos;

    /**
     * Cria o modulo de relatorios recebendo os vetores compartilhados com o sistema.
     *
     * @param nomes         vetor com os nomes dos produtos
     * @param precos        vetor com os precos unitarios
     * @param unidades      vetor com as unidades de medida
     * @param quantidades   vetor com as quantidades em estoque
     * @param totalProdutos numero de produtos cadastrados
     */
    public Relatorios(String[] nomes, double[] precos, String[] unidades,
                      int[] quantidades, int totalProdutos) {
        this.nomes         = nomes;
        this.precos        = precos;
        this.unidades      = unidades;
        this.quantidades   = quantidades;
        this.totalProdutos = totalProdutos;
    }

    /**
     * Procedimento: atualiza o numero de produtos cadastrados.
     *
     * @param total novo total de produtos cadastrados
     */
    public void setTotalProdutos(int total) {
        this.totalProdutos = total;
    }

    // =========================================================================
    // TELA 1.4 - Menu de Relatorios
    // =========================================================================

    /**
     * Sub-rotina principal do modulo de relatorios.
     * Exibe o menu e direciona para o relatorio escolhido.
     * Retorna ao menu principal ao selecionar Retornar.
     */
    public void menuRelatorios() {
        boolean continuar = true;
        while (continuar) {
            String[] opcoes = {"Lista de Precos", "Balanco Fisico-Financeiro", "Retornar"};
            int escolha = JOptionPane.showOptionDialog(
                null,
                "XYZ COMERCIO DE PRODUTOS LTDA.\nSISTEMA DE CONTROLE DE ESTOQUE\n\nRELATORIOS\n\nEscolha uma opcao:",
                "Relatorios",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            switch (escolha) {
                case 0:
                    exibirMensagem("Em desenvolvimento...", "Lista de Precos", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    exibirMensagem("Em desenvolvimento...", "Balanco Fisico-Financeiro", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;
            }
        }
    }

    /**
     * Procedimento: exibe uma janela de mensagem ao usuario.
     *
     * @param mensagem texto a ser exibido
     * @param titulo   titulo da janela
     * @param tipo     tipo da mensagem
     */
    private void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }
}
