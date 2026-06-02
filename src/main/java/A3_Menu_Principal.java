import javax.swing.JOptionPane;

/**
 * Sistema de Controle de Estoque Comercial.
 *
 * Menu principal com os módulos:
 * Cadastro de Preços, Movimentação de Estoque,
 * Reajuste de Preços e Relatórios.
 *
 * @author Mateus Machado Da Costa Zanela
 * @version 1.0
 */
public class A3_Menu_Principal {

    /**
     * Exibe o menu principal do sistema.
     *
     * @param args argumentos de linha de comando
     */
    public static void main(String[] args) {
        menuPrincipal();
    }

    /**
     * Mostra o menu principal com os módulos do sistema.
     */
    static void menuPrincipal() {
        while (true) {
            String[] opcoes = {
                "Cadastro de Precos",
                "Movimentacao de Estoque",
                "Reajuste de Precos",
                "Relatorios",
                "Finalizar"
            };

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "CONTROLE DE ESTOQUE\n\nEscolha um modulo:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == 4 || escolha == JOptionPane.CLOSED_OPTION) {
                break;
            }

            switch (escolha) {
                case 0:
                    menuCadastroPrecos();
                    break;

                case 1:
                    menuMovimentacaoEstoque();
                    break;

                case 2:
                    menuReajustePrecos();
                    break;

                case 3:
                    menuRelatorios();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida.");
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Sistema encerrado.",
                "Fim",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Exibe o menu do módulo Cadastro de Preços.
     */
    static void menuCadastroPrecos() {
        Cadastro_Produto.menuCadastro();
    }

    /**
     * Exibe o menu do módulo Movimentação de Estoque.
     */
    static void menuMovimentacaoEstoque() {
        JOptionPane.showMessageDialog(
                null,
                "Modulo: Movimentacao de Estoque",
                "Movimentacao de Estoque",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Exibe o menu do módulo Reajuste de Preços.
     */
    static void menuReajustePrecos() {
        JOptionPane.showMessageDialog(
                null,
                "Modulo: Reajuste de Precos",
                "Reajuste de Precos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Exibe o menu do módulo Relatórios.
     */
    static void menuRelatorios() {
        JOptionPane.showMessageDialog(
                null,
                "Modulo: Relatorios",
                "Relatorios",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
