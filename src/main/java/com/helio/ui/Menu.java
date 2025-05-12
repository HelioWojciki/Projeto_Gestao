package com.helio.ui;

import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.ui.SubmenuPessoa.submenuPessoa;
import static com.helio.ui.MenuTipoContas.menuTipoContas;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;


import java.util.Scanner;


public class Menu {    

    public static void menu(Scanner scanner) {
        SubmenuFuncionarios submenuFuncionarios = new SubmenuFuncionarios();
        SubmenuEmpresa submenuEmpresa = new SubmenuEmpresa();

        int opcao = -1;      
        do {
            limparTela();
            linhaPadronizadaTitulo("MENU PRINCIPAL");
            System.out.println("    1. Gerenciar Pessoas");
            System.out.println("    2. Gerenciar Contas");
            System.out.println("    3. Gerenciar Funcionarios");
            System.out.println("    4. Gerenciar Empresa\n");
            System.out.println("0. Sair");
            
            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    submenuPessoa(scanner);
                    break;                    
                case 2:
                    menuTipoContas(scanner);
                    break;
                case 3:
                    submenuFuncionarios.submenuFuncionarios(scanner);
                    break;
                case 4:
                    submenuEmpresa.submenuEmpresa(scanner);
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
                    break;
            }
        } while (opcao != 0);
    }
    
}
