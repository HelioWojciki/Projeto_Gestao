package com.helio.dao;

import java.util.ArrayList;
import java.util.List;

import com.helio.models.ContaPoupanca;
import com.helio.models.Empresa;
import com.helio.models.Funcionario;
import com.helio.models.Pessoa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class FuncionarioDao {

    public Funcionario criarPersistenciaFuncionario(Pessoa pessoa, double salario, String cargo, ContaPoupanca contaPoupanca, Empresa empresa){ 
    
        Funcionario f = new Funcionario(0, contaPoupanca, pessoa, empresa, salario, cargo);
        
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(f); 
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return f;
    }

    public Funcionario buscarFuncionario(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();
        Funcionario func = new Funcionario();
        func = em.find(Funcionario.class, id);
        
        if (func == null) {
            return null;
        }
        return func;
    }

    public List<Funcionario> listarFuncionarios(){

        EntityManager em = EntityManagerUtil.getEntityManager();
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        TypedQuery <Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        listaFuncionarios = query.getResultList();

        return listaFuncionarios;        
    }

    public Funcionario removerFuncionario(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        Funcionario f = new Funcionario();

        try {
            Funcionario func = em.find(Funcionario.class, id);
            if (func != null) {
                em.remove(func);
                em.getTransaction().commit();
                return func;
            }
        } catch (Exception e) {
            System.out.println("Funcionário(a) não encontrado(a)!");
            em.getTransaction().rollback();
            return null;

        } finally{
            if (em.isOpen()) {
                em.close();
            }
        }
        return null;

    }

    public Funcionario atualizarFuncionario(Funcionario func){
        
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            if (func != null) {
                Funcionario funcAtualizado = em.merge(func);
                em.getTransaction().commit();
                return funcAtualizado;
            }
            return null;
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário(a)!");
            em.getTransaction().rollback(); 
            return null;
        } finally {
            if (em.isOpen()) { 
                em.close(); 
            }
        }
        
    }
    
    public Funcionario registrarFuncionario(Funcionario func){

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            Funcionario funcRegistrado = em.merge(func);
            em.getTransaction().commit();
            return funcRegistrado;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário(a)!");
            em.getTransaction().rollback(); 
            return null;

        } finally {
            if (em.isOpen()) { 
                em.close(); 
            }
        }

    }

    public Funcionario demitirFuncionario(Funcionario func){

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            Funcionario funcDemitido = em.merge(func);
            em.getTransaction().commit();
            return funcDemitido;

        } catch (Exception e) {
            System.out.println("Erro ao demitir!");
            return null;
        
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        } 
    }
    
}
