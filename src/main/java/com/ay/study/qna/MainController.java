package com.ay.study.qna;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private final List<Article> articles = new ArrayList<>();
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

    // 세션을 불러오는 방법 1 : HttpSession 활용
    @RequestMapping("/saveSessionAge/{age}")
    @ResponseBody
    public String showSaveSessionAge (@PathVariable Integer age, HttpSession session) {
        session.setAttribute("age", age);

        return "Age : %d 값을 저장했습니다.".formatted(age);
    }

    // 세션을 불러오는 방법 1 : HttpServletRequest 활용
    @RequestMapping("/getSessionAge")
    @ResponseBody
    public String showGetSessionAge (HttpServletRequest req) {
        HttpSession session = req.getSession();
        int age = (int) session.getAttribute("age");

        return "세션에 저장된 나이는 %d살 입니다.".formatted(age);
    }

    @RequestMapping("/addArticle")
    @ResponseBody
    public String addArticle(@RequestParam (defaultValue = "제목 미정") String title,@RequestParam (defaultValue = "미상") String body) {
        Article article = new Article(title, body);
        articles.add(article);

        return "%d번 게시글이 생성되었습니다.".formatted(article.getId());
    }

    @RequestMapping("getArticle/{id}")
    @ResponseBody
    public String getArticle (@PathVariable int id) {
        Article article = articles
            .stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElse(null);

        if (article == null) {
            return "해당 글은 존재하지 않습니다.";
        }

        return """
            <h1> %d번 글 </h1>
            <h3> 제목 : %s </h3>
            <h3> 내용 : %s </h3>
            """.formatted(id, article.getTitle(), article.getBody());
    }

}

@Data
@AllArgsConstructor
class Article {
    private static int lastId = 0;
    private final int id;
    private final String title;
    private final String body;

    public Article(String title, String body) {
        this(++lastId, title, body);
    }
}