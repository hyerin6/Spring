package net.skhu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) { // view 에게 전달될 데이터를 변수 model에 전달
        model.addAttribute("message", "좋은 아침");
        model.addAttribute("now", new Date());
        return "index"; 
    }
}
