package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
            member.setHomeAddress(new Address("homeCity", "street","10000"));

            //member와 다른 테이블인데도 불구하고 member저장 시 같이 값이 들어감
            //왜냐? 값 타입이기 때문에. 값 타입 컬렉션도 스스로 라이프 사이클이 없다.
            // 모든 라이프 사이클에 대한 것이 엔티티에 의존함
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street","10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street","10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========== START ============= ");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity
            //findMember.getHomeAddress().setCity("newCity"); -> 이렇게 쓰면 사이드이펙트 발생할 수 있어서 절대 안됨!
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));

            //치킨 -> 한식 (단순 String 형태이기 때문에 remove 후 새로 add를 해서 넣어야 한다.)
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //컬렉션마다 다르긴 하지만 기본적으로 컬렉션들은 대부분 대상을 찾을 때 equals를 사용한다.
          /*  findMember.getAddressHistory().remove(new Address("old1", "street","10000"));
            findMember.getAddressHistory().add(new Address("newCity1","street","10000"));*/

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
