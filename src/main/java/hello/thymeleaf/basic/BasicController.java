package hello.thymeleaf.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        logger.info("list={}", list);
        logger.info("map={}", map);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    static class User {
        private String username;
        private int age;

        public User(final String username, final int age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public int getAge() {
            return age;
        }

        public void setAge(final int age) {
            this.age = age;
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
