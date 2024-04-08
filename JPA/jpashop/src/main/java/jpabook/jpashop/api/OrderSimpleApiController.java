package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/*
* xToOne(ManyToOne, OneToOne) -> 성능 최적화
* Order
* Order -> Member
* Order -> Delivery
* */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기화
        }
        return  all;
    }

    //엔티티를 DTO로 변환
    //List 그대로 출력하면 안됨 Result<T>로 감싸서 출력 -> 유연성 떨어짐
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders=orderRepository.findAllByString(new OrderSearch());
        orders.stream()
                .map(o->new SimpleOrderDto(o))
    }

    //Dto에서 엔티티를 파라미터로 받는 것은 문제되지 않는다.(중요하지 않은 곳에서 중요한 곳에 의존하는 것이기 때문에)
    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();


        }
    }
}
