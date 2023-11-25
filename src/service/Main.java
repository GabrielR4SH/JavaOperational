package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class Main {

    public static void main(String[] args) {
        List<Usuario> usuarios = Inicializacao.inicializarUsuarios();
        List<Cliente> clientes = Inicializacao.inicializarClientes();
        List<Empresa> empresas = Inicializacao.inicializarEmpresas();
        List<Produto> produtos = Inicializacao.inicializarProdutos();
        List<Produto> carrinho = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();

        SistemaCompra sistema = new SistemaCompra(usuarios, clientes, empresas, produtos, carrinho, vendas);
        sistema.executar();
    }
}
	