package com.helio.utilities;

public class ResetaTerminal {
    public static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
        } catch (Exception e) {
            System.err.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }
}
