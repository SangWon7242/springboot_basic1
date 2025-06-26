package com.sbs.basic1.boudedContext.home.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller // 스프링부트한테 이 클래스가 컨트롤러임을 알려줌
public class HomeController {
  int no;
  List<Person> personList;

  public HomeController() {
    no = -1;

    personList = new ArrayList<>();
  }


  @GetMapping("/home/main") // /home/main 주소로 GET 요청이 오면 이 메서드가 실행됨
  @ResponseBody // 이 메서드의 실행된 결과를 body에 담아서 응답으로 보냄
  public String showMain() {
    return "안녕하세요, 홈 페이지입니다.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showMain2() {
    return "어서오세요. 홈 페이지2입니다.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showMain3() {
    return "반갑습니다. 홈 페이지3입니다.";
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int showPlus(@RequestParam(defaultValue = "0") int a, int b) {
    /*
    // HttpServletRequest와 HttpServletResponse를 사용하여 요청 파라미터를 가져오는 방법
    int a = Integer.parseInt(req.getParameter("a"));
    int b = Integer.parseInt(req.getParameter("b"));
    return a + b;
    */

    // @RequestParam은 쿼리 파라미터를 매개변수로 전달받는다.
    // @RequestParam은 생략 가능하다.
    // @RequestParam을 생략하는 경우 쿼리 파라미터와 매개변수 이름은 일치해야 한다.

    // 파라미터에 기본값을 주는 경우 @RequestParam(defaultValue = "0") 을 통해 기본값 지정 가능

    return a + b;
  }

  @GetMapping("/home/gugudan")
  @ResponseBody
  public String showGugudan(@RequestParam(defaultValue = "9") int dan, @RequestParam(defaultValue = "9") int limit) {
    StringBuilder sb = new StringBuilder();
    sb.append("<h1>== 구구단 %d단 ==</h1>\n".formatted(dan));

    for (int i = 1; i <= limit; i++) {
      // sb.append 는 문자열을 연결
      sb.append("<div>%d * %d = %d</div>\n".formatted(dan, i, dan * i));
    }

    // sb.toString() 는 StringBuilder 객체를 문자열(String 타입)로 변환
    return sb.toString();
  }

  @GetMapping("/home/gugudan2/{dan}/{limit}")
  @ResponseBody
  public String showGugudan2(@PathVariable int dan, @PathVariable int limit) {
    StringBuilder sb = new StringBuilder();
    sb.append("<h1>== 구구단 %d단 ==</h1>\n".formatted(dan));

    for (int i = 1; i <= limit; i++) {
      // sb.append 는 문자열을 연결
      sb.append("<div>%d * %d = %d</div>\n".formatted(dan, i, dan * i));
    }

    // sb.toString() 는 StringBuilder 객체를 문자열(String 타입)로 변환
    return sb.toString();
  }

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  @ResponseBody
  public int[] showReturnArray() {
    int[] arr = {10, 20, 30, 40, 50};

    return arr;
  }

  @GetMapping("/home/returnList")
  @ResponseBody
  public List<Integer> showReturnList() {
    // List<Integer> list = List.of(10, 20, 30, 40, 50);

    /*
    List<Integer> list = new ArrayList<>){{
      add(10);
      add(20);
      add(30);
      add(40);
      add(50);
    }};
    */

    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);
    list.add(40);
    list.add(50);

    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    /*
    Map<String, Object> map = Map.of(
        "no", ++no,
        "name", "홍길동",
        "age", 20,
        "isMarried", false,
        "height", 175.5,
        "hobbies", new ArrayList<>() {{
          add("독서");
          add("등산");
          add("영화 감상");
        }}
    );
     */

    Map<String, Object> map = new LinkedHashMap<>() {{
      put("no", ++no);
      put("name", "홍길동");
      put("age", 20);
      put("isMarried", false);
      put("height", 175.5);
      put("hobbies", new ArrayList<>() {{
        add("독서");
        add("등산");
        add("영화 감상");
      }});
    }};

    return map;
  }

