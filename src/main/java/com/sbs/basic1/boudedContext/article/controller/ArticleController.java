package com.sbs.basic1.boudedContext.article.controller;

import com.sbs.basic1.boudedContext.article.entity.Article;
import com.sbs.basic1.boudedContext.article.service.ArticleService;
import com.sbs.basic1.boudedContext.base.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor // 필드 중에서 final로 선언된 필드에 대해 생성자를 자동으로 생성
public class ArticleController {
  private final ArticleService articleService;

  @GetMapping("/write")
  public String write() {
    return "usr/article/write";
  }

  @PostMapping("/write")
  @ResponseBody
  public RsData write(String title, String content) {
    if (title == null || title.trim().isBlank()) {
      return RsData.of("F-1", "제목을 입력해주세요.");
    }

    if (content == null || content.trim().isBlank()) {
      return RsData.of("F-2", "내용을 입력해주세요.");
    }

    Article article = articleService.write(title, content);

    return RsData.of("S-1", "%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
  }


  @GetMapping("/list")
  public String showList(Model model) {
    List<Article> articles = articleService.findAllByOrderByIdDesc();

    // findAll
    // SELECT * FROM article;

    // 우리가 원하는 실행 쿼리
    // SELECT * FROM article ORDER BY id DESC;
    // findAll() 메서드는 기본적으로 모든 데이터를 가져오지만, 정렬은 하지 않습니다.
    // 따라서, 정렬이 필요한 경우에는 별도의 쿼리를 작성하거나, JPA의 정렬 기능을 활용해야 합니다.

    model.addAttribute("articles", articles);

    return "usr/article/list";
  }

  @GetMapping("/detail")
  public String showDetail(@RequestParam(defaultValue = "0") Long id, Model model) {
    // redirect : 리다이렉트는 클라이언트에게 다른 URL로 이동하라고 지시하는 것입니다.
    if(id == 0) {
      return "redirect:/article/list";
    }

    Article article = articleService.findById(id);

    if(article == null) {
      return "redirect:/article/list";
    }

    model.addAttribute("article", article);

    return "usr/article/detail";
  }
}
