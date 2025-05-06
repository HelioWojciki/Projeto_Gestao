package com.helio.dao;

import com.helio.models.ContaPoupanca;
import com.helio.models.Funcionario;
import com.helio.models.Pessoa;
import com.helio.utilities.EntityManagerUtil;

import jakarta.persistence.EntityManager;

public class FuncionarioDao {

    public void criarPersistenciaFuncionario(Pessoa pessoa, double salario, String cargo, ContaPoupanca contaPoupanca){
    
        // Funcionario f = new Funcionario(contaPoupanca, pessoa, salario, cargo); // ok
        Funcionario f = new Funcionario(0, contaPoupanca, pessoa, salario, cargo); // aprimorado ID func

        // //---------------------------- JA OK  debug-------------------------------------------
        // limparTela();
        // System.out.println(f.getCargo() + " CARGO" + f.getNome() + " NOME");
        // pausarExecucao(null);
        // //----------------------------------------------------------------------

        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(f); // persiste o funcionário
            em.getTransaction().commit(); // Finaliza a transação
        } catch (Exception e) {
            em.getTransaction().rollback(); // Reverte caso de erro
            e.printStackTrace();
        } finally {
            em.close(); // fecha
        }

    }
}
