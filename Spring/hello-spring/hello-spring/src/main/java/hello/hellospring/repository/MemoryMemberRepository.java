package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    private  static Map<Long, Member> store=new HashMap<>();
    private static long sequence=0L; //0,1,2..Key값을 생성해주기 위해

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //최근엔 Null이 출력될 가능성이 있을 경우엔, Optional.ofNullable로 감싸서 출력
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByname(String name) {
        //루프를 돌리는 것이다
        //memeber에서 memeber.getName이 파라메터로 넘어온 name이랑 같은지 확인
        //같은 경우에만 filtering되어 반환
        //없으면 Optional에 null이 포함되어 반환
        return store.values().stream()
                .filter((member -> member.getName().equals(name)))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        
        //Map에 담긴걸 List로 뽑기
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
