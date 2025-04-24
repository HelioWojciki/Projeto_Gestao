package com.helio.utilities;

public class LinhaPadronizada {
    
    public static void linhaPadronizada(String texto) {
        int largura = 80; // defina a largura desejada
        int espacos = largura - texto.length() - 4; // subtrai o espaço para as bordas e o texto
        int espacosEsquerda = espacos / 2;
        int espacosDireita = espacos - espacosEsquerda;
    
        // Monta a linha
        String linha = "=".repeat(espacosEsquerda) + " " + texto + " " + "=".repeat(espacosDireita);
        System.out.println(linha);
    }
    
}
