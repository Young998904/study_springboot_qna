package com.ay.study.qna.question;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String showMain(Model model) {
        List<Question> questionList = questionService.getQuestionList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }
}
