package vip.dengwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        return "book/save";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        return "book/delete";
    }
}
