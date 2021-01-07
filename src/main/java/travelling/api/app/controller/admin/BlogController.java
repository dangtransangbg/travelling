package travelling.api.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminBlog")
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/blog-list")
    public String blogPage(){
        return "admin/blog/list";
    }

    @GetMapping("/blog-create")
    public String create(){
        return "admin/blog/edit";
    }
}
