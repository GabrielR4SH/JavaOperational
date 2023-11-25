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
        List<Produto> produtos = Arrays.asList(produto, produto2);

        SistemaCompra sistema = new SistemaCompra(usuarios, clientes, empresas, produtos, carrinho, vendas);
        sistema.executar();
    }
}
