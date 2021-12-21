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


    @GetMapping(value = "layout")
    public String layout() {
        return "template/layout/layoutMain";
    }

    @GetMapping(value = "/layoutExtend")
    public String layoutExtend() {
        return "/template/layoutExtend/layoutExtendMain";
    }

}
