package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {
    private static final Logger logger = getLogger(BasicController.class);

    @GetMapping(value = "/text-basic")
    public String textBasic(final Model model) {
        model.addAttribute("data", "Hello, Spring!");
        return "basic/text-basic";
    }

    @GetMapping(value = "/text-unescaped")
    public String textUnescape(final Model model) {
        model.addAttribute("data", "<b>Hello, Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping(value = "/variable")
    public String variable(final Model model) {
        final User userA = new User("userA", 10);
        final User userB = new User("userB", 20);
        final List<User> list = Arrays.asList(userA, userB);
        final Map<String, User> map = list.stream().collect(Collectors.toMap(User::getUsername, user -> user));

        logger.info("userA={}", userA);
        logger.info("userB={}", userB.getAge());
        logger.info("list={}", list);
        logger.info("map={}", map);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping(value = "/basic-objects")
    public String basicObjects(final HttpSession session) {
        session.setAttribute("sessionData", "Hello, Session!");
        return "basic/basic-objects";
    }

    @GetMapping(value = "/date")
    public String date(final Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping(value = "/link")
    public String link(final Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping(value = "/literal")
    public String literal(final Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @Component(value = "helloBean")
    static class HelloBean {
        public String hello(final String data) {
            return "Hello, " + data + '!';
        }
    }

    static class User {
        private final String username;
        private final int age;

        public User(final String username, final int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("username='" + username + "'")
                    .add("age=" + age)
                    .toString();
        }
    }
}
