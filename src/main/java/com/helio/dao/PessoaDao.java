package com.helio.dao;

import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaTitulo;
import static com.helio.utilities.CabecalhoPadrao.linhaPadronizadaFim;
import static com.helio.utilities.Pausa.pausarExecucao;
import static com.helio.utilities.ResetaTerminal.limparTela;
import static com.helio.utilities.EntityManagerUtil.getEntityManager;

import java.util.List;

import com.helio.models.Pessoa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class PessoaDao {
    
    public static void criarPersistenciaPessoa (int id, String nome, int idade, String endereco, String cpf){
        Pessoa p = new Pessoa(0 , nome, idade, endereco, cpf);
    
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(p);
        et.commit();

        limparTela();
        linhaPadronizadaTitulo("CADASTRAR PESSOA");
        System.out.println("Pessoa " + p.getNome() + ", cadastrada com sucesso.");
        linhaPadronizadaFim();
        pausarExecucao(null);
    }

    public static Pessoa buscarPessoa(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();// não chama et, apenas em
        Pessoa pessoa = new Pessoa();
        pessoa = em.find(Pessoa.class, id);//JPA não retorna exception, apenas NULL
        
        if (pessoa == null) {// por isso que foi tratado com if mesmo
            return null;
        }
        return pessoa;
    }

    public static Pessoa removerPessoa(int id){
        Pessoa pessoa = buscarPessoa(id);
        if(pessoa != null){
            EntityManager em = getEntityManager();
            EntityTransaction et = em.getTransaction();            

            et.begin();
            em.remove(pessoa);
            et.commit();

            return pessoa;
        }
        return null;
    }

    public static List<Pessoa> buscarTodasAsPessoas() {
        
        EntityManager em = EntityManagerUtil.getEntityManager();
        
        TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
        List<Pessoa> listaDePessoas = query.getResultList();
        limparTela();
        return listaDePessoas;
    }
    
    public static Pessoa atualizarPessoa(Pessoa pessoa){
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(pessoa);//o merge se encarrega de encontrar o objeto pelo ID no BD
        et.commit();


        return null;
    }
}
