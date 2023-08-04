package won.shop.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.crypto.password.PasswordEncoder;
import won.shop.constant.Gender;
import won.shop.constant.Role;
import won.shop.dto.MemberFormDto;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NotBlank
    @Length(min= 3,max=20,message = "최소 3자리부터 20자리까지 입력가능합니다.")
    private  String name;
    @NotBlank
    private  String password;
    @Column(unique = true)
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private  String email;
    @NotBlank
    private  String address;
    @Enumerated(EnumType.STRING)
    //@ColumnDefault // 디비에 저장되는 시점에 값이 없을경우 데이터를 초기화 함. VS @Builder.Default  : 객체를 생성하는 시점에서 값을 초기화해준다.
    @Builder.Default
    private Gender gender = Gender.MAN;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Integer contact;

    public static Member CreateMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));
        member.setRole(Role.USER); // 가입시 role은 무조건 회원으로 저장
        member.setGender(memberFormDto.getGender());
        member.setContact(memberFormDto.getContact());
        return member;
    }


}
