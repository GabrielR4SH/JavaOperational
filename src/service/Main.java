package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class Main {

    public static void main(String[] args) {
        List<Produto> carrinho = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        List<Usuario> usuarios = Inicializacao.inicializarUsuarios();
        List<Cliente> clientes = Inicializacao.inicializarClientes();
        List<Empresa> empresas = Inicializacao.inicializarEmpresas();
        List<Produto> produtos = Inicializacao.inicializarProdutos();

        executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
    }

    public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
            List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com seu usuário e senha:");
        System.out.print("Usuário: ");
        String username = sc.next();
        System.out.print("Senha: ");
        String senha = sc.next();

        List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
                .collect(Collectors.toList());

        if (!usuariosSearch.isEmpty()) {
            Usuario usuarioLogado = usuariosSearch.get(0);

            if (usuarioLogado.getSenha().equals(senha)) {
                if (usuarioLogado.getUsername().equals("admin")) {
                    menuAdmin(usuarios, clientes, empresas, produtos, carrinho, vendas);
                } else {
                    menuUsuario(usuarioLogado, usuarios, clientes, empresas, produtos, carrinho, vendas);
                }
            } else {
                System.out.println("Senha incorreta");
            }
        } else {
            System.out.println("Usuário não encontrado");
        }
    }

	
    public static void menuUsuario(Usuario usuarioLogado, List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
            List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {	
        Scanner sc = new Scanner(System.in);

        int escolha;

        do {
            System.out.println("Escolha uma opção para iniciar");
            if (usuarioLogado.getCliente() != null) {
                System.out.println("1 - Realizar compras");
                System.out.println("2 - Ver compras");
            }
            if (usuarioLogado.getEmpresa() != null) {
                System.out.println("3 - Ver vendas da empresa");
                System.out.println("4 - Ver produtos da empresa");
            }
            System.out.println("0 - Deslogar");

            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    if (usuarioLogado.getCliente() != null) {
                        OperacoesService.realizarCompras(usuarioLogado, clientes, empresas, produtos, carrinho, vendas);
                    } else {
                        System.out.println("Opção inválida para este tipo de usuário");
                    }
                    break;
                case 2:
                    if (usuarioLogado.getCliente() != null) {
                        OperacoesService.verCompras(usuarioLogado, vendas);
                    } else {
                        System.out.println("Opção inválida para este tipo de usuário");
                    }
                    break;
                case 3:
                    if (usuarioLogado.getEmpresa() != null) {
                        OperacoesService.verVendasEmpresa(usuarioLogado, vendas);
                    } else {
                        System.out.println("Opção inválida para este tipo de usuário");
                    }
                    break;
                case 4:
                    if (usuarioLogado.getEmpresa() != null) {
                        OperacoesService.verProdutosEmpresa(usuarioLogado, produtos);
                    } else {
                        System.out.println("Opção inválida para este tipo de usuário");
                    }
                    break;
                case 0:
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);  // Voltar para a tela de login
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (escolha != 0);
    }
    
    public static void menuAdmin(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
            List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
        Scanner sc = new Scanner(System.in);

        int escolha;

        do {
            System.out.println("Escolha uma opção para administradores");
            System.out.println("1 - Ver todas as vendas");
            System.out.println("2 - Ver todos os produtos");
            System.out.println("0 - Deslogar");

            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    OperacoesService.verTodasVendas(vendas);
                    break;
                case 2:
                    OperacoesService.verTodosProdutos(produtos);
                    break;
                case 0:
                    executar(usuarios, clientes, empresas, produtos, carrinho, vendas);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (escolha != 0);
    }

	
}
