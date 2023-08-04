package won.shop.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import won.shop.domain.Item;
import won.shop.dto.ItemSearchDto;



public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
