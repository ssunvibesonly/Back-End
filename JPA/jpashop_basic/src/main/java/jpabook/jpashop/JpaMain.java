package jpabook.jpashop;

import jakarta.persistence.*;
import jakarta.transaction.Transaction;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();

        tx.begin();

        try {
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}