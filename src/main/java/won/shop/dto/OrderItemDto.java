package won.shop.dto;

import lombok.Getter;
import lombok.Setter;
import won.shop.Service.OrderService;
import won.shop.constant.OrderStatus;
import won.shop.domain.OrderItem;


/*
조회한 주문 데이터를 화면에 뿌릴 dto
 */
@Getter
@Setter
public class OrderItemDto {

    private String itemNm;
    private int count;
    private int orderPrice;
    private String imgUrl;
    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

}
