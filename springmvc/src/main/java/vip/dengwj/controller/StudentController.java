package vip.dengwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/students")
public class StudentController {
    //@RequestMapping(value = "/students", method = RequestMethod.POST)
    //@ResponseBody
    @PostMapping
    public String save() {
        return "add";
    }

    //@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    //@ResponseBody
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        System.out.println("删除id -> " + id);
        return "delete";
    }

    //@RequestMapping(value = "/students", method = RequestMethod.GET)
    //@ResponseBody
    @GetMapping
    public String list() {
        return "all query";
    }

    //@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    //@ResponseBody
    @GetMapping("/{id}")
    public String list2(@PathVariable Integer id) {
        System.out.println("查询单个 -> " + id);
        return "listById";
    }

    //@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
    //@ResponseBody
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id) {
        System.out.println("修改id -> " + id);
        return "update";
    }
}
