package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import won.shop.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email); // 회원가입시 중복된 이메일 체크
}
