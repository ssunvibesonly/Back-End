package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 동작을 한다. -> JPA를 쓰려면 EntityManager를 주입 받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist : 영속하다, 영구저장하다 뜻 -> insert 되고 member에 setId 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       Member member = em.find(Member.class,id); //조회 = find() : ()안에는 조회할 파일이랑, PK값 넣어주면 된다.
        return Optional.ofNullable(member);
    }

    //PK기반이 아니고, 무조건 단건으로 나오는 것이 아닐 경우에는
    // 아래와같이 JPQL을 작성해주어야 한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result= em.createQuery("select m from Member m where m.name= :name",Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        //ctrl+alt+shift + T : List부터 return까지 합쳐진다.
        List<Member> result=em.createQuery("select m from member m",Member.class)
                .getResultList();
        return result;
    }
}
