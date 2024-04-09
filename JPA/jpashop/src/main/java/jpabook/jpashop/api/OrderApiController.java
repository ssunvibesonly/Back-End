package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all){
            order.getMember().getName();
            order.getDelivery().getAddress();
            
            //orderItems와 item들을 뽑기 위해 강제 초기화
            List<OrderItem> orderItems = order.getOrderItems(); 
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all; 
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
       List<Order> orders = orderRepository.findAllByString(new OrderSearch());
       //엔티티 -> stream 변환할 땐 stream() map()사용
       List<OrderDto> collect = orders.stream()
               .map(o->new OrderDto(o))
               .collect(Collectors.toList());

       return collect;
    }
    @Getter
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems; //컬렉션 타입도 엔티티가 있지 않도록 유의하자!!


        public OrderDto(Order order) {
            this.orderId=order.getId();
            this.name=order.getMember().getName();
            this.orderDate=order.getOrderDate();
            this.orderStatus=order.getStatus();
            this.address=order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }
    }

    //컬렉션 타입도 엔티티가 속해있으면 절대 안되기 때문에 DTO로 변환해준다.
    @Getter
    static class OrderItemDto{

        private String itemName; //상품명
        private int orderPrice; //주문 가격
        private int count; //주문 수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
