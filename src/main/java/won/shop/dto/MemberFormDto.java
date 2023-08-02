package won.shop.dto;

import lombok.Getter;
import lombok.Setter;
import won.shop.constant.Gender;

@Getter
@Setter
public class MemberFormDto { // 회원가입화면에서 넘어논 가입정보를 담는 객체
    private String name;
    private String email;
    private String password;
    private String address;
    private Gender gender;
    private Integer contact;


}
