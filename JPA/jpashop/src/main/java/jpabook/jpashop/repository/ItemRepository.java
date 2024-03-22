package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor //spring data jpa 덕분에 @PersistenceContext 대신 적용할 수 있음
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //item은 jpa저장 전까지 값이 없다.
            em.persist(item);
        }else{
            em.merge(item); //merge는 update처럼 쓰임
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    public List<Item> findAll(){
        List<Item> resultItem=em.createQuery("select i from Item i", Item.class)
                .getResultList();

        return resultItem;
    }
}
