package br.com.mercado.aplicacao;

import br.com.mercado.dao.*;
import br.com.mercado.model.*;
import br.com.mercado.service.MainService;

import java.sql.Date;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int idCliente = 0;
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Menu Principal ###\n");
            System.out.println("[1] Cliente");
            System.out.println("[2] Funcionário");
            System.out.println("[0] Sair");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

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
    
    public static void menuCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Menu Cliente ###\n");
            System.out.println("[1] Pesquisar Produto");
            System.out.println("[2] Login");
            System.out.println("[3] Cadastro");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuDePesquisaDeProdutoDoCliente();
            } else if (opcao.equals("2")) {
                loginDoCliente();
            } else if (opcao.equals("3")) {
                cadastroDoCliente();
            }
        }
    }

    public static void menuDePesquisaDeProdutoDoCliente() {
        Scanner scanner = new Scanner(System.in);
        String titulo = "Produtos";

        while (true) {
            System.out.println("### Menu de Produtos ###\n");
            System.out.println("Pesquisar por:");
            System.out.println("[1] Nome");
            System.out.println("[2] Faixa de preço");
            System.out.println("[3] Categoria");
            System.out.println("[4] Cidade do Fabricante");
            System.out.println("[5] Todos");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if(opcao.equals("1")){
                System.out.print("Nome do Produto: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForNameProdutoView(nome), titulo);
            } else if (opcao.equals("2")){
                System.out.print("Preço Mínimo: ");
                double min = scanner.nextDouble();
                System.out.print("Preço Máximo: ");
                double max = scanner.nextDouble();
                System.out.println();
                scanner.nextLine();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForPriceProdutoViewBy(min, max), titulo);
            } else if (opcao.equals("3")){
                System.out.print("Categoria do Produto: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForCategoryProdutoView(nome), titulo);
            } else if (opcao.equals("4")){
                System.out.print("Cidade do Fabricante: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForCityProdutoView(nome), titulo);
            } else if (opcao.equals("5")){
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getAllProdutoView(), titulo);
            }
        }
    }
    public static void cadastroDoCliente() {
        while(true){
            System.out.println("Digite os seu dados para o cadastro");
            String nome = MainService.perguntaString("Nome: ");
            int telefone = MainService.perguntaNumeroInt("Número de telefone: ");
            AnimeDAO.imprimirAnimes(AnimeDAO.getAllAnime());
            int idAnime = MainService.perguntaNumeroInt("Id do anime preferido: ");
            CidadeDAO.imprimirCidade(CidadeDAO.getAllCidade());
            int idCidade = MainService.perguntaNumeroInt("Id da cidade natal: ");
            TimeTorcedorDAO.imprimirTimeTorcedor(TimeTorcedorDAO.getAllTimeTorcedor());
            int idTimeTorcedor = MainService.perguntaNumeroInt("Id do time torcedor: ");

            Cliente cliente = new Cliente(nome,telefone, idAnime, idCidade, idTimeTorcedor);
            idCliente = ClienteDAO.save(cliente);

            System.out.println("Cadastrado com sucesso!, seu ID: " + idCliente);
            menuDoClienteLogado();
        }
    }

    public static void loginDoCliente() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            while(idCliente==0){
                idCliente = MainService.perguntaNumeroInt("ID do Cliente: ");
                idCliente = ClienteDAO.getClientId(idCliente);
                if (idCliente==0){
                    System.out.print("\nCliente não encontrado, tente novamente!\n\n");
                } else {
                    System.out.print("\nCliente encontrado!\n\n");
                }
            }
            menuDoClienteLogado();
        }
    }

    public static void menuDoClienteLogado() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Menu Cliente [LOGADO] ###\n");
            System.out.println("[1] Dados Cadastrais"); // Falta implementar
            System.out.println("[2] Pedidos Anteriores"); // Falta implementar
            System.out.println("[3] Pesquisar Produto"); 
            System.out.println("[4] Carrinho de Compras"); 
            System.out.println("[0] Logout");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("3")) {
                menuDePesquisaDeProdutoDoCliente();
            } else if (opcao.equals("4")) {
                carrinhoDeCompras();
            }
        }
    }

    public static void carrinhoDeCompras() {
        Scanner scanner = new Scanner(System.in);
        List<ItemVenda> carrinho =  new ArrayList<>();

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
            } else if (opcao.equals("1")) {

                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getAllProdutoView(), "Lista de produtos");
                int produto = MainService.perguntaNumeroInt("Digite o código do produto a ser adicionado: ");
                int quantidade = MainService.perguntaNumeroInt("Digite a quantidade: ");
                ItemVenda itemCarrinho = new ItemVenda(produto, quantidade, ProdutoViewDAO.getForIdProdutoView(produto).getPreco());
                carrinho.add(itemCarrinho);

            } else if (opcao.equals("2")) {

                System.out.println("Produtos no carrinho:");
                ItemVendaDAO.ImprimeCarrinho(carrinho);

            } else if (opcao.equals("3")) {

                System.out.println("Qual produto deseja remover?");
                ItemVendaDAO.ImprimeCarrinho(carrinho);
                int produto = MainService.perguntaNumeroInt("Digite o código do produto a ser removido: ");
                for(int i = 0; i < carrinho.size(); i++){
                    if(carrinho.get(i).getIdProduto() == produto){
                        carrinho.remove(i);
                    }
                }

            } else if (opcao.equals("4")) {

                ItemVendaDAO.ImprimeCarrinho(carrinho);

                int produto = MainService.perguntaNumeroInt("Digite o código do produto a ser alterado: ");
                int quantidade = MainService.perguntaNumeroInt("Digite a nova quantidade: ");

                for(int i = 0; i < carrinho.size(); i++){
                    if(carrinho.get(i).getIdProduto() == produto){
                        carrinho.get(i).setQuantidade(quantidade);
                    }
                }

            } else if (opcao.equals("5")) {
                if(carrinho.size() > 0){
                    finalizarCompra(carrinho);
                }else{
                    System.out.println("Carrinho vazio! Insira ao menos um produto ao carrinho para finalizar a compra.");
                }
            }
        }
    }

    public static void finalizarCompra(List<ItemVenda> carrinho) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n### Finalizar Compra ###\n");
            VendedorDAO.imprimirVendedores(VendedorDAO.getAllVendedor(), "Lista de vendedores");

            int idVendedor = MainService.perguntaNumeroInt("Insira o ID do vendedor que lhe auxiliou: ");
            System.out.println("Métodos de pagamento");
            System.out.println("[1] Dinheiro");
            System.out.println("[2] Cartão"); 
            System.out.println("[3] Boleto");
            System.out.println("[4] PIX");
            System.out.println("[5] Berries");
            int idFormaPagamento = MainService.perguntaNumeroInt("Insira o método de pagamento: ");
            System.out.println("\n[1] Confirmar");
            System.out.println("[0] Cancelar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                resumoDaCompra(carrinho, idVendedor, idFormaPagamento);
                break;
            }
        }
    }

    public static void resumoDaCompra(List<ItemVenda> carrinho, int idVendedor, int idFormaPagamento) {
        Scanner scanner = new Scanner(System.in);
        double desconto;
        Cliente cliente = ClienteDAO.getClienteById(idCliente);
        if(ClienteDAO.getClienteById(idCliente).isPossuiDesconto()){
            desconto = 0.15;
        }else{
            desconto = 0;
        }

        while (true) {
            System.out.println("\n### Resumo da Compra ###\n");
            System.out.println("Vendedor:" + VendedorDAO.getVendedorById(idVendedor).getNome());

            System.out.println("Produtos no carrinho:");
            for(int i = 0; i < carrinho.size(); i++){
                System.out.println("[" + i + "] " + carrinho.get(i).getIdProduto() + " - " + ProdutoViewDAO.getForIdProdutoView(carrinho.get(i).getIdProduto()).getNome() + " - " + carrinho.get(i).getQuantidade());
            }
            
            boolean estoqueInsuficiente = false;
            for(int i = 0; i < carrinho.size(); i++){
                if(carrinho.get(i).getQuantidade() >= ProdutoViewDAO.getForIdProdutoView(carrinho.get(i).getIdProduto()).getQuantidade()){
                    System.out.println("!!! Estoque Insuficiente do Produto" + ProdutoViewDAO.getForIdProdutoView(carrinho.get(i).getIdProduto()).getNome() + "!!!");
                    System.out.println("Quantidade em Estoque: " + ProdutoViewDAO.getForIdProdutoView(carrinho.get(i).getIdProduto()).getQuantidade());
                    estoqueInsuficiente = true;
                }
            }

            System.out.println("Total: R$ " + ItemVendaDAO.getTotal(carrinho)); // Listar total
            System.out.println("Desconto: " + desconto * 100 + "%"); // Listar desconto
            System.out.println("Total após desconto: R$ " + ItemVendaDAO.getTotal(carrinho) * (1 - desconto)); // Listar total com desconto
            System.out.println("Método de Pagamento: " + idFormaPagamento); // Listar formas de pagamento

            if(estoqueInsuficiente){
                System.out.println("\nSua compra tem produtos sem estoque suficiente!");
                System.out.println("Por favor, altere o seu carrinho.");
                System.out.println("\n[1] Alterar Carrinho");
                scanner.nextLine();
                carrinhoDeCompras();
            }
            
            System.out.println("\n[1] Confirmar");
            System.out.println("[0] Cancelar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                LocalDate dataAtual = LocalDate.now();
                Date dataVenda = Date.valueOf(dataAtual);
                Venda venda = new Venda(idCliente , idVendedor, idFormaPagamento, dataVenda, ItemVendaDAO.getTotal(carrinho), desconto, true);
                VendaDAO.save(venda);
                for (int i = 0; i < carrinho.size(); i++) {
                    ItemVendaDAO.save(carrinho.get(i), venda.getId());
                }
                System.out.println("\nCompra Realizada com Sucesso!!!\n");
                break;
            }
        }
    }

    public static void menuFuncionario() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Menu Funcionário ###\n");
            System.out.println("[1] Pesquisar Produto");
            System.out.println("[2] Relatórios"); // ! To implement
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuDePesquisaDeProdutoDoFuncionario();
            }
        }
    }

    public static void menuDePesquisaDeProdutoDoFuncionario() {
        Scanner scanner = new Scanner(System.in);
        String titulo = "Produtos";

        while (true) {
            System.out.println("### Menu de Produtos ###\n");
            System.out.println("Pesquisar por:");
            System.out.println("[1] Nome");
            System.out.println("[2] Faixa de preço");
            System.out.println("[3] Categoria");
            System.out.println("[4] Cidade do Fabricante");
            System.out.println("[5] Estoque");
            System.out.println("[6] Todos");
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if(opcao.equals("1")){
                System.out.print("Nome do Produto: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForNameProdutoView(nome), titulo);
            } else if (opcao.equals("2")){
                System.out.print("Preço Mínimo: ");
                double min = scanner.nextDouble();
                System.out.print("Preço Máximo: ");
                double max = scanner.nextDouble();
                System.out.println();
                scanner.nextLine();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForPriceProdutoViewBy(min, max), titulo);
            } else if (opcao.equals("3")){
                System.out.print("Categoria do Produto: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForCategoryProdutoView(nome), titulo);
            } else if (opcao.equals("4")){
                System.out.print("Cidade do Fabricante: ");
                String nome = scanner.nextLine();
                System.out.println();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForCityProdutoView(nome), titulo);
            } else if (opcao.equals("5")){
                System.out.print("Quantidade Máxima: ");
                int quantidade = scanner.nextInt();
                System.out.println();
                scanner.nextLine();
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getForAmountProdutoView(quantidade), titulo);
            } else if (opcao.equals("6")){
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getAllProdutoView(), titulo);
            }
        }
    }

}