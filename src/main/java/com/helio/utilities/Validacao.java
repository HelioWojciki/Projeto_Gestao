package com.helio.utilities;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validacao {
    
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
    
    public static void validarIdade(int idade){

        if (idade < 1 || idade > 100) {
            throw new IllegalArgumentException("Digite uma idade entre 1 e 100.");
        }
        try {
            if (idade >= 1 || idade <= 100) {
                return;
            }
        } catch (InputMismatchException e) {
            limparTela();
            linhaPadronizadaTitulo("CADASTRAR PESSOA");
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            pausarExecucao(null);
        }             
    }

    public static void validarEndereco(String endereco){
        if (endereco.length() < 5) {
            throw new IllegalArgumentException("Endereço deve ter no mínimo 5 caracteres");
        }
        if ((!endereco.matches("[a-zA-Z\\s0-9,]+"))) {
            throw new IllegalArgumentException("Endereço deve conter apenas letras, números e espaços.");
        }
    }

    public static void validarCpf(String cpf){
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter apenas os 11 números");
        }
    }

    public static double entradaDouble(Scanner scanner, String texto){
        while (true) {
            System.out.println(texto);
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
    
                if (valor <= 0) {
                    throw new IllegalArgumentException("Entrada inválida! Digite um número maior que 0!");
                }      

                return valor;

            } catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Entrada inválida! Digite um número decimal!");
                pausarExecucao(scanner);
                limparTela();
            } catch (IllegalArgumentException e) {                
                System.out.println(e.getMessage());
            }
        }
    }

    public static String entradaString(Scanner scanner, String texto){
        while (true) {
            System.out.println(texto);
            try {
                String valor = scanner.nextLine();
    
                if (!valor.matches("[1-9]\\d*")) {
                    throw new IllegalArgumentException("Entrada inválida! Digite apenas números positivos!");
                }      

                return valor;

            }catch (IllegalArgumentException e) {                
                System.out.println(e.getMessage());
            }
        }
    }

    public static String entradaStringCargo(Scanner scanner, String texto){
        while (true) {
            System.out.println(texto);
            
            String valor = scanner.nextLine();  
            return valor;            
        }
    }

    public static String entradaStringConta(Scanner scanner, String texto){
        while (true) {
            System.out.println(texto);
            try {
                String valor = scanner.nextLine();
    
                if (!valor.matches("[0-9]\\d*")) { // pequeno ajuste para que aceite também o zero
                    throw new IllegalArgumentException("Entrada inválida! Digite apenas números positivos!");
                }      

                return valor;

            }catch (IllegalArgumentException e) {                
                System.out.println(e.getMessage());
            }
        }
    }

    public static int entradaInt(Scanner scanner, String texto){
        while (true) {
            if (texto != null) {
                System.out.println(texto);
            }

            try {
                int valor = scanner.nextInt();
    
                if (valor <= 0) {
                    throw new IllegalArgumentException("Entrada inválida! Valor deve ser um número positivo!");
                }
    
                return valor;
    
            } catch (InputMismatchException e) { 
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                return -1;
            } catch (IllegalArgumentException e) {                
                System.out.println(e.getMessage());
            } finally {
                scanner.nextLine();
            }
        }
    }
    
    public static int entradaIntMenu(Scanner scanner, String texto){
        while (true) {
            if (texto != null) {
                System.out.print(texto);
            }

            int valor = 1;
            try {
                valor = scanner.nextInt();
                scanner.nextLine();
    
                if (valor < 0) {
                    throw new IllegalArgumentException("Entrada inválida! Valor deve ser igual ou maior que 0.");
                }
    
                return valor;
    
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números inteiros.");
                scanner.nextLine();
                return valor = -1;

            } catch (IllegalArgumentException e) {                
                System.out.println(e.getMessage());
            }
        }
    }

    public static String entradaStringPadrao(Scanner scanner, String texto){
        while (true) {
            System.out.println(texto);
            
            String valor = scanner.nextLine();  
            return valor;            
        }
    }
}