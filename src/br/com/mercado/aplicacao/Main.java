package br.com.mercado.aplicacao;
import br.com.mercado.dao.ClienteDAO;
import br.com.mercado.model.Cliente;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu Principal ###\n");
            System.out.println("[1] Cliente");
            System.out.println("[2] Funcionário");
            System.out.println("[0] Sair");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuCliente();
            } else if (opcao.equals("2")) {
                menuFuncionario();
            }
        }

        scanner.close();
    }

    public static void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu Funcionário ###\n");
            System.out.println("[1] Pesquisar Produto");
            System.out.println("[2] Relatórios");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuDePesquisaDeProdutoDoFuncionario();
            }
        }

        // scanner.close();
    }

    public static void menuDePesquisaDeProdutoDoFuncionario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu de Produtos ###\n");
            System.out.println("Pesquisar por:");
            System.out.println("[1] Nome");
            System.out.println("[2] Faixa de preço");
            System.out.println("[3] Categoria");
            System.out.println("[4] Cidade");
            System.out.println("[5] Estoque");
            System.out.println("[6] Todos");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            }
        }

        // scanner.close();
    }

    public static void menuCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu Cliente ###\n");
            System.out.println("[1] Pesquisar Produto");
            System.out.println("[2] Login");
            System.out.println("[3] Cadastro");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuDePesquisaDeProdutoDoCliente();
            } else if (opcao.equals("2")) {
                loginDoCliente();
            }
        }

        // scanner.close();
    }

    public static void menuDePesquisaDeProdutoDoCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu de Produtos ###\n");
            System.out.println("Pesquisar por:");
            System.out.println("[1] Nome");
            System.out.println("[2] Faixa de preço");
            System.out.println("[3] Categoria");
            System.out.println("[4] Cidade");
            System.out.println("[5] Todos");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            }
        }

        // scanner.close();
    }

    public static void loginDoCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Login ###\n");
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            if (!cpf.isEmpty()) {
                menuDoClienteLogado();
                break;
            }
        }

        // scanner.close();
    }

    public static void menuDoClienteLogado() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Menu Cliente [LOGADO] ###\n");
            System.out.println("[1] Dados Cadastrais");
            System.out.println("[2] Pedidos Anteriores");
            System.out.println("[3] Pesquisar Produto");
            System.out.println("[4] Carrinho de Compras");
            System.out.println("[5] Finalizar Compra");
            System.out.println("[0] Logout");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuDePesquisaDeProdutoDoCliente();
            } else if (opcao.equals("4")) {
                carrinhoDeCompras();
            } else if (opcao.equals("5")) {
                finalizarCompra();
            }
        }

        // scanner.close();
    }

    public static void carrinhoDeCompras() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Carrinho de Compras ###\n");
            System.out.println("[1] Adicionar Produto");
            System.out.println("[2] Listar Produtos");
            System.out.println("[3] Remover Produto");
            System.out.println("[4] Alterar Quantidade");
            System.out.println("[5] Finalizar Compra"); // Tem que ter pelo menos um produto
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("5")) {
                finalizarCompra();
            }
        }

        // scanner.close();
    }

    public static void finalizarCompra() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Finalizar Compra ###\n");
            System.out.println("Vendedores:");
            // Listar vendedores
            System.out.print("\nOpção: ");
            String vendedor = scanner.nextLine();
            System.out.println("\nMétodos de Pagamento:");
            // Listar formas de pagamento
            System.out.print("\nOpção: ");
            String metodoDePagamento = scanner.nextLine();
            System.out.println("\n[1] Confirmar");
            System.out.println("[0] Cancelar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                resumoDaCompra();
                break;
            }
        }

        // scanner.close();
    }

    public static void resumoDaCompra() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Resumo da Compra ###\n");
            System.out.println("Vendedor:");

            // Listar produtos
            System.out.println("Código:");
            System.out.println("Produto:");
            System.out.println("Quantidade:");

            // if produto estoqueInsuficiente:
            System.out.println("!!! Estoque Insuficiente !!!");
            System.out.println("Quantidade em Estoque: ");

            // desconto
            // if desconto:
            //    System.out.println("Desconto:");
            System.out.println("Total: "); // Listar total
            System.out.println("Métodos de Pagamento: "); // Listar formas de pagamento

            //if any estoqueInsuficiente:
            System.out.println("\nSua Compra Tem Produtos Sem Estoque Suficiente!");
            System.out.println("Por favor, Altere o Seu Carrinho.");

            // System.out.println("\n[1] Alterar Carrinho");
            // scanner.nextLine();
            // else:
            System.out.println("\n[1] Confirmar");
            System.out.println("[0] Cancelar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                // cadastra a compra
                System.out.println("\nCompra Realizada com Sucesso!!!\n");
                break;
            }
        }

        // scanner.close();
    }
}


//     public static void printTabela(List<Cliente> clientes) {
//         final int ID_WIDTH = 4;
//         final int NAME_WIDTH = 20;
//         final int PHONE_WIDTH = 15;
//         final int CITY_ID_WIDTH = 4;
//         final int TIME_ID_WIDTH = 4;
//         final int ANIME_ID_WIDTH = 4;

