package vip.dengwj2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.dengwj2.config.SpringConfig2;
import vip.dengwj2.controller.StudentController;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig2.class);
        StudentController bean = ctx.getBean(StudentController.class);
        System.out.println(bean);
    }
}