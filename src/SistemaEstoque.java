
import javax.swing.JOptionPane;

public class SistemaEstoque {

    static final int MAX = 100;
    static String[] nomes = new String[MAX];
    static String[] unidades = new String[MAX];
    static double[] precos = new double[MAX];
    static int[] quantidades = new int[MAX];
    static int total = 0;

    public static void main(String[] args) {
        while (true) {
            String[] opcoes = {"Cadastro de Produtos", "Finalizar"};
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "CONTROLE DE ESTOQUE",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, opcoes, opcoes[0]
            );

            if (escolha == 1 || escolha == JOptionPane.CLOSED_OPTION) {
                break;
            }
            if (escolha == 0) {
                menuCadastro();
            }
        }

        JOptionPane.showMessageDialog(null, "Sistema encerrado.", "Fim", JOptionPane.INFORMATION_MESSAGE);
    }

    static void menuCadastro() {
        while (true) {
            String[] opcoes = {"Inclusao", "Alteracao", "Consulta", "Exclusao", "Retornar"};
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "CONTROLE DE ESTOQUE\nCADASTRO DE PRODUTOS",
                    "Cadastro",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, opcoes, opcoes[0]
            );

            if (escolha == 4 || escolha == JOptionPane.CLOSED_OPTION) {
                break;
            }

            switch (escolha) {
                case 0:
                    inclusao();
                    break;
                case 1:
                    alteracao();
                    break;
                case 2:
                    consulta();
                    break;
                case 3:
                    exclusao();
                    break;
            }
        }
    }

    static void inclusao() {
        do {
            if (total >= MAX) {
                erro("Estoque cheio! Nao e possivel incluir mais produtos.");
                return;
            }

            // Nome
            String nome;
            while (true) {
                nome = input("INCLUSAO DE PRODUTO\n\nNome do produto:");
                if (nome == null) {
                    return;
                }
                nome = nome.trim();
                if (nome.isEmpty()) {
                    erro("Nome nao pode ser vazio!");
                    continue;
                }
                if (buscar(nome) != -1) {
                    erro("Produto ja cadastrado!");
                    continue;
                }
                break;
            }

            String unidade;
            while (true) {
                unidade = input("INCLUSAO: " + nome + "\n\nUnidade (ex: UN, KG, LT):");
                if (unidade == null) {
                    return;
                }
                unidade = unidade.trim().toUpperCase();
                if (unidade.isEmpty()) {
                    erro("Unidade nao pode ser vazia!");
                    continue;
                }
                break;
            }

            double preco = 0;
            while (true) {
                String entrada = input("INCLUSAO: " + nome + "\n\nPreco (ex: 10.50):");
                if (entrada == null) {
                    return;
                }
                try {
                    preco = Double.parseDouble(entrada.trim().replace(",", "."));
                    if (preco <= 0) {
                        erro("Preco deve ser maior que zero!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    erro("Valor invalido! Use o formato: 10.50");
                }
            }

            int quantidade = 0;
            while (true) {
                String entrada = input("INCLUSAO: " + nome + "\n\nQuantidade:");
                if (entrada == null) {
                    return;
                }
                try {
                    quantidade = Integer.parseInt(entrada.trim());
                    if (quantidade < 0) {
                        erro("Quantidade nao pode ser negativa!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    erro("Digite um numero inteiro!");
                }
            }

            String resumo = "Confirma a inclusao?\n\n"
                    + "Nome      : " + nome + "\n"
                    + "Unidade   : " + unidade + "\n"
                    + "Preco     : R$ " + String.format("%.2f", preco) + "\n"
                    + "Quantidade: " + quantidade;

            if (confirmar(resumo)) {
                nomes[total] = nome;
                unidades[total] = unidade;
                precos[total] = preco;
                quantidades[total] = quantidade;
                total++;
                sucesso("Produto incluido com sucesso!");
            } else {
                aviso("Inclusao cancelada.");
            }

        } while (confirmar("Deseja fazer nova inclusao?"));
    }

    static void alteracao() {
        do {
            String nome = input("ALTERACAO DE PRODUTO\n\nNome do produto:");
            if (nome == null) {
                return;
            }
            int i = buscar(nome.trim());

            if (i == -1) {
                erro("Produto nao encontrado!");
                continue;
            }

            String entrada;
            String novaUnidade = unidades[i];
            entrada = input("ALTERACAO: " + nomes[i]
                    + "\n\nUnidade atual: " + unidades[i]
                    + "\nNova unidade (deixe vazio para manter):");
            if (entrada == null) {
                return;
            }
            if (!entrada.trim().isEmpty()) {
                novaUnidade = entrada.trim().toUpperCase();
            }

            // Novo preco
            double novoPreco = precos[i];
            while (true) {
                entrada = input("ALTERACAO: " + nomes[i]
                        + "\n\nPreco atual: R$ " + String.format("%.2f", precos[i])
                        + "\nNovo preco (deixe vazio para manter):");
                if (entrada == null) {
                    return;
                }
                if (entrada.trim().isEmpty()) {
                    break;
                }
                try {
                    novoPreco = Double.parseDouble(entrada.trim().replace(",", "."));
                    if (novoPreco <= 0) {
                        erro("Preco deve ser maior que zero!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    erro("Valor invalido! Use o formato: 10.50");
                }
            }

            int novaQtd = quantidades[i];
            while (true) {
                entrada = input("ALTERACAO: " + nomes[i]
                        + "\n\nQuantidade atual: " + quantidades[i]
                        + "\nNova quantidade (deixe vazio para manter):");
                if (entrada == null) {
                    return;
                }
                if (entrada.trim().isEmpty()) {
                    break;
                }
                try {
                    novaQtd = Integer.parseInt(entrada.trim());
                    if (novaQtd < 0) {
                        erro("Quantidade nao pode ser negativa!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    erro("Digite um numero inteiro!");
                }
            }

            String resumo = "Confirma a alteracao?\n\n"
                    + "Nome      : " + nomes[i] + "\n"
                    + "Unidade   : " + novaUnidade + "\n"
                    + "Preco     : R$ " + String.format("%.2f", novoPreco) + "\n"
                    + "Quantidade: " + novaQtd;

            if (confirmar(resumo)) {
                unidades[i] = novaUnidade;
                precos[i] = novoPreco;
                quantidades[i] = novaQtd;
                sucesso("Produto alterado com sucesso!");
            } else {
                aviso("Alteracao cancelada.");
            }

        } while (confirmar("Deseja fazer nova alteracao?"));
    }

    static void consulta() {
        do {
            String nome = input("CONSULTA DE PRODUTO\n\nNome do produto:");
            if (nome == null) {
                return;
            }
            int i = buscar(nome.trim());

            if (i == -1) {
                erro("Produto nao encontrado!");
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "DADOS DO PRODUTO\n\n"
                        + "Nome      : " + nomes[i] + "\n"
                        + "Unidade   : " + unidades[i] + "\n"
                        + "Preco     : R$ " + String.format("%.2f", precos[i]) + "\n"
                        + "Quantidade: " + quantidades[i],
                        "Consulta",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } while (confirmar("Deseja fazer nova consulta?"));
    }

    static void exclusao() {
        do {
            String nome = input("EXCLUSAO DE PRODUTO\n\nNome do produto:");
            if (nome == null) {
                return;
            }
            int i = buscar(nome.trim());

            if (i == -1) {
                erro("Produto nao encontrado!");
                continue;
            }

            String resumo = "Confirma a exclusao?\n\n"
                    + "Nome      : " + nomes[i] + "\n"
                    + "Unidade   : " + unidades[i] + "\n"
                    + "Preco     : R$ " + String.format("%.2f", precos[i]) + "\n"
                    + "Quantidade: " + quantidades[i];

            if (confirmar(resumo)) {
                for (int j = i; j < total - 1; j++) {
                    nomes[j] = nomes[j + 1];
                    unidades[j] = unidades[j + 1];
                    precos[j] = precos[j + 1];
                    quantidades[j] = quantidades[j + 1];
                }
                total--;
                sucesso("Produto excluido com sucesso!");
            } else {
                aviso("Exclusao cancelada.");
            }

        } while (confirmar("Deseja fazer nova exclusao?"));
    }

    static int buscar(String nome) {
        for (int i = 0; i < total; i++) {
            if (nomes[i].equalsIgnoreCase(nome)) {
                return i;
            }
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
