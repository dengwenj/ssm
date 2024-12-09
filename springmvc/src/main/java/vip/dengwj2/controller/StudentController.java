package vip.dengwj2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @RequestMapping("/student/save")
    public String save() {
        System.out.println("student save...");
        return "student/save";
    }
}
