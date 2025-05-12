package com.helio.ui;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaFim;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaMeio;
import static com.helio.utilities.Validacao.validarNome;
import static com.helio.utilities.Validacao.validarIdade;
import static com.helio.utilities.Validacao.validarEndereco;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.utilities.Validacao.validarCpf;
import static com.helio.dao.PessoaDao.criarPersistenciaPessoa;
import static com.helio.dao.PessoaDao.removerPessoa;
import static com.helio.dao.PessoaDao.atualizarPessoa;
import static com.helio.dao.PessoaDao.buscarPessoa;
import static com.helio.dao.PessoaDao.buscarTodasAsPessoas;
import static com.helio.ui.InternoSubMenuPessoa.internoSubMenuPessoa;

import java.util.Scanner;

import com.helio.models.Pessoa;



public class SubmenuPessoa {

    public static void submenuPessoa(Scanner scanner) {
        int opcao = -1;      
        do {
            limparTela();
            linhaPadronizadaTitulo("SUBMENU PESSOA");
            System.out.println("    1. Cadastrar Pessoa");
            System.out.println("    2. Buscar uma Pessoa");
            System.out.println("    3. Listar Todas as Pessoas");
            System.out.println("    4. Remover Pessoa");
            System.out.println("    5. Atualizar Pessoa\n");

            System.out.println("0. Voltar ao Menu Principal");
            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            String nome;
            int idade = -1;
            String endereco;
            String cpf;

            switch (opcao) {
                case 1:                    
                    do {
                        limparTela();
                        linhaPadronizadaTitulo("CADASTRAR PESSOA");
                        System.out.println("Digite o nome da pessoa: ");
                        nome = scanner.nextLine();
                        try {
                            validarNome(nome);
                            break; 
                        } catch (IllegalArgumentException e) {
                            limparTela();
                            System.out.println(e.getMessage());
                            pausarExecucao(scanner);
                        }
                    } while (true);


                    do {
                        try {
                            limparTela();
                            linhaPadronizadaTitulo("CADASTRAR PESSOA");
                            System.out.println("Digite a idade da pessoa: ");
                            idade = scanner.nextInt();
                            scanner.nextLine();
                            validarIdade(idade);
                            break;
                        } catch (IllegalArgumentException e) {
                            limparTela();
                            System.out.println(e.getMessage());
                            pausarExecucao(scanner);
                        }
                        break;
                    } while (true);



                    do {
                        try {
                            limparTela();
                            linhaPadronizadaTitulo("CADASTRAR PESSOA");
                            System.out.println("Digite o endereço da pessoa: ");
                            endereco = scanner.nextLine();
                            validarEndereco(endereco);
                            break;
                        } catch (IllegalArgumentException e) {
                            limparTela();
                            System.out.println(e.getMessage());
                            pausarExecucao(scanner);                            
                        }
                    } while (true);


                    do {
                        try {
                            limparTela();
                            linhaPadronizadaTitulo("CADASTRAR PESSOA");
                            System.out.println("Digite o CPF da pessoa: ");
                            cpf = scanner.nextLine();
                            validarCpf(cpf);
                            break;
                        } catch (IllegalArgumentException e) {
                            limparTela();
                            System.out.println(e.getMessage());
                            pausarExecucao(scanner);
                        }                 
                    } while (true);

                    try {
                        criarPersistenciaPessoa(0, nome, idade, endereco, cpf);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        pausarExecucao(scanner);
                    }
                    break;
                        
                case 2:
                    limparTela();
                    linhaPadronizadaTitulo("BUSCAR PESSOA");

                    int id = -1;
                    try {
                        System.out.println("Digite o ID da pessoa pra buscar: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Falha na inserção do dado, tente novamente.");
                    }

                    Pessoa pessoaEncontrada = null;
                    pessoaEncontrada = buscarPessoa(id);
                    if (pessoaEncontrada == null){
                        limparTela();
                        System.out.println("Pessoa com esse id " + id + " não encontrada!");
                        pausarExecucao(scanner);
                        break;
                    }

                    limparTela();
                    linhaPadronizadaTitulo("BUSCAR PESSOA");
                    System.out.println("Encontrado(a) com sucesso o(a): " + pessoaEncontrada.getNome());
                    linhaPadronizadaFim();
                    pausarExecucao(scanner);                    
                    break;
                    
                    

                case 3:
                    limparTela();
                    System.out.println("Listar Todas as Pessoas");
                    int quantidadePessoas = buscarTodasAsPessoas().size();
                    int contador = 0;
                    for (Pessoa pessoa : buscarTodasAsPessoas()) {    
                        if (contador == 0) {
                            linhaPadronizadaTitulo("LISTA DE PESSOAS"); 
                        }

                        System.out.println("ID: " + pessoa.getId() + ".");
                        System.out.println("Nome: " + pessoa.getNome() + ".");
                        System.out.println("CPF: " + pessoa.getCpf() + ".");
                        System.out.println("Endereço: " + pessoa.getEndereco() + ".");
                        System.out.println("idade: " + pessoa.getIdade() + " anos.");
                        
                        contador++;
                        if (contador < quantidadePessoas) {
                            linhaPadronizadaMeio();
                        }
                    }
                    linhaPadronizadaFim();

                    pausarExecucao(scanner);
                    break;
                case 4:
                    limparTela();
                    linhaPadronizadaTitulo("REMOVER PESSOA");

                    System.out.println("Digite o ID da pessoa que deseja remover: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    pessoaEncontrada = buscarPessoa(id);
                    if (pessoaEncontrada == null){
                        limparTela();
                        linhaPadronizadaTitulo("REMOVER PESSOA");
                        System.out.println("Pessoa com esse id " + id + " não encontrada!");
                        pausarExecucao(scanner);
                        break;
                    }

                    removerPessoa(pessoaEncontrada.getId());
                    limparTela();
                    linhaPadronizadaTitulo("REMOVER PESSOA");
                    System.out.println("Pessoa " + pessoaEncontrada.getNome()+ " removido(a) com sucesso!");
                    linhaPadronizadaFim();
                    pausarExecucao(scanner);
                    break;
                case 5:
                    limparTela();
                    linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                    System.out.println("Digite o ID da pessoa: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    pessoaEncontrada = buscarPessoa(id);
                    if (pessoaEncontrada == null){
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                        System.out.println("Pessoa com esse id " + id + " não encontrada!");
                        pausarExecucao(scanner);
                        break;
                    }

                    System.out.println();
                    internoSubMenuPessoa(pessoaEncontrada, scanner);
                    atualizarPessoa(pessoaEncontrada);//merge busca o ID automaticamente Obj
                    break;
                case 0:
                    limparTela();
                    System.out.println("Voltando ao Menu Principal...");
                    pausarExecucao(scanner);
                    break;
                default:
                    limparTela();
                    System.out.println("Opção inválida. Tente novamente.");
                    pausarExecucao(scanner);
            }
        } while (opcao != 0);
    }
}
