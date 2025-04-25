package com.helio.ui;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizada;


import static com.helio.utilities.Validacao.validarNome;
import static com.helio.utilities.Validacao.validarIdade;
import static com.helio.utilities.Validacao.validarEndereco;
import static com.helio.utilities.Validacao.validarCpf;
import static com.helio.dao.PessoaDao.criarPersistenciaPessoa;

import java.util.Scanner;

public class SubmenuPessoa {

    public static void submenuPessoa(Scanner scanner) {
        int opcao = -1;      
        do {
            limparTela();
            linhaPadronizada("SUBMENU PESSOA");
            System.out.println("1. Cadastrar Pessoa");
            System.out.println("2. Listar Pessoa");
            System.out.println("3. Editar Pessoa");
            System.out.println("4. Remover Pessoa\n");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o scanner após nextInt()

            String nome;
            int idade;
            String endereco;
            String cpf;

            switch (opcao) {
                case 1:                    
                    do {
                        // While sempre verdadeiro, até que o validarNome() retorne ok executando o break
                        limparTela();
                        linhaPadronizada("CADASTRAR PESSOA");
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
                            linhaPadronizada("CADASTRAR PESSOA");
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
                            linhaPadronizada("CADASTRAR PESSOA");
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
                            linhaPadronizada("CADASTRAR PESSOA");
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
                        criarPersistenciaPessoa(opcao, nome, opcao, endereco, cpf);
                    } catch (Exception e) {
                        System.out.println("Erro...");
                    }
                        
                case 2:
                    limparTela();
                    System.out.println("Listar Pessoa");
                    pausarExecucao(scanner);
                    break;
                case 3:
                    limparTela();
                    System.out.println("Editar Pessoa");
                    pausarExecucao(scanner);
                    break;
                case 4:
                    limparTela();
                    System.out.println("Remover Pessoa");
                    pausarExecucao(scanner);
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
