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
                    listaDePrecos();
                    break;
                case 1:
                    balancoFisicoFinanceiro();
                    break;
                case 2:
                case JOptionPane.CLOSED_OPTION:
                    continuar = false;
                    break;
            }
        }
    }

    // =========================================================================
    // TELA 1.4.1 - Lista de Precos
    // =========================================================================

    /**
     * Sub-rotina: gera e exibe a Lista de Precos de todos os produtos em ordem alfabetica.
     *
     * <p>Formato do relatorio: PRODUTO | UND | PRECO</p>
     * <p>Se nao houver produtos cadastrados, exibe mensagem de aviso.</p>
     */
    private void listaDePrecos() {
        if (totalProdutos == 0) {
            exibirMensagem("Nenhum produto cadastrado.", "Lista de Precos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] nomesOrd    = copiarVetorString(nomes, totalProdutos);
        double[] precosOrd   = copiarVetorDouble(precos, totalProdutos);
        String[] unidadesOrd = copiarVetorString(unidades, totalProdutos);
        int[]    quantOrd    = copiarVetorInt(quantidades, totalProdutos);

        ordenarAlfabetico(nomesOrd, precosOrd, unidadesOrd, quantOrd, totalProdutos);

        String texto = "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                     + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                     + "LISTA DE PRECOS\n"
                     + "________________________________\n\n";

        for (int i = 0; i < totalProdutos; i++) {
            texto = texto
                  + "Produto : " + nomesOrd[i] + "\n"
                  + "Unidade : " + unidadesOrd[i] + "\n"
                  + "Preco   : R$ " + String.format("%.2f", precosOrd[i]) + "\n"
                  + "________________________________\n";
        }

        exibirMensagem(texto, "Lista de Precos", JOptionPane.PLAIN_MESSAGE);
    }

    // =========================================================================
    // TELA 1.4.2 - Balanco Fisico-Financeiro
    // =========================================================================

    /**
     * Sub-rotina: gera e exibe o Balanco Fisico-Financeiro em ordem alfabetica.
     *
     * <p>Formato do relatorio: PRODUTO | UND | PRECO UNITARIO | QTDE | PRECO TOTAL</p>
     * <p>Exibe ao final o total de itens e o valor total do estoque.</p>
     * <p>Se nao houver produtos cadastrados, exibe mensagem de aviso.</p>
     */
    private void balancoFisicoFinanceiro() {
        if (totalProdutos == 0) {
            exibirMensagem("Nenhum produto cadastrado.", "Balanco Fisico-Financeiro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] nomesOrd    = copiarVetorString(nomes, totalProdutos);
        double[] precosOrd   = copiarVetorDouble(precos, totalProdutos);
        String[] unidadesOrd = copiarVetorString(unidades, totalProdutos);
        int[]    quantOrd    = copiarVetorInt(quantidades, totalProdutos);

        ordenarAlfabetico(nomesOrd, precosOrd, unidadesOrd, quantOrd, totalProdutos);

        int    totalItens = 0;
        double valorTotal = 0.0;

        String texto = "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                     + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                     + "BALANCO FISICO-FINANCEIRO\n"
                     + "________________________________\n\n";

        for (int i = 0; i < totalProdutos; i++) {
            double precoTotalProd = precosOrd[i] * quantOrd[i];
            totalItens = totalItens + quantOrd[i];
            valorTotal = valorTotal + precoTotalProd;

            texto = texto
                  + "Produto     : " + nomesOrd[i] + "\n"
                  + "Unidade     : " + unidadesOrd[i] + "\n"
                  + "Preco Unit. : R$ " + String.format("%.2f", precosOrd[i]) + "\n"
                  + "Quantidade  : " + quantOrd[i] + "\n"
                  + "Preco Total : R$ " + String.format("%.2f", precoTotalProd) + "\n"
                  + "________________________________\n";
        }

        texto = texto
              + "\nTOTAL DE ITENS NO ESTOQUE : " + totalItens + "\n"
              + "VALOR TOTAL DO ESTOQUE    : R$ " + String.format("%.2f", valorTotal);

        exibirMensagem(texto, "Balanco Fisico-Financeiro", JOptionPane.PLAIN_MESSAGE);
    }

    // =========================================================================
    // SUB-ROTINAS AUXILIARES
    // =========================================================================

    /**
     * Sub-rotina: ordena os vetores de produtos em ordem alfabetica pelo nome (Bubble Sort).
     *
     * @param nomesOrd    vetor de nomes a ser ordenado
     * @param precosOrd   vetor de precos sincronizado
     * @param unidadesOrd vetor de unidades sincronizado
     * @param quantOrd    vetor de quantidades sincronizado
     * @param total       numero de elementos a considerar
     */
    private void ordenarAlfabetico(String[] nomesOrd, double[] precosOrd,
            String[] unidadesOrd, int[] quantOrd, int total) {
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (nomesOrd[j].compareToIgnoreCase(nomesOrd[j + 1]) > 0) {
                    String tmpNome = nomesOrd[j];
                    nomesOrd[j] = nomesOrd[j + 1];
                    nomesOrd[j + 1] = tmpNome;

                    double tmpPreco = precosOrd[j];
                    precosOrd[j] = precosOrd[j + 1];
                    precosOrd[j + 1] = tmpPreco;

                    String tmpUnd = unidadesOrd[j];
                    unidadesOrd[j] = unidadesOrd[j + 1];
                    unidadesOrd[j + 1] = tmpUnd;

                    int tmpQtd = quantOrd[j];
                    quantOrd[j] = quantOrd[j + 1];
                    quantOrd[j + 1] = tmpQtd;
                }
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

    /**
     * Funcao: copia um vetor de Strings ate o indice total.
     *
     * @param origem vetor original
     * @param total  numero de elementos a copiar
     * @return novo vetor com os elementos copiados
     */
    private String[] copiarVetorString(String[] origem, int total) {
        String[] copia = new String[total];
        for (int i = 0; i < total; i++) copia[i] = origem[i];
        return copia;
    }

    /**
     * Funcao: copia um vetor de doubles ate o indice total.
     *
     * @param origem vetor original
     * @param total  numero de elementos a copiar
     * @return novo vetor com os elementos copiados
     */
    private double[] copiarVetorDouble(double[] origem, int total) {
        double[] copia = new double[total];
        for (int i = 0; i < total; i++) copia[i] = origem[i];
        return copia;
    }

    /**
     * Funcao: copia um vetor de inteiros ate o indice total.
     *
     * @param origem vetor original
     * @param total  numero de elementos a copiar
     * @return novo vetor com os elementos copiados
     */
    private int[] copiarVetorInt(int[] origem, int total) {
        int[] copia = new int[total];
        for (int i = 0; i < total; i++) copia[i] = origem[i];
        return copia;
    }
}
