package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import won.shop.domain.Order;
import won.shop.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
