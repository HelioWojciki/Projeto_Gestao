package com.helio.ui;

import static com.helio.dao.PessoaDao.buscarPessoa;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaFim;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaMeio;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.dao.ContaPoupancaDao.atualizarContaPoupanca;
import static com.helio.dao.ContaPoupancaDao.buscarContaPoupanca;
import static com.helio.dao.ContaPoupancaDao.criarPersistenciaContaPoupanca;
import static com.helio.dao.ContaPoupancaDao.listarContasPoupancas;
import static com.helio.dao.ContaPoupancaDao.removerContaPoupanca;
import static com.helio.utilities.Validacao.entradaDouble;
import static com.helio.utilities.Validacao.entradaInt;
import static com.helio.utilities.Validacao.entradaIntMenu;
import static com.helio.utilities.Validacao.entradaString;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.helio.models.ContaPoupanca;
import com.helio.models.Pessoa;

import jakarta.persistence.NoResultException;


public class SubmenuContaPoupanca {

    public static void submenuContaPoupanca(Scanner scanner){
        int opcao = -1;
        do {
            limparTela();
            linhaPadronizadaTitulo("SUBMENU CONTA POUPANCA");
            System.out.println("    1. Cadastrar Conta Poupança");
            System.out.println("    2. Buscar Conta Poupança");
            System.out.println("    3. Listar Contas Poupanças");
            System.out.println("    4. Remover Conta Poupança");
            System.out.println("    5. Atualizar Conta Poupança\n");

            System.out.println("0. Voltar ao Menu Principal");
            
            opcao = entradaIntMenu(scanner, "\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    limparTela();
                    linhaPadronizadaTitulo("CADASTRAR CONTA POUPANÇA"); 

                    int id = -1; // ------------ revisar esse try... --------------------
                    try {
                        id = entradaInt(scanner, "Digite o ID da pessoa que abrirá a conta: ");
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }

                    Pessoa pessoaEncontrada = buscarPessoa(id);

                    if (pessoaEncontrada == null){
                        limparTela();
                        System.out.println("Pessoa não encontrada, realize o cadastro dessa pessoa no menu principal!");
                        pausarExecucao(scanner);
                        break;
                    }
                    
                    
                    limparTela();
                    linhaPadronizadaTitulo("CADASTRAR CONTA POUPANÇA");

                    double saldoInicial = entradaDouble(scanner, "É Obrigatório colocar um valor para a geração da Conta!\nDigite o Saldo Inicial da Conta: ");                        
                    String conta = entradaString(scanner, "Digite o número da Conta: ");
                    String agencia = entradaString(scanner, "Digite o número da Agência: ");
                
                    criarPersistenciaContaPoupanca(pessoaEncontrada, saldoInicial, conta, agencia);
                    break;

                case 2:
                    limparTela();
                    linhaPadronizadaTitulo("BUSCAR CONTA POUPANÇA");

                    int nrConta = -1;
                    nrConta = entradaInt(scanner, "Digite o número da Conta Poupanca: ");
                    
                    ContaPoupanca contaPoupancaEncontrada = null; // apenas para iniciar

                    try {
                        contaPoupancaEncontrada = buscarContaPoupanca(nrConta);

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
                   
                    if (contaPoupancaEncontrada != null) {
                        limparTela();
                        linhaPadronizadaTitulo("BUSCAR CONTA POUPANÇA");
                        System.out.println(contaPoupancaEncontrada.toString());
                        pausarExecucao(scanner);
                    }                   

                    break;                    

                case 3:
                    int i = 1;
                    for (ContaPoupanca elementoDaLista : listarContasPoupancas()) { // o que retorna do listar eu apresento no foreach, com dados de cada elemento da lista
                        
                        if (i == 1) {
                            limparTela();
                            linhaPadronizadaTitulo("BUSCAR CONTA POUPANÇA");
                        }

                        System.out.println(i + " Elemento da Lista:\n" +
                                                elementoDaLista.toString());
                        ;
                        linhaPadronizadaMeio();

                        i++;
                    }

                    // complemento da mensagem
                    System.out.println("\nAtenção. Valores de iteração não remetem a ordem exata da identificação no banco!");
                    System.out.println("Para posição do banco considerar o CAMPO ID da conta!");
                    pausarExecucao(scanner);

                    break;

                case 4:
                    limparTela();
                    linhaPadronizadaTitulo("REMOVER CONTA POUPANÇA");

                    // chama o remove conta poupança    
                    try {
                        ContaPoupanca removido = removerContaPoupanca(entradaInt(scanner, "Digite o ID da Conta a ser removida: "));
                        limparTela();
                        linhaPadronizadaTitulo("REMOVER CONTA POUPANÇA");
                        System.out.println(removido.toString() + "\nRemovido com sucesso!");
                        linhaPadronizadaFim();
                        pausarExecucao(scanner);
                    } catch (Exception e) {
                        System.out.println("Erro ao remover. Digite um ID válido!");
                    }
                    break;

                case 5:
                    // chamar para atualizar e trato aqui com try-catch
                    nrConta = -1;
                    limparTela();
                    linhaPadronizadaTitulo("ATUALIZANDO CONTA POUPANCA");

                    nrConta = entradaInt(scanner, "Digite o número da conta que deseja alterar: ");
                    ContaPoupanca cp =null;
                    try {
                        cp = buscarContaPoupanca(nrConta);
                    } catch (Exception e) {
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO CONTA POUPANCA");                        
                        System.out.println("Erro. Não foi encontrado esse número de Conta Poupança!");
                        pausarExecucao(scanner);
                    }

                    try {
                        limparTela();
                        linhaPadronizadaTitulo("ATUALIZANDO CONTA POUPANCA");

                        System.out.println("Atenção. É possível alterar somente a Agência da conta!\n");
                        agencia = entradaString(scanner, cp.toString() + "\n\nDigite o número da nova agência da conta: ");
                        
                        cp.setAgencia(agencia);
                        limparTela();

                        linhaPadronizadaTitulo("ATUALIZANDO CONTA POUPANCA");
                        System.out.println("Agência alterada com sucesso para: " + cp.getAgencia());
                        pausarExecucao(scanner);

                        if (cp != null){
                            atualizarContaPoupanca(cp);
                        }

                    } catch (Exception e) {

                        System.out.println("Erro ao alterar a agência!");
                        break;
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
