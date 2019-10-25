package com.hehe.controller;
import com.hehe.entity.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import	java.util.function.Consumer;

/**
 * ClassName: indexController <br/>
 * Description: <br/>
 * date: 2019/10/21 17:09<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
@Controller
public class indexController {
    @GetMapping("/")
    public String index(Model model) {
      //  model.addAttribute("info", "user/list");
        return "login";
    }
    @GetMapping("/to")
    public String to(Model model) {
        model.addAttribute("info", "user/list");
        return "index";
    }

}
