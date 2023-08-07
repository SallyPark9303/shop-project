package won.shop.Repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import won.shop.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
    //주문 이력 조회
    @Query("select o from Order o "+
    "where o.member.email = :email "+
    "order by o.orderDate desc ")
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    //사용자 전체 주문 수
    @Query("select count(o) from Order o "+
            "where o.member.email = :email ")
    Long countOrder(@Param("email") String email);

}
