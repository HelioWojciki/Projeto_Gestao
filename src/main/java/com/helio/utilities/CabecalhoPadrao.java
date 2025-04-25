package com.helio.utilities;

public class CabecalhoPadrao {
    
    public static void linhaPadronizada(String texto) {
        int largura = 80; // define a largura
        int espacos = largura - texto.length() - 4; 
        int espacosEsquerda = espacos / 2;
        int espacosDireita = espacos - espacosEsquerda;
    
        // Monta a linha
        String linha = "=".repeat(espacosEsquerda) + " " + texto + " " + "=".repeat(espacosDireita);
        System.out.println(linha);
    }
    
}
