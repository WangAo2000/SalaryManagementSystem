package com.example.SalaryManagementSystem.dao;

import com.example.SalaryManagementSystem.bean.Salary;

import java.util.ArrayList;
import java.util.Map;

public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public ArrayList<Salary> querySalary(String userId) {

        ArrayList<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM salary where userid=?;";
        try {
            ArrayList<Map<String, Object>> maps = querySQL(sql, userId);
            for (Map<String, Object> map : maps) {
                String name = (String) map.get("name");
                float basic_salary = (float) map.get("basic_salary");
                float bonus = (float) map.get("bonus");
                float fine = (float) map.get("fine");
                String date = (String) map.get("date");
                Salary salary = new Salary(userId,name,basic_salary,bonus,fine,date);
                salaries.add(salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salaries;
    }

    @Override
    public void updatePassword(String userId, String newPassword) {

        String sql = "UPDATE user_info SET `password`=? WHERE userid=?;";
        try {
            updateSQL(sql,newPassword,userId);
            System.out.println("修改成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
