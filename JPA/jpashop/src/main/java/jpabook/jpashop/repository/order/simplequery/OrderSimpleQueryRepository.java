package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private EntityManager em;

    //레포지토리에서 컨트롤러에 의존 관계가 생기면 큰일난다!!!
    //JPA는 기본적으로 엔티티나 valueObject(@Embeddable)만 반환할 수 있다.
    //다른건 new 로 생성해야한다.


    public List<OrderSimpleQueryDto> findOrderDtos() {
        //엔티티로 바로 넘기면 안된다.(식별자로 넘어가기 때문에)
        //address는 값타입이기 때문에 가능
        return em.createQuery("select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o "
                        +"join o.member m "+
                        "join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();

    }
}
