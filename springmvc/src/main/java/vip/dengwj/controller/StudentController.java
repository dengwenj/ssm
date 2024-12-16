package vip.dengwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ResponseBody
    public String save() {
        return "add";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        System.out.println("删除id -> " + id);
        return "delete";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
        return "all query";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String list2(@PathVariable Integer id) {
        System.out.println("查询单个 -> " + id);
        return "listById";
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable Integer id) {
        System.out.println("修改id -> " + id);
        return "update";
    }
}
