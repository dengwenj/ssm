package vip.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.transaction.mapper.AccountMapper;
import vip.transaction.service.AccountService;

import java.io.IOException;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void transfer(String from, String to, long amount) {
        accountMapper.outMoney(from, amount);
        int i = 10 / 0;
        accountMapper.inMoney(to, amount);
    }
}
