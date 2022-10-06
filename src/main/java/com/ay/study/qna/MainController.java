package com.ay.study.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String ShowMain() {
        return "Hello Main";
    }

    @RequestMapping("/sbb")
    @ResponseBody
    public String ShowSbb() {
        return "Hello qna";
    }

    @GetMapping("/test")
    @ResponseBody
    public String showGetTest() {
        return """
            <h1> GetMapping 으로 들어옴 </h1>
            <form method="POST" action="/test">
                <input type="number" name="age" placeholder="나이를 입력하세요" />
                <input type="submit" value="test 페이지에 POST 방식으로 이동" />
            </form>
            """;
    }

    @PostMapping("/test")
    @ResponseBody
    public String showPostTest(@RequestParam(defaultValue = "0") int age) {
        return """
            <h1> PostMapping 으로 들어옴 </h1>
            <h3> 입력된 나이 : %d </h3>
            """.formatted(age);
    }
}
