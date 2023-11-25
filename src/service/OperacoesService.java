package service;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import model.Cliente;
import model.Empresa;
import model.Produto;
import model.Usuario;
import model.Venda;

public class OperacoesService {

    public static void realizarCompras(Usuario usuarioLogado, List<Cliente> clientes, List<Empresa> empresas,
                                       List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
        Scanner sc = new Scanner(System.in);
        AtomicInteger escolhaProduto = new AtomicInteger(0);

        System.out.println("Empresas disponíveis para compra:");
        empresas.forEach(empresa -> System.out.println(empresa.getId() + " - " + empresa.getNome()));

        System.out.print("Escolha a empresa para realizar a compra: ");
        int escolhaEmpresa = sc.nextInt();

        System.out.println("Produtos disponíveis para compra:");
        produtos.stream().filter(produto -> produto.getEmpresa().getId() == escolhaEmpresa)
                .forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));

        do {
            System.out.print("Escolha um produto para adicionar ao carrinho (0 para finalizar): ");
            escolhaProduto.set(sc.nextInt());

            if (escolhaProduto.get() != 0) {
                Produto produtoEscolhido = produtos.stream().filter(produto -> produto.getId() == escolhaProduto.get())
                        .findFirst().orElse(null);

                if (produtoEscolhido != null) {
                    carrinho.add(produtoEscolhido);
                    System.out.println("Produto adicionado ao carrinho.");
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } while (escolhaProduto.get() != 0);

        System.out.println("Resumo do carrinho:");
        carrinho.forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));

        // Implemente o restante da lógica conforme necessário
    }

    public static void finalizarCompra(Usuario usuarioLogado, List<Empresa> empresas, List<Produto> carrinho,
                                       List<Venda> vendas) {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio. Nenhuma compra realizada.");
            return;
        }

        System.out.println("Resumo do carrinho:");
        carrinho.forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));

        double valorTotal = carrinho.stream().mapToDouble(Produto::getPreco).sum();
        System.out.println("Valor total da compra: R$" + valorTotal);

        System.out.println("Escolha a empresa para finalizar a compra:");
        empresas.forEach(empresa -> System.out.println(empresa.getId() + " - " + empresa.getNome()));
        Scanner sc = new Scanner(System.in);
        System.out.print("Escolha a empresa: ");
        int escolhaEmpresa = sc.nextInt();

        Empresa empresaEscolhida = empresas.stream().filter(empresa -> empresa.getId() == escolhaEmpresa).findFirst()
                .orElse(null);

        if (empresaEscolhida == null) {
            System.out.println("Empresa não encontrada. Compra cancelada.");
            return;
        }

        Venda venda = new Venda(vendas.size() + 1, carrinho, valorTotal, 0.0, empresaEscolhida,
                usuarioLogado.getCliente());
        vendas.add(venda);

        empresaEscolhida.setSaldo(empresaEscolhida.getSaldo() + valorTotal);

        carrinho.clear();

        System.out.println("Compra realizada com sucesso!");
    }

    public static void verCompras(Usuario usuarioLogado, List<Venda> vendas) {
        List<Venda> comprasCliente = vendas.stream()
                .filter(venda -> venda.getCliente().getUsername().equals(usuarioLogado.getUsername()))
                .collect(Collectors.toList());

        System.out.println("Compras realizadas pelo cliente:");
        comprasCliente.forEach(venda -> {
            System.out.println("Código da venda: " + venda.getCódigo());
            System.out.println("Produtos comprados:");
            venda.getItens().forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));
            System.out.println("Valor total: R$" + venda.getValor());
            System.out.println("Empresa: " + venda.getEmpresa().getNome());
            System.out.println("------------------------------");
        });

        if (comprasCliente.isEmpty()) {
            System.out.println("Nenhuma compra realizada.");
        }
    }
}
