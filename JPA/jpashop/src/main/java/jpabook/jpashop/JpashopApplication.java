package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {
	public static void main(String[] args) {

		SpringApplication.run(JpashopApplication.class, args);

	}

	//이 옵션을 키면 order -> member, member -> orders 양방향 연관 관계를 계속 로딩하게 된다.
	// 단, @JsonIgnore 옵션을 한 곳에 주어야 한다.
	@Bean
	Hibernate5JakartaModule hibernate5JakartaModule() {
		
		Hibernate5JakartaModule hibernate5JakartaModule=new Hibernate5JakartaModule();
		
		//강제 지연 로딩 설정
		//hibernate5JakartaModule.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING,true);
		return hibernate5JakartaModule;
	}

}
