package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
 // @Service 이걸 넣어주어야 Spring이 자기가 관리하는 애인 것을 알 수 있다.
@Transactional // JPA를 쓰려면 항상 트랜잭션이 필요하다.
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    // Repository를 new로 생성하지 않고 외부에서 파라메터 값으로 Repository를 넣어준다.(DI : Dependency Injection)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){

        //시간 측정
        long start = System.currentTimeMillis();

        try {
            //아래 findByName과 같이 로직이 쭉 나오면 메서드로 뽑아주는 것이 좋다
            validateDuplicateMember(member); //중복회원 검증
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("join = "+timeMs+"ms");
        }

        //같은 이름이 있는 중복 회원 X
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{ //ifPresent : 만약 결과값이 존재한다면 동작하게 하는 메서드
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });*/


    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{ //ifPresent : 만약 결과값이 존재한다면 동작하게 하는 메서드
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /*전체 회원 조회*/
    public List<Member> findMembers(){

        long start=System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        }finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("findMembers = "+timeMs+"ms");
        }


    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
