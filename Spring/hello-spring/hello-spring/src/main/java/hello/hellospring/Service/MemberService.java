package hello.hellospring.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //MemberReoisitory를 MemoryMEmeberRepository 구현체로 생성
    private final MemberRepository memberRepository;

    //memberRepository를 new()로 직접 생성해서 쓰는 것이 아니라 외부에서 넣어주도록 생성자를 만들어준다.
    //늘 new()로 생성해서 만들면, 그건 동일한 인스턴스들을 사용하는게 아니라 다 다른 인스턴스를 이용해 테스트 하는 것이기 때문이다.
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }
    //같은 이름이 있는 중복 회원x
    //이전 결과를 사용하여 변수 정의 ctrl+alt+v : 윈도우
    //직접 꺼내는 걸 권장하지 않기 때문에 Optional로 감싼 것들은 Optional에 있는 메서드들을 사용해서 꺼낸다.
    //바로 findByname옆에 .ifPresent 사용도 가능
    //result.ifPresent(m) : m(멤버: 데이터)에 값이 있으면..
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByname(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    
    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //ID에 해당하는 사용자 찾기
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