  @GetMapping("/home/returnArticle")
  @ResponseBody
  public Article showReturnArticle() {
    Article article = new Article(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add("태그1");
      add("태그2");
      add("태그3");
    }});

    return article;
  }

  @GetMapping("/home/returnArticle2")
  @ResponseBody
  public Article2 showReturnArticle2() {
    Article2 article = new Article2(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add("태그1");
      add("태그2");
      add("태그3");
    }});

    return article;
  }

  @GetMapping("/home/returnArticleMapList")
  @ResponseBody
  public List<Map<String, Object>> showReturnArticleMapList() {
    Map<String, Object> articleMap1 = new LinkedHashMap<>() {{
      put("id", 1);
      put("title", "제목1");
      put("content", "내용1");
      put("writer", "김철수");
      put("tags", new ArrayList<>() {{
        add("태그1");
        add("태그2");
        add("태그3");
      }});
    }};

    Map<String, Object> articleMap2 = new LinkedHashMap<>() {{
      put("id", 2);
      put("title", "제목2");
      put("content", "내용2");
      put("writer", "이영희");
      put("tags", new ArrayList<>() {{
        add("태그A");
        add("태그B");
        add("태그C");
      }});
    }};

    Map<String, Object> articleMap3 = new LinkedHashMap<>() {{
      put("id", 3);
      put("title", "제목3");
      put("content", "내용3");
      put("writer", "박지민");
      put("tags", new ArrayList<>() {{
        add("태그X");
        add("태그Y");
        add("태그Z");
      }});
    }};

    List<Map<String, Object>> articleMapList = new ArrayList<>();

    articleMapList.add(articleMap1);
    articleMapList.add(articleMap2);
    articleMapList.add(articleMap3);

    return articleMapList;
  }

  @GetMapping("/home/returnArticleList")
  @ResponseBody
  public List<Article2> showReturnArticleList() {
    List<Article2> articleList = new ArrayList<>();

    articleList.add(new Article2(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add("태그1");
      add("태그2");
      add("태그3");
    }}));

    articleList.add(new Article2(2, "제목2", "내용2", "이영희", new ArrayList<>() {{
      add("태그A");
      add("태그B");
      add("태그C");
    }}));

    articleList.add(new Article2(3, "제목3", "내용3", "박지민", new ArrayList<>() {{
      add("태그X");
      add("태그Y");
      add("태그Z");
    }}));

    return articleList;
  }

  @GetMapping("/home/personTestCase")
  @ResponseBody
  public String personTestCase() {
    personList.add(new Person("홍길동", 11));
    personList.add(new Person("김철수", 22));
    personList.add(new Person("이영희", 33));

    return "테스트 케이스 추가";
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age) {
    Person person = new Person(name, age);
    personList.add(person);

    return "%d번 사람이 추가되었습니다.".formatted(person.getId());
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(@RequestParam(defaultValue = "0") int id) {
    if(id == 0) {
      return "삭제할 사람의 id를 입력해주세요.";
    }

    // v1 : 스트림 방식 사용 안한 버전
    /*
    Person target = null;
    for(Person p : personList) {
      if(p.getId() == id) {
        target = p;
        break;
      }
    }

    if(target == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }
    */

    // v2 : 스트림 방식 사용 버전
    /*
    Person p = personList.stream()
        .filter(person -> person.getId() == id)
        .findFirst()
        .orElse(null);

    if(p == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }
    */

    // v3 : removeIf 메서드 사용 버전
    boolean removed = personList.removeIf(p -> p.getId() == id);
    // 조건에 맞는 결과를 찾으면 true를 반환하고, 없으면 false를 반환
    // true를 반환하면 해당 조건에 맞는 객체가 삭제됨

    if(!removed) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }

    return "%d번 사람이 제거되었습니다.".formatted(id);
  }

  @GetMapping("/home/modifyPerson")
  @ResponseBody
  public String modifyPerson(int id, String name, int age) {

    // v1 : 스트림 방식 사용 안한 버전
    /*
    Person target = null;
    for(Person p : personList) {
      if(p.getId() == id) {
        target = p;
        break;
      }
    }

    if(target == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }
    */

    // v2 : 스트림 방식 사용 버전
    Person p = personList.stream()
        .filter(person -> person.getId() == id)
        .findFirst()
        .orElse(null);

    if(p == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }

    p.setName(name);
    p.setAge(age);

    return "%d번 사람이 수정되었습니다.".formatted(id);
  }

  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople() {
    return personList;
  }
}

class Article {
  private int id;
  private String title;
  private String content;
  private String writer;
  private List<String> tags;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public String getWriter() {
    return writer;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Article() {

  }

  public Article(int id, String title, String content, String writer, List<String> tags) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.writer = writer;
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", writer='" + writer + '\'' +
        ", tags=" + tags +
        '}';
  }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Article2 {
  private int id;
  private String title;
  private String content;
  private String writer;
  private List<String> tags;
}

@AllArgsConstructor
@Data
class Person {
  private static int lastId;
  private final int id;
  private String name;
  private int age;

  static {
    lastId = 0;
  }

  // 생성자 오버로딩
  public Person(String name, int age) {
    this(++lastId, name, age);
  }
}