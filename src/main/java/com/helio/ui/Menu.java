package com.helio.ui;

import static com.helio.utilities.ClearScreen.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;

import java.util.Scanner;

public class Menu {

    public static void menu(Scanner scanner) {
        int opcao = -1;      
        do {
            limparTela();
            System.out.println("--------------------MENU--------------------");
            System.out.println("1. Pessoa");
            System.out.println("2. ");
            System.out.println("4. ");
            System.out.println("5. \n");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
             
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o scanner após nextInt()

            switch (opcao) {
                case 1:                    
                    limparTela(); // criar amanhã os submenus em classe destinta
                    
                    System.out.println("1. Adicionar Pessoa");
                    System.out.println("2. Listar Pessoa");
                    System.out.println("3. Editar Pessoa");
                    System.out.println("4. Remover Pessoa\n");
                    System.out.println("0. Voltar ao menu principal");
                    System.out.print("\nEscolha uma opção: ");

                    pausarExecucao(scanner);
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
                    System.out.println("Saindo...");
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
