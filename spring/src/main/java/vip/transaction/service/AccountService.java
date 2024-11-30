package vip.transaction.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    void transfer(String from, String to, long amount);
}
