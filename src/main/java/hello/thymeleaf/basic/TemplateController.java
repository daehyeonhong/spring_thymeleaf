package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    @GetMapping(value = "fragment")
    public String template() {
        return "/template/fragment/fragmentMain";
    }

}
