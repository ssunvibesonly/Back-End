package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

                Team team = new Team();
                team.setName("teamA");
                em.persist(team);

                Member member = new Member();
                member.setUsername("teamA");
                member.setAge(10);

                member.setTeam(team);

                em.persist(member);

            em.flush();
            em.clear();

            //left outer join에서 outer는 생략 가능
            //left join에서 left빼면 내부조인
            String query = "select m from Member m  join Team t on m.username=t.name";
            List<Member> result= em.createQuery(query,Member.class)
                                            .getResultList();

            System.out.println("result = " + result.size());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
