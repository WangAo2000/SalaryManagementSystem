package com.example.SalaryManagementSystem.dao;

import com.example.SalaryManagementSystem.bean.Attendance;
import com.example.SalaryManagementSystem.bean.Salary;
import com.example.SalaryManagementSystem.bean.User;

import java.util.ArrayList;

public interface AdminDao {

    ArrayList<User> queryAllUser();
    User queryUser(String userId);
    void addUser(String userId,String name,String password,String dId,String profession,String idNumber,String bankId);
    void delUser(String userId);
    void updateUser(String userId,String name,String password,String dId,String profession,String idNumber,String bankId);
    ArrayList<Salary> queryAllSalary();
    void addSalary(String userId,String name, float basicSalary, float bonus, float fine, String date);
    void delSalary(String userId);
    Attendance queryAttendance(String userId);
    void updateAttendance(String userId,int requiredTime, int actualTime, int leaveTime, int absenceTime);
    void delAttendance(String userId);


}
