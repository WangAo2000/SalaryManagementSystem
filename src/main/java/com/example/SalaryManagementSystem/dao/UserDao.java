package com.example.SalaryManagementSystem.dao;

import com.example.SalaryManagementSystem.bean.Salary;

import java.util.ArrayList;

public interface UserDao {

    ArrayList<Salary> querySalary(String userId);
    void updatePassword(String userId, String newPassword);

}
