package com.helio.ui;

import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaMeio;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Validacao.entradaInt;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.utilities.Validacao.entradaStringPadrao;

import java.util.Scanner;

import com.helio.dao.EmpresaDao;
import com.helio.models.Empresa;


public class SubmenuEmpresa {
    EmpresaDao empresaDao = new EmpresaDao();

    public void submenuEmpresa(Scanner scanner){

        int opcao = -1;
        do {
            limparTela();
            linhaPadronizadaTitulo("SUBMENU EMPRESAS");
            System.out.println("    1. Cadastrar Empresa");
            System.out.println("    2. Remover Empresa");
            System.out.println("    3. Listar Empresas");
            System.out.println("    4. Atualizar Empresa\n");

            System.out.println("\n0. Voltar ao Menu Principal");
            
            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    limparTela();
                    linhaPadronizadaTitulo("CADASTRO EMPRESA");

                    String nomeEmpresa = entradaStringPadrao(scanner, "Digite o nome da empresa: ");
                    String cnpj = entradaStringPadrao(scanner, "Digite o CNPJ da empresa: ");

                    empresaDao.criarPersistenciaEmpresa(nomeEmpresa, cnpj);

                    limparTela();
                    linhaPadronizadaTitulo("CADASTRO EMPRESA");
                    System.out.println("Empresa cadastrada com sucesso!");
                    System.out.println("Nome: " + nomeEmpresa);
                    pausarExecucao(scanner);

                    break;
                case 2:
                    limparTela();
                    linhaPadronizadaTitulo("REMOVER EMPRESA");                   

                    try {
                        Empresa empresaRemovida = empresaDao.removerEmpresa(entradaInt(scanner, "Digite o ID da Empresa: "));
                        
                        limparTela();
                        linhaPadronizadaTitulo("REMOVER EMPRESA");
                        
                        System.out.println(empresaRemovida.toString() + "\nRemovido com sucesso!");
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        System.out.println("Erro ao remover. Digite um ID válido!");
                        pausarExecucao(scanner);
                    }
                    break;

                case 3:
                    int i = 1;
                    for (Empresa elementoDaLista : empresaDao.listarEmpresas()) { // o que retorna do listar eu apresento no foreach, com os dados de cada elemento da lista
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("LISTAR EMPRESAS"); // printa o título no 1º i
                        }

                        System.out.println(i + "º Elemento da Lista:\n" + // printa a posição
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }    

                    System.out.println("\nAtenção. Valores de iteração não remetem a ordem exata da identificação no banco!");
                    System.out.println("Para posição do banco considerar o campo ID da Empresa!");
                    pausarExecucao(scanner); 

                    break;

                case 4:
                    int idEmpresa = -1;

                    limparTela();
                    linhaPadronizadaTitulo("LISTAR EMPRESAS");  

                    idEmpresa = entradaInt(scanner, "Digite o ID da empresa: ");
                    Empresa empresa = null;

                    try {
                        empresa = empresaDao.buscarEmpresa(idEmpresa);
                        
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO EMPRESA");

                        System.out.println("Encontrado para alterar: " + empresa.toString());
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO EMPRESA");                        
                        System.out.println("Erro. Não foi encontrado essa empresa!");
                        pausarExecucao(scanner);
                    }

                    String novoNomeEmpresa = entradaStringPadrao(scanner, "Digite o novo nome: ");
                    String novoCNPJEmpresa = entradaStringPadrao(scanner, "Digite o novo CNPJ: ");

                    empresa.setNomeEmpresa(novoNomeEmpresa);
                    empresa.setCnpj(novoCNPJEmpresa);

                    if (empresa != null) {
                        Empresa empresaAtualizada = empresaDao.atualizarEmpresa(empresa);

                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO EMRPESA"); 

                        System.out.println("Alterado com sucesso para: " + empresaAtualizada.toString());
                        pausarExecucao(scanner);
                    }

                    break;

                case 0:
                    limparTela();
                    System.out.println("Voltando ao Menu...");
                    pausarExecucao(scanner);
                    break;

                default:
                    limparTela();
                    System.out.println("Opção inválida. Tente novamente.");
                    pausarExecucao(scanner);
            }

        } while (opcao != 0);
    }

}
