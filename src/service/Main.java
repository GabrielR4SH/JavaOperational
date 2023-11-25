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
		// SIMULANDO BANCO DE DADOS
		List<Produto> carrinho = new ArrayList<>();
		List<Venda> vendas = new ArrayList<>();

		Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
		Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
		Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

		Produto produto = new Produto(1, "Pão Francês", 5, 3.50, empresa);
		Produto produto2 = new Produto(2, "Coturno", 10, 400.0, empresa2);
		// Adicione mais produtos conforme necessário

		Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
		Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

		Usuario usuario1 = new Usuario("admin", "1234", null, null);
		Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
		Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
		Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
		Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
		Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);

		List<Usuario> usuarios = Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6);
		List<Cliente> clientes = Arrays.asList(cliente, cliente2);
		List<Empresa> empresas = Arrays.asList(empresa, empresa2, empresa3);
		List<Produto> produtos = Arrays.asList(produto, produto2); // Adicione mais produtos conforme necessário

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
				menuUsuario(usuarioLogado, clientes, empresas, produtos, carrinho, vendas);
			} else {
				System.out.println("Senha incorreta");
			}
		} else {
			System.out.println("Usuário não encontrado");
		}
	}

	public static void menuUsuario(Usuario usuarioLogado, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
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
