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
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Telefone: ");
            int telefone = scanner.nextInt();

            System.out.println();
            AnimeDAO.imprimirAnimes(AnimeDAO.getAllAnime());
            System.out.print("Anime Preferido: ");
            int idAnime = scanner.nextInt();

            System.out.println();
            CidadeDAO.imprimirCidade(CidadeDAO.getAllCidade());
            System.out.print("Cidade: ");
            int idCidade = scanner.nextInt();
            
            System.out.println();
            TimeTorcedorDAO.imprimirTimeTorcedor(TimeTorcedorDAO.getAllTimeTorcedor());
            System.out.print("Time Torcedor: ");
            int idTimeTorcedor = scanner.nextInt();
            scanner.nextLine();
            
            Cliente cliente = new Cliente(nome,telefone, idAnime, idCidade, idTimeTorcedor);
            idCliente = ClienteDAO.save(cliente);

            if (idCliente==0){
                System.out.print("Erro ao cadastrar, tente novamente!\n\n");
            } else {
                System.out.println("\nCadastrado realizado ! ID do Cliente: " + idCliente + "\n");
                menuDoClienteLogado();
                break;
            }
        }
    }

    public static void loginDoCliente() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("ID do Cliente: ");
            idCliente = scanner.nextInt();
            idCliente = ClienteDAO.getClientId(idCliente);

            System.out.println();
            scanner.nextLine();
            if (idCliente==0){
                System.out.print("Cliente não encontrado, tente novamente!\n\n");
            } else {
                System.out.print("Cliente encontrado!\n\n");
                menuDoClienteLogado();
                break;
            }
        }
    }

    public static void menuDoClienteLogado() {
        Scanner scanner = new Scanner(System.in);
        List<ItemVenda> carrinho =  new ArrayList<>();

        while (true) {
            System.out.println("### Menu Cliente ["+ idCliente +"] ###\n");
            System.out.println("[1] Dados Cadastrais"); // Falta implementar
            System.out.println("[2] Pedidos Anteriores"); // Falta implementar
            System.out.println("[3] Pesquisar Produto"); 
            System.out.println("[4] Carrinho de Compras"); 
            System.out.println("[0] Logout");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                idCliente = 0;
                break;
            } else if (opcao.equals("1")) {
                ClienteViewDAO.imprimirClientes(ClienteViewDAO.getClientesById(idCliente), "Dados Cadastrais");
            } else if (opcao.equals("2")) {
                VendaDAO.imprimirVendas(VendaDAO.getForCliente(idCliente), "Pedidos Anteriores");
            } else if (opcao.equals("3")) {
                menuDePesquisaDeProdutoDoCliente();
            } else if (opcao.equals("4")) {
                carrinhoDeCompras(carrinho);
            }
        }
    }

    public static void carrinhoDeCompras(List<ItemVenda> carrinho) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Carrinho de Compras ###\n");
            System.out.println("[1] Adicionar Produto");
            System.out.println("[2] Listar Produtos");
            System.out.println("[3] Remover Produto");
            System.out.println("[4] Alterar Produto");
            System.out.println("[5] Finalizar Compra"); // Tem que ter pelo menos um produto
            System.out.println("[0] Voltar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();
            
            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                ProdutoViewDAO.imprimirProdutos(ProdutoViewDAO.getAllProdutoView(), "Lista de produtos");
                System.out.print("Adicionar Produto de ID: ");
                int produto = scanner.nextInt();
                System.out.print("Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                
                ItemVenda itemCarrinho = new ItemVenda(produto, quantidade, ProdutoViewDAO.getForIdProdutoView(produto).getPreco());
                System.out.println("\nProduto adicionado ao carrinho!\n");
                carrinho.add(itemCarrinho);

            } else if (opcao.equals("2")) {

                ItemVendaDAO.ImprimeCarrinho(carrinho, "Carrinho de Compras");

            } else if (opcao.equals("3")) {

                System.out.print("Remover Produto de ID: ");
                int produto = scanner.nextInt();
                scanner.nextLine();

                int encontrado = 0;
                for(int i = 0; i < carrinho.size(); i++){
                    if(carrinho.get(i).getIdProduto() == produto){
                        carrinho.remove(i);
                        encontrado = 1;
                        break;
                    }
                }
                if(encontrado == 1){
                    System.out.println("\nProduto removido do carrinho!\n");
                } else{
                    System.out.println("\nProduto não encontrado no carrinho!\n");
                }

            } else if (opcao.equals("4")) {

                ItemVendaDAO.ImprimeCarrinho(carrinho, "Carrinho de Compras");

                System.out.print("Alterar Produto de ID: ");
                int produto = scanner.nextInt();
                System.out.print("Nova Quantidade: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();
                
                int encontrado = 0;
                for(int i = 0; i < carrinho.size(); i++){
                    if(carrinho.get(i).getIdProduto() == produto){
                        carrinho.get(i).setQuantidade(quantidade);
                        encontrado = 1;
                    }
                }
                if(encontrado == 1){
                    System.out.println("\nProduto alterado!\n");
                } else{
                    System.out.println("\nProduto não encontrado!\n");
                }

            } else if (opcao.equals("5")) {
                if(carrinho.isEmpty()){
                    System.out.println("Carrinho vazio! Insira pelo menos um produto ao carrinho para finalizar a compra.\n");
                }else{
                    finalizarCompra(carrinho);
                    break;
                }
            }
        }
    }

    public static void finalizarCompra(List<ItemVenda> carrinho) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("### Finalizar Compra ###\n");
            VendedorDAO.imprimirVendedores(VendedorDAO.getAllVendedor(), "Vendedores");
            
            System.out.print("ID do Vendedor: ");
            int idVendedor = scanner.nextInt();
            System.out.println();

            System.out.println("Método de Pagamento:");
            System.out.println("[1] Dinheiro");
            System.out.println("[2] Cartão"); 
            System.out.println("[3] Boleto");
            System.out.println("[4] PIX");
            System.out.println("[5] Berries");
            System.out.print("\nOpção: ");
            int idFormaPagamento = scanner.nextInt();
            scanner.nextLine();
            
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
        if(cliente.isPossuiDesconto()){
            desconto = 0.15;
        }else{
            desconto = 0;
        }

        while (true) {
            System.out.println("\n### Resumo da Compra ###\n");
            System.out.println("Vendedor: " + VendedorDAO.getVendedorById(idVendedor).getNome());

            System.out.println("\nCarrinho de Produtos:");
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

            double total = ItemVendaDAO.getTotal(carrinho);
            System.out.println("\nTotal: R$ " + total); // Listar total
            if (desconto > 0) {
                System.out.println("Desconto: " + desconto * 100 + "%"); // Listar desconto
                total = total * (1 - desconto);
                System.out.println("Total com desconto: R$ " + total); // Listar total com desconto
            }
            System.out.println("Método de Pagamento: " + idFormaPagamento); // Listar formas de pagamento

            if(estoqueInsuficiente){
                System.out.println("\nSua compra tem produtos sem estoque suficiente!");
                System.out.println("Por favor, altere o seu carrinho.");
                System.out.println("\n[1] Alterar Carrinho");
                scanner.nextLine();
                break;
            }
            
            System.out.println("\n[1] Confirmar");
            System.out.println("[0] Cancelar");
            System.out.print("\nOpção: ");
            String opcao = scanner.nextLine();
            System.out.println();

            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                LocalDate dataAtual = LocalDate.now();
                Date dataVenda = Date.valueOf(dataAtual);
                Venda venda = new Venda(idCliente , idVendedor, idFormaPagamento, dataVenda, total, desconto, true);
                int idVenda = VendaDAO.save(venda);
                for (int i = 0; i < carrinho.size(); i++) {
                    ItemVendaDAO.save(carrinho.get(i), idVenda);
                }
                System.out.println("Compra Realizada com Sucesso!!!\n");
                carrinho.clear();
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