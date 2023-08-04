package won.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue
    @Column(name="cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") // name 에는 조인할 외래키를 입력
    private Member member;
}
