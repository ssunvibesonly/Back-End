package jpabook.jpashop.domain;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.expression.Star;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
