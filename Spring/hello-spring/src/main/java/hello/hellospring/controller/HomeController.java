package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //localhost:8080 요청이 오면 Controller 먼저 찾아본다.
    //Controller가 없으면 static index.html 을 호출한다.
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
