package com.ay.study.qna.answer;

import static com.ay.study.qna.answer.AnswerDto.*;

import com.ay.study.qna.question.QuestionDto.QuestionDetail;
import com.ay.study.qna.question.QuestionService;
import com.ay.study.qna.user.SiteUser;
import com.ay.study.qna.user.UserService;
import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    private final UserService userService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable int id, @Valid AddAnswer addAnswer, BindingResult bindingResult, QuestionDetail questionDetail, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            questionDetail = questionService.getQuestionDetail(id);
            model.addAttribute("questionDetail", questionDetail);
            return "/question/question_detail";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        answerService.createAnswer(id,addAnswer, siteUser);
        questionDetail = questionService.getQuestionDetail(id);
        model.addAttribute("questionDetail", questionDetail);
        return "/question/question_detail";
    }
}
