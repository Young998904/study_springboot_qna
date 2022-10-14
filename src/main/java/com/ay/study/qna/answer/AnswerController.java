package com.ay.study.qna.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    @ResponseBody
    public String createAnswer(@PathVariable int id, @RequestParam (defaultValue = "") String content) {
        answerService.createAnswer(id,content);
        return "답변이 등록되었습니다.";
    }
}
