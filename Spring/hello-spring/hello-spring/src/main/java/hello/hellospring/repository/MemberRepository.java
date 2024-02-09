package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //반환값 메서드명(타입 파라메터값)
    Optional<Member> findById(Long id);
    Optional<Member> findByname(String name);
    List<Member> findAll();
}
