package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    //현재 연결된 데이터 저장소가 없어서 데이터 저장소로 사용하기 위한 Map
    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 회원이 가입할 때 마다 sequence 증가
        store.put(member.getId(),member); // member가 가입될 때 시스템은 member ID로 구분하기 위해 key값으로 써준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 결과값이 없으면 Null이 반환되는데 Optional로 반환했으니 Optional로 감싸서 반환(최근 추세)
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //파라메터로 넘어온 name이 멤버의 name과 같은지 확인 -> 같은 경우에만 filtering
                .findAny(); //하나라도 찾는다는 뜻
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void  clearStore(){
        store.clear(); // 메서드 하나가 끝날때마다 남아있는 데이터들을 싹 정리해준다.
    }
}
