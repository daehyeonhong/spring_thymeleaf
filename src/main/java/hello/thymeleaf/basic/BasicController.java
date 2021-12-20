package hello.thymeleaf.basic;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

    @GetMapping(value = "text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello, World!");
        return "/basic/text-basic";
    }

    @GetMapping(value = "text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello, <b>World!</b>");
        return "/basic/text-unescaped";
    }

    @GetMapping(value = "/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> userList = new ArrayList<>();
        userList.add(userA);
        userList.add(userB);

        Map<String, User> userMap = new ConcurrentHashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("userList", userList);
        model.addAttribute("userMap", userMap);

        return "/basic/variable";
    }

    @GetMapping(value = "/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello, World!");
        return "/basic/basic-objects";
    }

    @GetMapping(value = "/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "/basic/date";
    }

    @GetMapping(value = "link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "/basic/link";
    }

    @GetMapping(value = "/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "/basic/literal";
    }

    @GetMapping(value = "operation")
    public String operation(Model model) {
        model.addAttribute("null", null);
        model.addAttribute("data", "Spring!");
        return "/basic/operation";
    }

    @GetMapping(value = "attribute")
    public String attribute() {
        return "/basic/attribute";
    }

    @GetMapping(value = "each")
    public String each(Model model) {
        this.addUser(model);
        return "/basic/each";
    }

    @GetMapping(value = "condition")
    public String condition(Model model) {
        this.addUser(model);
        return "/basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "/basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model) {
        this.addUser(model);
        return "/basic/block";
    }

    private void addUser(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return String.format("Hello, %s!", data);
        }
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class User {

        String username;
        int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }

    }

}
