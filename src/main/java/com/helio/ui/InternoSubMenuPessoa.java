package com.helio.ui;

import static com.helio.dao.PessoaDao.atualizarPessoa;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Validacao.validarNome;
import static com.helio.utilities.Validacao.validarCpf;
import static com.helio.utilities.Validacao.validarEndereco;
import static com.helio.utilities.Validacao.validarIdade;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.helio.models.Pessoa;

public class InternoSubMenuPessoa {
    
    public static void internoSubMenuPessoa(Pessoa pessoa, Scanner scanner){
        int opcao = 0;
        do {
            limparTela();
            linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
            System.out.println("Selecionado " + pessoa.getNome() + ".");
            System.out.println("1. Nome");
            System.out.println("2. Idade");
            System.out.println("3. Endereço");
            System.out.println("4. CPF");
            System.out.println("\n0. Voltar ao menu\n\n");
            System.out.print("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    limparTela();
                    System.out.println("Digite o nome: ");
                    String nome = "";
                    try {
                        nome = scanner.nextLine();
                        validarNome(nome);
                        pessoa.setNome(nome);
                        atualizarPessoa(pessoa);
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                        System.out.println("Alterado nome para " + pessoa.getNome() + " com sucesso!");
                        pausarExecucao(scanner);
                        break;
                    } catch (Exception e) {
                        exibirErro(scanner, "ATUALIZAR CADASTRO DE PESSOA", e.getMessage());
                        break;
                    }
                    
                case 2:
                    limparTela();
                    int idade = -1;
                    System.out.println("Digite a idade: ");     
                    try {
                        idade = scanner.nextInt();
                        pausarExecucao(scanner);
                    } catch (InputMismatchException e){ 
                        scanner.nextLine();
                        exibirErro(scanner, "ATUALIZAR CADASTRO DE PESSOA", "Entrada inválida. Por favor, insira um número inteiro.");
                        break;
                    }

                    try {
                        validarIdade(idade);
                        pessoa.setIdade(idade);
                        atualizarPessoa(pessoa);

                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                        System.out.println("Alterado idade para " + pessoa.getIdade() + " com sucesso!");
                        pausarExecucao(scanner);
                        break;
                    } catch (IllegalArgumentException e) { 
                        exibirErro(scanner, "ATUALIZAR CADASTRO DE PESSOA", e.getMessage());
                        break;
                    }
                                
                case 3:
                    limparTela();
                    System.out.println("Digite o endereço: ");
                    String endereco = scanner.nextLine();
                    try {
                        validarEndereco(endereco);
                        pessoa.setEndereco(endereco);
                        atualizarPessoa(pessoa);
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                        System.out.println("Alterado endereço para " + pessoa.getEndereco() + " com sucesso!");
                        pausarExecucao(scanner);                    
                        break; 
                    } catch (IllegalArgumentException e) {
                        exibirErro(scanner, "ATUALIZAR CADASTRO DE PESSOA", e.getMessage());
                        break;
                    }      

                case 4:
                    limparTela();
                    System.out.println("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    try { 
                        validarCpf(cpf);
                        pessoa.setCpf(cpf);
                        atualizarPessoa(pessoa);
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZAR CADASTRO DE PESSOA");
                        System.out.println("Alterado CPF para " + pessoa.getCpf() + " com sucesso!");
                        pausarExecucao(scanner);
                        break;
                    } catch (IllegalArgumentException e) {
                        exibirErro(scanner, "ATUALIZAR CADASTRO DE PESSOA", e.getMessage());
                        break;
                    }    

                case 0:
                    limparTela();
                    System.out.println("Voltando ao Menu...");
                    pausarExecucao(scanner);
                    break;

                default:
                    limparTela();
                    System.out.println("Opção inválida. Tente novamente.");
                    pausarExecucao(scanner);
            }
        } while (opcao != 0);        
    }

    public static void exibirErro(Scanner scanner, String titulo, String mensagem) {
        limparTela();
        
        linhaPadronizadaTitulo(titulo);
        
        System.out.println(mensagem);
        pausarExecucao(scanner);
    }

}