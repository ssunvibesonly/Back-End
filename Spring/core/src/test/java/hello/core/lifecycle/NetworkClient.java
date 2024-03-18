package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//실제 네트워크 연결은 아니고 임시 네트워크 구성
public class NetworkClient{

    private String url; //접속하려는 url

    public NetworkClient() {
        //디폴트 생성자 만들기
        System.out.println("생성자 호출, url = " + url);

    }
    
    public void setUrl(String url){
        //외부에서 setter로 url을 넣을 수 있도록 만들기
        this.url=url;
    }

    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료 시 호출
    public void disConnect(){
        System.out.println("close " + url);
    }


    @PostConstruct
    public void init() {
        // 의존 관계 주입 후 호출되는 메서드
        System.out.println("NetworkClient.init"); //초기화 확인을 위한 메세지
        connect();
        call("초기화 연결 메세지");

    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close"); //스프링 컨테이너가 내려가서 빈들이 죽기 전에 호출 확인 메세지
        disConnect();

    }
}
