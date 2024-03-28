package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long Id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private Member member;
    private List<OrderItem> orderItems=new ArrayList<>();

    private String city;
    private String street;
    private String zipcode;
    private DelivertStatus status;
}
