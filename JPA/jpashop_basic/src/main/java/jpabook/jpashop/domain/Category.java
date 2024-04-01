package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
     private List<Category> child=new ArrayList<>();

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
            joinColumns = @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_ID")) //연결 테이블 생성  ,연관 관계 주인
    //내가 조인하는 컬럼의 외래키는 CATEGORY_ID로 불릴거고, Item은 ITEM_ID로 불릴거야
    private List<Item> items=new ArrayList<>();

}
