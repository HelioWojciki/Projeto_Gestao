package com.helio.utilities;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizada;

import java.util.InputMismatchException;

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
            linhaPadronizada("CADASTRAR PESSOA");
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
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
}
