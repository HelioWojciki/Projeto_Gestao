package com.helio;

import static com.helio.ui.Menu.menu; // import static .método estático

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        menu(scanner);
        
        scanner.close();
    }
}
