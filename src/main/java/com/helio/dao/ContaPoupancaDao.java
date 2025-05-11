package com.helio.dao;

import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaFim;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;

import java.util.ArrayList;
import java.util.List;

import com.helio.models.ContaPoupanca;
import com.helio.models.Pessoa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ContaPoupancaDao {    
    // gerencia os dados de uma pessoa já existente para cadastrar a conta
    public static ContaPoupanca criarPersistenciaContaPoupanca (Pessoa pessoa, double saldoInicial, String conta, String agencia){
        
        ContaPoupanca cp = new ContaPoupanca(0, pessoa.getNome(), saldoInicial, conta, agencia, pessoa);
    
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(cp);
        et.commit();

        limparTela();
        linhaPadronizadaTitulo("CADASTRAR CONTA POUPANÇA");
        System.out.println("Conta " + cp.getConta() + ", cadastrada com sucesso.");
        linhaPadronizadaFim();
        pausarExecucao(null);
        
        return cp;
    }

    public static ContaPoupanca buscarContaPoupanca (int numeroConta){
        EntityManager em = EntityManagerUtil.getEntityManager();
        String conta = String.valueOf(numeroConta); // apos alterado de Int para String, passo esse dado pro.setParameter pra buscar em typedQuery

        TypedQuery<ContaPoupanca> query = em.createQuery(
            "SELECT c FROM ContaPoupanca c WHERE c.conta = :conta", ContaPoupanca.class
        );
        query.setParameter("conta", conta); // informa para a query

        // se não achar ele retorna NoResultException e não null
        return query.getSingleResult(); // apenas retornar, quem vai tratar é o SubmenuContaPoupanca, melhor
    }

    public static List<ContaPoupanca> listarContasPoupancas(){

        EntityManager em = EntityManagerUtil.getEntityManager();        
        TypedQuery<ContaPoupanca> query = em.createQuery("SELECT t FROM ContaPoupanca t", ContaPoupanca.class);
        
        List<ContaPoupanca> listaDeContaPoupancas = new ArrayList<>();
        listaDeContaPoupancas = query.getResultList();

        return listaDeContaPoupancas;
    }

    public static ContaPoupanca removerContaPoupanca(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            ContaPoupanca cp = em.find(ContaPoupanca.class, id);
            if (cp != null) {   
                em.remove(cp); // remove a conte que retornou da busca
                em.getTransaction().commit(); // fecha o em
                return cp; // retorna pra ser exibido como removido
            }
        } catch (Exception e) {
            System.out.println("Conta Poupança não encontrada!");
            return null;
        }
        return null;

    }

    public static ContaPoupanca atualizarContaPoupanca(ContaPoupanca cp){
        EntityManager em = EntityManagerUtil.getEntityManager();
        
        em.getTransaction().begin();

        try {
            if (cp != null) {
                ContaPoupanca cpAtualizada = em.merge(cp); // o merge retorna a nova instância atualizada no banco
                em.getTransaction().commit();
                return cpAtualizada;
            }else{
                em.getTransaction().rollback(); // mais seguro voltar ao estado anterior
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a Conta Poupança!");
            em.getTransaction().rollback(); // volta ao estado anterior tmb
            return null;
        } finally {
            if (em.isOpen()) { // se ainda estiver aberto ele vai fechar o em
                em.close();
            }
        }
    }

}
