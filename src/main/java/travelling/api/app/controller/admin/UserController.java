package travelling.api.app.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import travelling.api.app.model.request.user.UserRequest;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.user.UserResponse;
import travelling.api.app.service.UserService;

import java.util.List;

@Controller("adminUser")
@RequestMapping("/admin")
public class UserController {


    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-list")
    public ModelAndView tourPage(
            @RequestParam(defaultValue = "1" ,required = false) Integer page,
            @RequestParam(defaultValue = "5" ,required = false) Integer size){
        ModelAndView mav = new ModelAndView("admin/user/list");
        UserRequest userRequest = new UserRequest(page,size);
        ListResponse<UserResponse> users = userService.findAll(userRequest);
        mav.addObject("data",users);
        return mav;
    }

    @GetMapping("/user-create")
    public String create(){
        return "admin/user/edit";
    }
}
