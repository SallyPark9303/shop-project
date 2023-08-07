package won.shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import won.shop.domain.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    // 대표 이미지

    ItemImg findByItemIdAndRepimgYn(Long itemId,String repimgYn);

}
