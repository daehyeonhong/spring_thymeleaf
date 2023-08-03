package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {
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
}