//         if (clientes.size() == 0) {
//             System.out.println("Nenhum cliente cadastrado.");
//             return;
//         }
        
//         String format = "| %-" + ID_WIDTH + "s | %-" + NAME_WIDTH + "s | %-" + PHONE_WIDTH + "s | %-" + CITY_ID_WIDTH + "s | %-" + TIME_ID_WIDTH + "s | %-" + ANIME_ID_WIDTH + "s |%n";
//         System.out.format(format, "ID", "NOME", "TELEFONE", "CIDADE_ID", "TIME_ID", "ANIME_ID");
    
//         for (Cliente c : clientes) {
//             System.out.format(
//                 format,
//                 Integer.toString(c.getId()).substring(0, Math.min(Integer.toString(c.getId()).length(), ID_WIDTH)),
//                 c.getNome().substring(0, Math.min(c.getNome().length(), NAME_WIDTH)),
//                 c.getTelefone().substring(0, Math.min(c.getTelefone().length(), PHONE_WIDTH)),
//                 Integer.toString(c.getCidadeID()).substring(0, Math.min(Integer.toString(c.getCidadeID()).length(), CITY_ID_WIDTH)),
//                 Integer.toString(c.getTimeID()).substring(0, Math.min(Integer.toString(c.getTimeID()).length(), TIME_ID_WIDTH)),
//                 Integer.toString(c.getAnimeID()).substring(0, Math.min(Integer.toString(c.getAnimeID()).length(), ANIME_ID_WIDTH))
//             );
//         }
//     }


//     public static void listarCadastros(ClienteDAO clienteDao) {
//         System.out.println("\n*** Listar Cadastros ***\n");
//         List<Cliente> clientes = clienteDao.listar();
//         printTabela(clientes);
//     }


//     public static void buscarCadastro(ClienteDAO clienteDao, Scanner scn) {
//         System.out.println("\n*** Buscar Cadastro ***\n");
        
//         System.out.println("ID: ");
//         int id = scn.nextInt();
        
//         List<Cliente> clientes = new ArrayList<>();
//         Cliente cliente = clienteDao.buscar(id);

//         if (cliente != null) {
//             clientes.add(cliente);
//             printTabela(clientes);
//         } else {
//             System.out.println("Cliente não encontrado.");
//         }
//     }


//     public static void buscarCadastroPorNome(ClienteDAO clienteDao, Scanner scn){
//         System.out.println("\n*** Buscar pelo nome ***\n");

//         System.out.println("Nome: ");
//         String nome = scn.nextLine();
//         nome = scn.nextLine();

//         List<Cliente> clientes = clienteDao.buscarPorNome(nome);
//         printTabela(clientes);
//     }


//     public static void cadastrarCliente(Cliente cliente, ClienteDAO clienteDao, Scanner scn) {
//         System.out.println("\n*** Novo Cadastro ***\n");

//         System.out.println("Nome: ");
//         String nome = scn.nextLine();
//         nome = scn.nextLine();

//         System.out.println("Telefone: ");
//         String telefone = scn.nextLine();
//         telefone = scn.nextLine();

//         System.out.println("ID da Cidade: ");
//         int cidadeID = scn.nextInt();

//         System.out.println("ID do Time: ");
//         int timeID = scn.nextInt();

//         System.out.println("ID do Anime: ");
//         int animeID = scn.nextInt();

//         cliente.setNome(nome);
//         cliente.setTelefone(telefone);
//         cliente.setCidadeID(cidadeID);
//         cliente.setTimeID(timeID);
//         cliente.setAnimeID(animeID);

//         clienteDao.cadastrar(cliente);

//         System.out.println("Cliente cadastrado com sucesso!");
//     }


//     public static void atualizarCadastro(Cliente cliente, ClienteDAO clienteDao, Scanner scn){
//         System.out.println("\n*** ALterar Cadastro ***\n");

//         System.out.println("ID: ");
//         int id = scn.nextInt();

//         System.out.println("Nome: ");
//         String nome = scn.nextLine();
//         nome = scn.nextLine();

//         System.out.println("Telefone: ");
//         String telefone = scn.nextLine();
//         telefone = scn.nextLine();

//         System.out.println("ID da Cidade: ");
//         int cidadeID = scn.nextInt();

//         System.out.println("ID do Time: ");
//         int timeID = scn.nextInt();

//         System.out.println("ID do Anime: ");
//         int animeID = scn.nextInt();

//         cliente.setId(id);
//         cliente.setNome(nome);
//         cliente.setTelefone(telefone);
//         cliente.setCidadeID(cidadeID);
//         cliente.setTimeID(timeID);
//         cliente.setAnimeID(animeID);

//         clienteDao.atualizar(cliente);

//         System.out.println("Cadastro atualizado.");
//     }


//     public static void deletarCadastro(ClienteDAO clienteDao, Scanner scn){
//         System.out.println("\n*** Deletar Cadastro ***\n");
        
//         System.out.println("ID: ");
//         int id = scn.nextInt();
//         clienteDao.deletar(id);

//         System.out.println("Cadastro deletado.");
//     }

// }
