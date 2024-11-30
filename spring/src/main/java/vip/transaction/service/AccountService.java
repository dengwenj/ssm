package vip.transaction.service;

import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    @Transactional(rollbackFor = Exception.class)
    void transfer(String from, String to, long amount);
}
