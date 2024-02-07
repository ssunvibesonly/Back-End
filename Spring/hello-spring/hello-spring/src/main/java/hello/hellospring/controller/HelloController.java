package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
//    data를 model에 담아서 temlplate의 hello파일에 전달하라
      model.addAttribute("data","hello!!!");

//  thmplate에 있는 hello파일을 찾아서 렌더링 하란 뜻
    return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name,Model model){
    model.addAttribute("name", name);
    return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //문자 반환
    //ResponseBody : http의 body부분에 return에 있는 내용을 직접 넣어주겠다(데이터를 그대로 내려준다는 뜻), viewResolver를 사용하지 않음
    public String helloString(@RequestParam("name") String name){
        return "hello "+name; // name이 "spring"이라면 hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody //객체 반환
    public Hello helloApi(@RequestParam("name") String name){

        Hello hello=new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }



    }


}
