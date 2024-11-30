package vip.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.transaction.config.SpringConfig;
import vip.transaction.service.AccountService;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        accountService.transfer("朴睦", "李雷", 10000);
    }
}
