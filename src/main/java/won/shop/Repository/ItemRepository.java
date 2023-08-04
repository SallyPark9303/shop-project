package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import won.shop.domain.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {


}
