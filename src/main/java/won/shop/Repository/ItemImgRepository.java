package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import won.shop.domain.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
