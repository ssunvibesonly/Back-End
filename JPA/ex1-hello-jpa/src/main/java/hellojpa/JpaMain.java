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
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();
 
            Member refMember = em.find(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); //프록시 객체 출력
            Hibernate.initialize(refMember); //강제 초기화-> 하이버네이트가 지원하는 것 JPA에는 없음
            //객체의 초기화 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            //em.detach(refMember); //detach하면 영속성 컨텍스트가 더이상 관리하지 않겠다는 뜻
            //.getClass()끼리의 비교는 m1과 m2의 인스턴스 타입(클래스)가 동일한지 물어보는 것이므로 true
            //logic(m1, m2);



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

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username= member.getUsername();
        System.out.println("username = " + username);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }

}
