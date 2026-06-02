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
    Movimentacao mov = new Movimentacao(
            Cadastro_Produto.nomes,
            Cadastro_Produto.precos,
            Cadastro_Produto.unidades,
            Cadastro_Produto.quantidades,
            Cadastro_Produto.total
    );

    mov.menuMovimentacao();
}

    /**
     * Exibe o menu do módulo Reajuste de Preços.
     */
    static void menuReajustePrecos() {

    A3_Reajuste_Preco.nomes = Cadastro_Produto.nomes;
    A3_Reajuste_Preco.unidades = Cadastro_Produto.unidades;
    A3_Reajuste_Preco.precos = Cadastro_Produto.precos;
    A3_Reajuste_Preco.quantidades = Cadastro_Produto.quantidades;
    A3_Reajuste_Preco.total = Cadastro_Produto.total;

    A3_Reajuste_Preco.reajuste();
}

    /**
     * Exibe o menu do módulo Relatórios.
     */
    static void menuRelatorios() {
    Relatorios rel = new Relatorios(
            Cadastro_Produto.nomes,
            Cadastro_Produto.precos,
            Cadastro_Produto.unidades,
            Cadastro_Produto.quantidades,
            Cadastro_Produto.total
    );

    rel.menuRelatorios();
    }
}
