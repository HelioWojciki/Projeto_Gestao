package com.helio.utilities;

public class CabecalhoPadrao {
    
    public static void linhaPadronizadaTitulo(String texto) {
        int largura = 80; // define a largura
        int espacos = largura - texto.length() - 4; 
        int espacosEsquerda = espacos / 2;
        int espacosDireita = espacos - espacosEsquerda;
    
        // Monta a linha
        String linha = "=".repeat(espacosEsquerda) + " " + texto + " " + "=".repeat(espacosDireita);
        System.out.println(linha);
    }

    public static void linhaPadronizadaFim() {
        int largura = 80; // define a largura
        String linha = "=".repeat(largura);
        System.out.println(linha);
    }

    public static void linhaPadronizadaMeio() {
        int largura = 80; // define a largura
        String linha = ".".repeat(largura);
        System.out.println(linha);
    }
    
}
