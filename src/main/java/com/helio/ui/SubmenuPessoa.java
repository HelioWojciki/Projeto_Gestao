package com.helio.ui;

import static com.helio.utilities.ClearScreen.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.LinhaPadronizada.linhaPadronizada;
import static com.helio.utilities.Validation.validarNome;
import static com.helio.utilities.Validation.validarIdade;

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

            switch (opcao) {
                case 1:
                    String nome = null;
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
                            System.out.println(e.getMessage());
                            pausarExecucao(scanner);
                        }
                    } while (true);

                    // essa validação de idade não precisa de loop, porque já está validando no método validarIdade()                    
                    validarIdade(scanner);

//================parei aqui, funcionando=====================================================
                    System.out.println("Digite o endereço da pessoa: ");
                    String endereco = scanner.nextLine();

                    System.out.println("Digite o CPF da pessoa: ");
                    String cpf = scanner.nextLine();

                    break;                    
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
