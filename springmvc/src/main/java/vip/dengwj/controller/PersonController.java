package vip.dengwj.controller;

import org.springframework.web.bind.annotation.*;
import vip.dengwj.domain.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @PostMapping
    public String add(@RequestBody Person person) {
        System.out.println("person -> " + person);
        return "add success";
    }

    @GetMapping
    public List<Person> list() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("朴睦", 24));
        list.add(new Person("韩梅梅", 20));
        return list;
    }
}
