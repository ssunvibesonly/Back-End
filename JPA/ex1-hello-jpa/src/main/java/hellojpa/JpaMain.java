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
            //저장

            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member)
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // member.changeTeam(team); //** 멤버 엔티티를 기준으로 팀을 넣기
            em.persist(member);

            team.addMember(member); //팀 엔티티을 기준으로 멤버를 넣기

            em.flush();
            em.clear();

            Team findTeam=em.find(Team.class,team.getId());
            List<Member> findMember=findTeam.getMembers();

            for (Member m : findMember) {
                System.out.println("member = " + m.getUsername());
            }

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
