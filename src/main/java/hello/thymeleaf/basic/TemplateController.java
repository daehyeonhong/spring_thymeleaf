package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {
    @RequestMapping(value = "/fragment")
    public String template() {
        return "template/fragment/fragmentMain";
    }

    @RequestMapping(value = "/layout")
    public String layout() {
        return "template/layout/layoutMain";
    }

    @RequestMapping(value = "/layoutExtend")
    public String layoutExtend() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
