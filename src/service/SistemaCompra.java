package service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class SistemaCompra {

    private List<Usuario> usuarios;
    private List<Cliente> clientes;
    private List<Empresa> empresas;
    private List<Produto> produtos;
    private List<Produto> carrinho;
    private List<Venda> vendas;

    public SistemaCompra(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas, List<Produto> produtos,
            List<Produto> carrinho, List<Venda> vendas) {
        this.usuarios = usuarios;
        this.clientes = clientes;
        this.empresas = empresas;
        this.produtos = produtos;
        this.carrinho = carrinho;
        this.vendas = vendas;
    }

    public void executar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com seu usuário e senha:");
        System.out.print("Usuário: ");
        String username = sc.next();
        System.out.print("Senha: ");
        String senha = sc.next();

        Usuario usuarioLogado = autenticarUsuario(username, senha);

        if (usuarioLogado != null) {
            menuUsuario(usuarioLogado);
        } else {
            System.out.println("Usuário não encontrado ou senha incorreta");
        }
    }

    private Usuario autenticarUsuario(String username, String senha) {
        List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
                .collect(Collectors.toList());

        if (!usuariosSearch.isEmpty()) {
            Usuario usuarioLogado = usuariosSearch.get(0);

            if (usuarioLogado.getSenha().equals(senha)) {
                return usuarioLogado;
            }
        }
        return null;
    }

    private void menuUsuario(Usuario usuarioLogado) {
        Scanner sc = new Scanner(System.in);

        int escolha;

        do {
            System.out.println("Escolha uma opção para iniciar");
            System.out.println("1 - Realizar compras");
            System.out.println("2 - Ver compras");
            System.out.println("0 - Deslogar");

            escolha = sc.nextInt();

            switch (escolha) {
            case 1:
                OperacoesService.realizarCompras(usuarioLogado, clientes, empresas, produtos, carrinho, vendas);
                break;
            case 2:
                OperacoesService.verCompras(usuarioLogado, vendas);
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida");
            }
        } while (escolha != 0);
    }
}
