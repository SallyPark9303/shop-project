package won.shop;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user, Model model){
        if(user != null){
            model.addAttribute("userName",user.getUsername());
        }

        return "/main";
    }
}
