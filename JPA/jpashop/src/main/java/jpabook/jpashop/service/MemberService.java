package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//JPA의 데이터 변경이나 로직들은 가급적이면 트랜잭션 안에서 실행되어야 한다. -> 기본적으로 public 메서드들은 트랜잭션에 걸려 들어간다.
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional //"쓰기"에는 readOnly=true가 적용되면 안되므로 @Transactional 따로 설정해준다.
    public Long join(Member member){
        //중복 회원 검증 - 중복 회원일 경우 예외 발생 메서드
        validateDuplicateMember(member);
        //회원 저장
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {

        List<Member> findMembers=memberRepository.findName(member.getName());
        
        //회원 이름이 중복되는 경우 예외 처리
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원 입니다.");

        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
    @Transactional
    public void update(Long id, String name) {
        Member member=memberRepository.findOne(id);
        member.setName(name);
    }
}
