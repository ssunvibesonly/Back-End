package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long Id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
