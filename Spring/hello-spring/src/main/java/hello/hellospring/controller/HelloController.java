package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMVC(@RequestParam ("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody //ResponseBody는 ViewResolver이런게 없다. 요청한 클라이언트에게 문자가 바로 넘어간다.
    public String helloString(@RequestParam("name") String name){
        
        //@ResponswBody를 사용하여 HTTP 바디부에 name을 직접 넣어주겠다는 의미
        return "hello "+name;
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //static Class로 만들면 Class안에서 Class 생성이 가능하다.(자바에서 정식 지원하는 것)
    //HelloController.Hello
    static class Hello{
        private String name;

        //getter(),setter() -> Java Bean 조약 / property 접근 방식
        //private으로 선언해서 외부에서 접근하지 못하기 때문에 메서드를 통해 접근한다.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
