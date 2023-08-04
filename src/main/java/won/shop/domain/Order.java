package won.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.extras.springsecurity6.util.SpringSecurityContextUtils;
import won.shop.constant.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order extends BaseEntity  {
    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY) // 연관관계주인은 orderitem 엔티티이고 mappedBy="order" 인 이유는 OrderItem 에 있는 Order 필드가 연관 주인으로 설정되어있기 때문
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

}
