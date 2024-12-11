package vip.dengwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 使用 Controller 定义 bean
@Controller
@RequestMapping("/user")
public class UserController {
    // 设置访问路径
    @RequestMapping("/save")
    // 返回的数据，把响应的东西返回出去
    @ResponseBody
    public String save() {
        System.out.println("user save...");
        return "{'module': 'info'}";
    }

    @RequestMapping("/commonParams")
    @ResponseBody
    public String commonParams(String name, Integer age) {
        System.out.println("姓名：" + name + ", " + "年龄：" + age);
        return "{'module': 'commonParams'}";
    }
}
