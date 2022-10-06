package com.ay.study.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
