package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS) //값이 하나면 ()안에 "" 하나만 써줘도 되지만, 여러개 이상이면 value="" 써줘야 함
public class MyLogger {

    private String uuid;

    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        uuid= UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create" + this);

    }
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close" + this);
    }
}
