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
            Address address = new Address("city", "street", "10000");

            Member member1=new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address copyAddress=new Address(address.getCity(),address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity");

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
