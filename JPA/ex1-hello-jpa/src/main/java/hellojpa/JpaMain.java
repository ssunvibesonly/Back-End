package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //모든 데이터를 변경하는 모든 작업은 JPA에서 꼭 transaction 단위 안에서 작업을 해야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team=new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB=new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1=new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2=new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

           // Member m=em.find(Member.class, member1.getId());

           List<Member> members=em.createQuery("select m from Member m", Member.class)
                           .getResultList();
           // SQL : select * from Member 로 쿼리가 나가서 Member테이블을 가지고 오고,
            //SQL : select * from Member where team=xxx
            // Member클래스에서 Team의 FetchType이 EAGER인 것을 확인하곤 Team테이블도 가져오는 현상이 발생

            tx.commit(); //commit 시점에 데이터베이스에 쿼리가 날아간다.
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
    //프록시 em.getReference()
    //파라메터로 넘어온 값이 Proxy를 쓰는지 안쓰는지 모르기 때문에 JPA에서 타입 비교할 때  ==보다는
    //instanceof사용하기
    private static void logic(Member m1, Member m2) {

        //타입 비교 할 때는 꼭 instanceof쓰자
        System.out.println("m1 == m2 : " + (m1 instanceof Member));
        System.out.println("m1 == m2 : " + (m2  instanceof Member));
    }

}
