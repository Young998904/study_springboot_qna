package com.ay.study.qna.question;

import com.ay.study.qna.question.QuestionDto.QuestionInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String showList(Model model) {
        List<Question> questionList = questionService.getQuestionList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }
    @RequestMapping("/question/list/data")
    @ResponseBody
    public List<Question> ListData() {
        System.out.println(questionService.getQuestionList());
        return questionService.getQuestionList();
    }

    @RequestMapping("/question/dtolist")
    public String showDtoList (Model model) {
        List<QuestionInfo> questionDtoList = questionService.getQuestionDtoList();
        model.addAttribute("questionDtoList",questionDtoList);
        return "question_dto_list";
    }

    @RequestMapping("/question/dtolist/data")
    @ResponseBody
    public List<QuestionInfo> DtoListData () {
        return questionService.getQuestionDtoList();
    }
}
