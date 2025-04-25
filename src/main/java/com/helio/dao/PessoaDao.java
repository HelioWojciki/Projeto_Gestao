package com.helio.dao;

import static com.helio.utilities.Pausa.pausarExecucao;

import com.helio.models.Pessoa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PessoaDao {
    
    public static void criarPersistenciaPessoa (int id, String nome, int idade, String endereco, String cpf){
        Pessoa p = new Pessoa(0 , nome, idade, endereco, cpf);
    
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.persist(p);
        et.commit();
        System.out.println("Pessoa " + p.getNome() + ", criada com sucesso");
        pausarExecucao(null);
    }
}
