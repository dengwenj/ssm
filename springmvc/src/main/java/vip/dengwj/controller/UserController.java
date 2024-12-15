package vip.dengwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.dengwj.domain.User;

import java.util.Arrays;
import java.util.List;

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

    // 请求参数名与形参不同
    @RequestMapping("/diffParams")
    @ResponseBody
    public String diffParams(@RequestParam("name") String username, Integer age) {
        System.out.println("username -> " + username);
        System.out.println("age -> " + age);
        return "{'module': 'diffParams'}";
    }

    // pojo 对象
    @RequestMapping("/pojoobject")
    @ResponseBody
    public String pojoobject(User user) {
        System.out.println(user);
        return "{'module': 'pojoobject'}";
    }

    // 数组
    @RequestMapping("/array")
    @ResponseBody
    public String array(String[] names) {
        System.out.println(Arrays.toString(names));
        return "{'module': 'array'}";
    }

    // List集合
    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestParam List<String> list) {
        System.out.println(list + " <- list");
        return "{'module': 'list'}";
    }
}
