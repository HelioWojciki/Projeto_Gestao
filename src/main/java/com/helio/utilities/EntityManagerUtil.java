package com.helio.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
  
public class EntityManagerUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("esoft_persistence");
    private static EntityManager em;

    // método para obter a instancia do EntityManagerr
    public static EntityManager getEntityManager() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
        return em;
    }

    // Método para fechar o EntityManager
    public static void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    // fechar o EntityManagerFactory (quando o programa terminar)
    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }
}
