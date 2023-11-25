package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;

public class Inicializacao {

    public static List<Usuario> inicializarUsuarios() {
        Usuario usuario1 = new Usuario("admin", "1234", null, null);
        Usuario usuario2 = new Usuario("empresa", "1234", null, inicializarEmpresas().get(0));
        Usuario usuario3 = new Usuario("cliente", "1234", inicializarClientes().get(0), null);
        Usuario usuario4 = new Usuario("cliente2", "1234", inicializarClientes().get(1), null);
        Usuario usuario5 = new Usuario("empresa2", "1234", null, inicializarEmpresas().get(1));
        Usuario usuario6 = new Usuario("empresa3", "1234", null, inicializarEmpresas().get(2));

        return Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5, usuario6);
    }

    public static List<Cliente> inicializarClientes() {
        Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
        Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

        return Arrays.asList(cliente, cliente2);
    }

    public static List<Empresa> inicializarEmpresas() {
        Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
        Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
        Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

        return Arrays.asList(empresa, empresa2, empresa3);
    }

    public static List<Produto> inicializarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        // Produtos para SafeWay Padaria
        Empresa padaria = inicializarEmpresas().get(0);
        produtos.add(new Produto(1, "Pão Francês", 5, 3.50, padaria));
        produtos.add(new Produto(2, "Rosquinha", 10, 2.50, padaria));
        produtos.add(new Produto(3, "Leite", 8, 4.00, padaria));
        produtos.add(new Produto(4, "Pão Doce", 15, 1.80, padaria));

        // Produtos para Level Varejo
        Empresa varejo = inicializarEmpresas().get(1);
        produtos.add(new Produto(5, "Coturno", 10, 400.0, varejo));
        produtos.add(new Produto(6, "Jaqueta", 5, 25.50, varejo));
        produtos.add(new Produto(7, "Gravata", 3, 30.00, varejo));
        produtos.add(new Produto(8, "Sapato Social", 8, 15.75, varejo));
        

        // Produtos para SafeWay Restaurante
        Empresa restaurante = inicializarEmpresas().get(2);
        produtos.add(new Produto(9, "Omelete", 10, 15.00, restaurante));
        produtos.add(new Produto(10, "Frango Grelhado", 12, 18.50, restaurante));
        produtos.add(new Produto(11, "Macarrão", 8, 22.75, restaurante));
        produtos.add(new Produto(12, "Lasanha", 15, 12.50, restaurante));
   

        return produtos;
    }
}
