package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //모든 데이터를 변경하는 모든 작업은 JPA에서 꼭 transaction 단위 안에서 작업을 해야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
           Member member=new Member();
           member.setUsername("member1");

           em.persist(member);

           Team team=new Team();
           team.setName("teamA");
           team.getMembers().add(member);

           em.persist(team);

            tx.commit(); //commit 시점에 데이터베이스에 쿼리가 날아간다.
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
