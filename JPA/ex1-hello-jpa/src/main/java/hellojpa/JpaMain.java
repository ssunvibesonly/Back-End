package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

            //Native 쿼리
            em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                    .getResultList();

            //Criteria 사용 준비 -> Criteria는 자바 표준 문법이다.
           /* CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class); //Member를 대상으로 from절
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"),"kim")); //username이 kim인 사람;
            String username = "asdf";
            if(username != null){
                cq = cq.where(cb.equal(m.get("username"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();

            em.createQuery(cq).getResultList();*/


            //Member는 테이블이 아니라 엔티티를 가리킨다.
            // m은 Member 자체를 조회한다.
      /*      List<Member> resultList = em.createQuery(
                            "select m From Member m where m.username like '%kim%'", Member.class)
                    .getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member);
            }*/

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
