package vip.dengwj.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.dengwj.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    // json 方式传递参数
    @RequestMapping("/jsonlist")
    @ResponseBody
    public String jsonlist(@RequestBody List<String> list) {
        System.out.println("list -> " + list);
        return "{'module': 'jsonlist'}";
    }

    @RequestMapping("/jsonpojo")
    @ResponseBody
    public String jsonpojo(@RequestBody User user) {
        System.out.println("user -> " + user);
        return "{'module': 'jsonpojo'}";
    }

    @RequestMapping("/jsonlistpojo")
    @ResponseBody
    public String jsonlistpojo(@RequestBody List<User> list) {
        System.out.println("list user ->" + list);
        return "{'module': 'jsonlistpojo'}";
    }

    // 日期传递
    @RequestMapping("/date")
    @ResponseBody
    public String date(Date date,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date3) {
        System.out.println("date -> " + date);
        System.out.println("date2 -> " + date2);
        System.out.println("date3 -> " + date3);
        return "{'module': 'date'}";
    }

    // 响应字符串
    @RequestMapping("returnstring")
    @ResponseBody
    public String returnstring() {
        return "returnstring";
    }

    @RequestMapping("reutrnpojo")
    @ResponseBody
    public User reutrnpojo() {
        User user = new User();
        user.setName("朴睦");
        user.setAge(18);
        return user;
    }

    @RequestMapping("reutrnlistpojo")
    @ResponseBody
    public List<User> reutrnlistpojo() {
        List<User> list = new ArrayList<>();
        list.add(new User("朴睦", 24, null));
        list.add(new User("李雷", 18, null));
        return list;
    }
}
