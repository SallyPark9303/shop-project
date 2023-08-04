package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import won.shop.domain.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
