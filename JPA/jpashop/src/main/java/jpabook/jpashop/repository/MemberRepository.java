package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext //JPA가 제공하는 표준 어노테이션
    private EntityManager em;

    public void save(Member member){
        em.persist(member); //JPA가 member를 저장
    }
    public Member findOne(Long id){
        //em.find(타입, pk) - 단건 조회
        return em.find(Member.class,id); //JPA가 ()클래스에서 id를 넘기면 Member를 찾아 반환(단건 조회)
    }

    public List<Member> findAll(){
        List<Member> resultAll= em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPA에서 전부 다 찾을 때 em.createQuery(JPQL 작성,반환 타입)
        return resultAll;
    }

    public List<Member> findName(String name){
        //ex) :name - 파라메터 값
        List<Member> resultName=em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return resultName;
    }

}
