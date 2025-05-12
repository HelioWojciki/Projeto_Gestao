package com.helio.dao;

import java.util.ArrayList;
import java.util.List;

import com.helio.models.Empresa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmpresaDao {
  
    public Empresa criarPersistenciaEmpresa(String nomeEmpresa, String cnpj) {

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        Empresa empresa = new Empresa();
        
        try {
            empresa.setNomeEmpresa(nomeEmpresa);
            empresa.setCnpj(cnpj);
            
            em.persist(empresa);
            em.getTransaction().commit(); 
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close(); 
        }        
        return empresa;
    }

    public Empresa removerEmpresa(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            Empresa empresaEncontrada = em.find(Empresa.class, id);
            if (empresaEncontrada != null) {
                em.remove(empresaEncontrada);
                em.getTransaction().commit();
                return empresaEncontrada;
            }            
        } catch (Exception e) {
            System.out.println("Empresa não encontrada!");
            em.getTransaction().rollback();
            return null;

        } finally{
            if (em.isOpen()) {
                em.close();
            }
        }
        return null;

    }

    public List<Empresa> listarEmpresas(){

        EntityManager em = EntityManagerUtil.getEntityManager();
        List<Empresa> listaEmpresas = new ArrayList<>();

        TypedQuery <Empresa> query = em.createQuery("SELECT e FROM Empresa e", Empresa.class);
        listaEmpresas = query.getResultList();

        return listaEmpresas;
    }

    public Empresa buscarEmpresa(int id){

        EntityManager em = EntityManagerUtil.getEntityManager();
        Empresa empresa = new Empresa();
        empresa = em.find(Empresa.class, id);
        
        if (empresa == null) {
            return null;
        }
        return empresa;
    }
    
    public Empresa atualizarEmpresa(Empresa empresa){
        
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            if (empresa != null) {
                Empresa empresaAtualizada = em.merge(empresa);
                em.getTransaction().commit();
                return empresaAtualizada;
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
}
