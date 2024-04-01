package jpabook.jpashop.domain;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.expression.Star;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID") //연관 관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>();

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private  OrderStatus status;

    @OneToOne
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
