package com.helio.utilities;

import java.util.Scanner;

public class Pausa {
    
    public static void pausarExecucao(Scanner scanner){
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
