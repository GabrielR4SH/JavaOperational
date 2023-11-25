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

                if (produtoEscolhido != null && produtoEscolhido.getEmpresa().getId() == escolhaEmpresa) {
                    carrinho.add(produtoEscolhido);
                    System.out.println("Produto adicionado ao carrinho.");
                } else {
                    System.out.println("Produto não encontrado ou não disponível nesta empresa.");
                }
            }
        } while (escolhaProduto.get() != 0);

        System.out.println("Resumo do carrinho:");
        carrinho.forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));

        finalizarCompra(usuarioLogado, empresas, carrinho, vendas);
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

        double taxaComissao = empresaEscolhida.getTaxa();
        double comissaoSistema = valorTotal * taxaComissao;
        double valorLiquido = valorTotal - comissaoSistema;

        Venda venda = new Venda(vendas.size() + 1, carrinho, valorLiquido, comissaoSistema, empresaEscolhida,
                usuarioLogado.getCliente());
        vendas.add(venda);

        empresaEscolhida.setSaldo(empresaEscolhida.getSaldo() + valorLiquido);
        empresaEscolhida.setTaxaComissaoSistema(comissaoSistema);

        carrinho.clear();

        System.out.println("Compra realizada com sucesso!");
        System.out.println("Resumo da compra:");
        System.out.println("Valor total da compra: R$" + valorTotal);
        System.out.println("Taxa de comissão da empresa: " + taxaComissao);
        System.out.println("Comissão do sistema: R$" + comissaoSistema);
    }

    // Restante do código permanece o mesmo
	


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
    
    public static void verVendasEmpresa(Usuario usuarioLogado, List<Venda> vendas) {
        if (usuarioLogado.getEmpresa() != null) {
            List<Venda> vendasEmpresa = vendas.stream()
                    .filter(venda -> venda.getEmpresa().getId() == usuarioLogado.getEmpresa().getId())
                    .collect(Collectors.toList());

            System.out.println("Vendas realizadas pela empresa:");
            vendasEmpresa.forEach(venda -> {
                System.out.println("Código da venda: " + venda.getCódigo());
                System.out.println("Produtos vendidos:");
                venda.getItens().forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));
                System.out.println("Valor total: R$" + venda.getValor());
                System.out.println("Taxa de comissão do sistema: " + venda.getComissaoSistema());
                System.out.println("------------------------------");
            });
        } else {
            System.out.println("Usuário não é uma empresa. Acesso negado.");
        }
    }

    public static void verProdutosEmpresa(Usuario usuarioLogado, List<Produto> produtos) {
        if (usuarioLogado.getEmpresa() != null) {
            List<Produto> produtosEmpresa = produtos.stream()
                    .filter(produto -> produto.getEmpresa().getId() == usuarioLogado.getEmpresa().getId())
                    .collect(Collectors.toList());

            System.out.println("Produtos da empresa:");
            produtosEmpresa.forEach(produto -> System.out.println(produto.getId() + " - " + produto.getNome()));
        } else {
            System.out.println("Usuário não é uma empresa. Acesso negado.");
        }
    }

}
