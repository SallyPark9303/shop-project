package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import won.shop.domain.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
