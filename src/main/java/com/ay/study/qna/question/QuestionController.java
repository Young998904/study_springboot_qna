package com.ay.study.qna.question;

import com.ay.study.qna.DataNotFoundException;
import com.ay.study.qna.question.QuestionDto.QuestionDetail;
import com.ay.study.qna.question.QuestionDto.QuestionInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

//    @RequestMapping("/question/list")
//    public String showList(Model model) {
//        List<Question> questionList = questionService.getQuestionList();
//        model.addAttribute("questionList",questionList);
//        return "question_list";
//    }
//    @RequestMapping("/question/list/data")
//    @ResponseBody
//    public List<Question> ListData() {
//        System.out.println(questionService.getQuestionList());
//        return questionService.getQuestionList();
//    }

    @RequestMapping("/list")
    public String showDtoList (Model model) {
        List<QuestionInfo> questionDtoList = questionService.getQuestionDtoList();
        model.addAttribute("questionDtoList",questionDtoList);
        return "question_list";
    }

//    @RequestMapping("/question/dtolist/data")
//    @ResponseBody
//    public List<QuestionInfo> DtoListData () {
//        return questionService.getQuestionDtoList();
//    }

    // 질문 상세페이지
    @RequestMapping("/detail/{id}")
    public String showQuestionDetail (@PathVariable int id, Model model) {
        QuestionDetail questionDetail = questionService.getQuestionDetail(id);

        if (questionDetail == null) {
            throw new DataNotFoundException("question not found");
        }

        model.addAttribute("questionDetail", questionDetail);
        return "question_detail";
    }

    // 질문 등록페이지 (GET)
    @GetMapping("/create")
    public String showCreateForm() {
        return "question_form";
    }
    // 질문 등록 (POST)
    @PostMapping("/create")
    public String createQuestion(QuestionDto.RequestQuestionForm requestQuestionForm) {
        questionService.createQuestion(requestQuestionForm.getSubject(), requestQuestionForm.getContent());
        return "redirect:/question/list";
    }
}
