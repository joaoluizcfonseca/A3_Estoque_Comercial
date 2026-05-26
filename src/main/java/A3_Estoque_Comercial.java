import javax.swing.JOptionPane;

/**
 * Classe principal do Sistema de Controle de Estoque.
 * Responsável pelo menu principal e inicialização do sistema.
 *
 * @author Eduarda Thais Silva Brandao
 */
public class A3_Estoque_Comercial {

    // -------------------------------------------------------------------------
    // Vetores compartilhados entre os módulos
    // -------------------------------------------------------------------------
    private static final int MAX_PRODUTOS = 100;

    private static String[] nomes      = new String[MAX_PRODUTOS];
    private static double[] precos     = new double[MAX_PRODUTOS];
    private static String[] unidades   = new String[MAX_PRODUTOS];
    private static int[]    quantidades = new int[MAX_PRODUTOS];
    private static int      totalProdutos = 0;

    // -------------------------------------------------------------------------
    // Método principal
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        menuPrincipal();
    }

    // =========================================================================
    // TELA 1.0 - Menu Principal
    // =========================================================================

    /**
     * Sub-rotina principal: exibe o menu e direciona para cada módulo.
     * Encerra o sistema ao selecionar a opção Finalizar.
     */
    private static void menuPrincipal() {
        boolean continuar = true;
        while (continuar) {
            int escolha = exibirOpcoes(
                "XYZ COMERCIO DE PRODUTOS LTDA.\n"
                + "SISTEMA DE CONTROLE DE ESTOQUE\n"
                + "________________________________\n\n"
                + "MENU PRINCIPAL\n\n"
                + "Escolha uma opção:",
                "Menu Principal",
                new String[]{"Cadastro de Produtos", "Movimentação",
                             "Reajuste de Preços", "Relatórios", "Finalizar"}
            );

            switch (escolha) {
                case 0:
                    // Cadastro de Produtos - a implementar
                    exibirMensagem("Módulo em desenvolvimento...", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    Movimentacao mov = new Movimentacao(nomes, precos, unidades, quantidades, totalProdutos);
                    mov.menuMovimentacao();
                    break;
                case 2:
                    // Reajuste de Preços - a implementar
                    exibirMensagem("Módulo em desenvolvimento...", "Reajuste", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    Relatorios rel = new Relatorios(nomes, precos, unidades, quantidades, totalProdutos);
                    rel.menuRelatorios();
                    break;
                case 4:
                case JOptionPane.CLOSED_OPTION:
                    int confirma = JOptionPane.showConfirmDialog(null,
                        "Deseja realmente finalizar o sistema?",
                        "Finalizar", JOptionPane.YES_NO_OPTION);
                    if (confirma == JOptionPane.YES_OPTION) {
                        exibirMensagem("Sistema encerrado. Até logo!", "Finalizar", JOptionPane.INFORMATION_MESSAGE);
                        continuar = false;
                    }
                    break;
            }
        }
    }

    // =========================================================================
    // SUB-ROTINAS AUXILIARES
    // =========================================================================

    /**
     * Procedimento: exibe uma janela de mensagem ao usuário.
     */
    private static void exibirMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }

    /**
     * Função: exibe janela com botões de opções e retorna o índice escolhido.
     */
    private static int exibirOpcoes(String mensagem, String titulo, String[] opcoes) {
        return JOptionPane.showOptionDialog(
            null, mensagem, titulo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE,
            null, opcoes, opcoes[0]
        );
    }
}
