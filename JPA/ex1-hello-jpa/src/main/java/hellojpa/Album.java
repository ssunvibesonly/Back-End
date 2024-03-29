package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A") //DTYPE에 들어갈 이름 바꿀 때
public class Album extends Item{

    private String artist;
}
