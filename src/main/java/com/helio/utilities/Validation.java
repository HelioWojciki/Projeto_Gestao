package com.helio.utilities;

import static com.helio.utilities.ClearScreen.limparTela;
import static com.helio.utilities.Pausa.pausarExecucao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    
    public static void validarNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 3 caracteres.");
        }
        if (!nome.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Nome deve conter apenas letras e espaços.");
        }
    }
    
    public static void validarIdade(Scanner scanner){

        int idade = -1;
        do {
            limparTela();
            System.out.println("Digite a idade da pessoa: ");
            try {
                idade = scanner.nextInt();
                scanner.nextLine();
                if (idade < 1 || idade > 100) {
                    System.out.println("Idade deve ser entre 1 e 100 anos.");
                    pausarExecucao(scanner);
                } else {
                    break; // Sai do loop se a idade for válida
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.nextLine();
                pausarExecucao(scanner);
                continue; // Volta lá pro início do loop
            } 
            
        } while (true);
            
    }
}
