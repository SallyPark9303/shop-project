package won.shop.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import won.shop.Repository.MemberRepository;
import won.shop.domain.Member;
import won.shop.dto.MemberFormDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트시 롤백 수행
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@gmail.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("부산시 행복동");
        memberFormDto.setPassword("1234");
        return  Member.CreateMember(memberFormDto,passwordEncoder);
    }
    @Test
    @DisplayName("회원가입 테스트")
    @Rollback(value = false)
    public void saveMemberTest(){
        Member member = createMember(); // 회원 테스트 데이터 생성
        Member savedmember = memberService.saveMember(member);

        // assertEquals (기댓값, 실제 저장된값)
        assertEquals(member.getEmail(),savedmember.getEmail());
        assertEquals(member.getName(),savedmember.getName());
        assertEquals(member.getAddress(),savedmember.getAddress());
        assertEquals(member.getPassword(),savedmember.getPassword());
        assertEquals(member.getRole(),savedmember.getRole());
        assertEquals(member.getGender(),savedmember.getGender());
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void duplicatedMememberSavedTest(){
        Member member1 = createMember(); // 회원 테스트 데이터 생성
        Member member2 = createMember(); // 회원 테스트 데이터 생성
        Member savedmember1 = memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class,()->{
            memberService.saveMember(member2);
        });
        assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }

}