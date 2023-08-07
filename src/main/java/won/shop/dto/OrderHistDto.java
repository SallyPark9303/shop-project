package won.shop.dto;

import lombok.Getter;
import lombok.Setter;
import won.shop.constant.OrderStatus;
import won.shop.domain.Order;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
주문 정보를 담는 객체
 */
@Getter
@Setter
public class OrderHistDto {

    private Long orderId;
    private String orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    public void addOrderItemDto(OrderItemDto orderIqItemDto){
        orderItemDtoList.add(orderIqItemDto);
    }
    public OrderHistDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }
}
