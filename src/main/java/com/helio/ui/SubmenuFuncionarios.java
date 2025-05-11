package com.helio.ui;

import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaFim;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaMeio;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.Validacao.entradaDouble;
import static com.helio.utilities.Validacao.entradaInt;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.utilities.Validacao.entradaStringCargo;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.helio.models.ContaPoupanca;
import com.helio.models.Empresa;
import com.helio.models.Funcionario;
import com.helio.models.Pessoa;
import com.helio.dao.ContaPoupancaDao;
import com.helio.dao.EmpresaDao;
import com.helio.dao.FuncionarioDao;
import com.helio.dao.PessoaDao; 

import jakarta.persistence.NoResultException;

public class SubmenuFuncionarios {
    PessoaDao pessoaDao = new PessoaDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ContaPoupancaDao contaPoupancaDao = new ContaPoupancaDao();
    EmpresaDao empresaDao = new EmpresaDao();

    public void submenuFuncionarios(Scanner scanner) {

        int opcao = -1;
        do {
            limparTela();
            linhaPadronizadaTitulo("SUBMENU FUNCIONÁRIOS");
            System.out.println("    1. Cadastrar Funcionário");
            System.out.println("    2. Buscar Funcionário");
            System.out.println("    3. Listar Funcionários");
            System.out.println("    4. Remover Funcionario");
            System.out.println("    5. Atualizar Funcionário\n");
            System.out.println("    6. Registrar Funcionário na Empresa");
            System.out.println("    7. Demitir Funcionário da Empresa\n");

            System.out.println("\n0. Voltar ao Menu Principal");
            
            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    limparTela();
                    linhaPadronizadaTitulo("CADASTRAR FUNCIONÁRIO"); 

                    // -----------------------------------confirma pessoa
                    int id = -1;
                    try {
                        id = entradaInt(scanner, "Digite o ID da pessoa que se tornará funcionário(a): ");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }

                    Pessoa pessoaEncontrada = pessoaDao.buscarPessoa(id);
                    if (pessoaEncontrada == null){
                        limparTela();
                        System.out.println("Pessoa não encontrada, realize o cadastro dessa pessoa no menu principal!");
                        pausarExecucao(scanner);
                        return;
                    }

                    // -----------------------------------confirma conta
                    int nrConta = -1;
                    nrConta = entradaInt(scanner, "Digite o número da Conta Poupanca: ");

                    ContaPoupanca contaPoupancaEncontrada = null;
                    try {
                        contaPoupancaEncontrada = contaPoupancaDao.buscarContaPoupanca(nrConta);

                    } catch (NoResultException e) {
                        limparTela();                        
                        System.out.println("Erro: Conta não encontrada!");
                        pausarExecucao(scanner);
                    
                    } catch (IllegalArgumentException e) { 
                        limparTela();                      
                        System.out.println("Erro: Insira apenas números!");
                        pausarExecucao(scanner);

                    } catch (Exception e) {
                        limparTela();
                        System.out.println("Erro inesperado.");
                        pausarExecucao(scanner);
                    }
                    
                    limparTela();
                    linhaPadronizadaTitulo("CADASTRAR FUNCIONÁRIO");                 
                    
                    double salario = entradaDouble(scanner, "Digite o salário do funcionário: ");
                    String cargo = entradaStringCargo(scanner, "Digite o cargo do funcionário");
                    Empresa empresa = null; // Add, para manipular na empresa

                    try {
                        Funcionario funcPersistido = funcionarioDao.criarPersistenciaFuncionario(pessoaEncontrada, salario, cargo, contaPoupancaEncontrada, empresa); // passo: pessoa, conta e (empresa = null)
                        
                        limparTela();
                        linhaPadronizadaTitulo("CADASTRAR FUNCIONÁRIO");
                        System.out.println("Persistido com sucesso! " + funcPersistido.toString());
                        
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        System.out.println("Erro ao persistir o dado!");
                        pausarExecucao(scanner);
                    }
                    break;
//-----------------------------------------------------------------------------------------
                case 2:
                    limparTela();
                    linhaPadronizadaTitulo("BUSCAR FUNCIONÁRIO");

                    int idFuncionario = -1;
                    
                    idFuncionario = entradaInt(scanner, "Digite o número de identificação ID do funcionário: ");
                    if (idFuncionario == -1) {
                        System.out.println("ID inválido! Retornando ao menu...");
                        pausarExecucao(scanner);
                        break;
                    }                    
                    
                    Funcionario funcionarioEncontrado = null; // inicia 

                    try {
                        funcionarioEncontrado = funcionarioDao.buscarFuncionario(idFuncionario);

                    } catch (NoResultException e) {
                        limparTela();                        
                        System.out.println("Erro: Funcionário não encontrado(a)!");
                        pausarExecucao(scanner);
                    
                    } catch (IllegalArgumentException e) { 
                        limparTela();                      
                        System.out.println("Erro: Insira apenas números!");
                        pausarExecucao(scanner);

                    } catch (Exception e) {
                        limparTela();
                        System.out.println("Erro inesperado.");
                        pausarExecucao(scanner);
                    }
                   
                    if (funcionarioEncontrado != null) {
                        limparTela();
                        linhaPadronizadaTitulo("BUSCAR FUNCIONÁRIO");
                        System.out.println(funcionarioEncontrado.toString());
                        pausarExecucao(scanner);
                    }                   

                    break;                    

                case 3:
                    int i = 1;
                    for (Funcionario elementoDaLista : funcionarioDao.listarFuncionarios()) { // o que retorna do listar eu apresento no foreach, com dados de cada elemento da lista
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("LISTAR FUNCIONÁRIOS"); // apenas printa o título no 1º i
                        }

                        System.out.println(i + "º Elemento da Lista:\n" + // printa a posição
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }

                    // complemento da mensagem
                    System.out.println("\nAtenção. Valores de iteração não remetem a ordem exata da identificação no banco!");
                    System.out.println("Para posição do banco considerar o campo ID do funcionário(a)!");
                    pausarExecucao(scanner);

                    break;

                case 4:
                    limparTela();
                    linhaPadronizadaTitulo("REMOVER FUNCIONÁRIO");

                    // chama o remove   
                    try {
                        Funcionario removido = funcionarioDao.removerFuncionario(entradaInt(scanner, "Digite o ID do funcionário a ser removido: "));
                        limparTela();
                        linhaPadronizadaTitulo("REMOVER FUNCIONÁRIO");

                        System.out.println(removido.toString() + "\nRemovido com sucesso!");

                        linhaPadronizadaFim();
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        System.out.println("Erro ao remover. Digite um ID válido!");
                        pausarExecucao(scanner);
                    }
                    break;

                case 5:
                    // chamar para atualizar e trato aqui com try-catch
                    int idfunc = -1;
                    limparTela();
                    linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO");

                    idfunc = entradaInt(scanner, "Digite o ID do funcionário(a) que deseja alterar: ");
                    Funcionario func = null; // apenas inicia o objeto funcionário

                    try {
                        func = funcionarioDao.buscarFuncionario(idfunc);
                        
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO");
                        System.out.println("Encontrado para alterar: " + func.toString());
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO");                        
                        System.out.println("Erro. Não foi encontrado esse funcionário(a)!");
                        pausarExecucao(scanner);
                    }
                    
                    //               ⬆️ está ok    ⬇️ desenvolvendo
                    try {
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO");   
                        System.out.println("Atenção. É possível alterar apenas o salário e cargo!\n");
                        
                        double novoSalario = entradaDouble(scanner, "Digite o novo salário para este funcionário(a): ");

                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO");   
                        String novoCargo = entradaStringCargo(scanner, "Digite o novo cargo para este funcionário(a): ");
                        // agencia = entradaString(scanner, cp.toString() + "\n\nDigite o número da nova agência da conta: ");
                        
                        func.setSalario(novoSalario); // atribuí de fato ao objeto ambas atualizações
                        func.setCargo(novoCargo);

                        if (func != null){
                            Funcionario funcAtualizado = funcionarioDao.atualizarFuncionario(func);

                            limparTela();
                            linhaPadronizadaTitulo("ATUALIZANDO FUNCIONÁRIO"); 

                            System.out.println("Alterado com sucesso para: " + funcAtualizado.toString());
                            pausarExecucao(scanner);
                        }

                    } catch (Exception e) {
                        System.out.println("Erro ao atualizar!");
                        pausarExecucao(scanner);
                        break;
                    }

                    break;
                
                case 6:
                    // ------ lista empresas
                    i = 1;
                    for (Empresa elementoDaLista : empresaDao.listarEmpresas()) { // o que retorna do listar eu apresento no foreach, com dados de cada elemento da lista
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("REGISTRAR FUNCIONÁRIO"); // apenas printa o título no 1º i
                        }

                        System.out.println(i + "º Elemento da Lista:\n" + // printa a posição
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }
                    int idEmpresa = entradaInt(scanner, "Digite o ID da empresa para vincular o funcionário(a): ");
                    
                    // ----- lista funcionário(a)
                    limparTela();
                    linhaPadronizadaTitulo("REGISTRAR FUNCIONÁRIO");
                    
                    i = 1;
                    for (Funcionario elementoDaLista : funcionarioDao.listarFuncionarios()) { // o que retorna do listar eu apresento no foreach, com dados de cada elemento da lista
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("REGISTRAR FUNCIONÁRIO"); // apenas printa o título no 1º i
                        }

                        System.out.println(i + "º Elemento da Lista:\n" + // printa a posição
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }
                    idFuncionario = entradaInt(scanner, "Digite o ID do funcionário(a) para vincular na empresa: ");

                    // setar
                    Funcionario funcEncontrado = funcionarioDao.buscarFuncionario(idFuncionario);
                    Empresa empresaEncontrada = empresaDao.buscarEmpresa(idEmpresa);

                    if (funcEncontrado != null && empresaEncontrada != null) {
                        funcEncontrado.setEmpresa(empresaEncontrada);
                        Funcionario funcRegistrado = funcionarioDao.registrarFuncionario(funcEncontrado);

                        limparTela();
                        linhaPadronizadaTitulo("REGISTRAR FUNCIONÁRIO");
                        System.out.println("Registrado com sucesso! " + funcRegistrado.toString());
                        System.out.println("\nEm: " + empresaEncontrada.toString());
                        pausarExecucao(scanner);
                    }
                    break;
                
                case 7:
                    limparTela();
                    linhaPadronizadaTitulo("DEMITIR FUNCIONÁRIO");

                    i = 1;
                    for (Funcionario elementoDaLista : funcionarioDao.listarFuncionarios()) { 
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("DEMITIR FUNCIONÁRIO");
                        }

                        System.out.println(i + "º Elemento da Lista:\n" + 
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }
                    idFuncionario = entradaInt(scanner, "Digite o ID do funcionário(a) para demitir da empresa: ");

                    funcEncontrado = funcionarioDao.buscarFuncionario(idFuncionario);

                    if (funcEncontrado != null) {
                        String nomeEmpresa = funcEncontrado.getEmpresa().getNomeEmpresa();

                        funcEncontrado.setEmpresa(null);
                        funcionarioDao.demitirFuncionario(funcEncontrado);

                        limparTela();
                        linhaPadronizadaTitulo("DEMITIR FUNCIONÁRIO");
                        System.out.println("Demitido com sucesso! " + funcEncontrado.toString());
                        System.out.println("Da empresa: " + nomeEmpresa);
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

