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
        try{
            //비영속
            Member member=new Member();
            member.setId(100L);
            member.setName("HelloJPA");
            
            //영속(이 때 DB에 저장되는 것이 아니다.)
            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
