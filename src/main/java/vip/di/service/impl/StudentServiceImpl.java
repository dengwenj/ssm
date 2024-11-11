package vip.di.service.impl;

import vip.di.service.StudentService;

public class StudentServiceImpl implements StudentService {
    @Override
    public void save() {
        System.out.println("StudentServiceImpl save...");
    }
}
