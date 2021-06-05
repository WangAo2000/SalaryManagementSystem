package com.example.SalaryManagementSystem.dao;

import com.example.SalaryManagementSystem.bean.Attendance;
import com.example.SalaryManagementSystem.bean.Salary;
import com.example.SalaryManagementSystem.bean.User;

import java.util.ArrayList;
import java.util.Map;

public class AdminDaoImpl extends BaseDao implements AdminDao{

    @Override
    public ArrayList<User> queryAllUser() {

        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user_info where userid != 'admin';";
        try {
            ArrayList<Map<String, Object>> maps = querySQL(sql);
            for (Map<String, Object> map : maps) {
                String userId = (String) map.get("userid");
                String name = (String) map.get("name");
                String password = (String) map.get("password");
                String did = (String) map.get("did");
                String profession = (String) map.get("profession");
                String id_number = (String) map.get("id_number");
                String bankid = (String) map.get("bankid");
                User user = new User(userId, name, password, did, profession, id_number, bankid);
                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User queryUser(String userId) {

        User user = new User();
        String sql = "SELECT * FROM user_info where userid = ?;";
        try {
            ArrayList<Map<String, Object>> maps = querySQL(sql, userId);
            for (Map<String, Object> map : maps) {
                String name = (String) map.get("name");
                String password = (String) map.get("password");
                String did = (String) map.get("did");
                String profession = (String) map.get("profession");
                String id_number = (String) map.get("id_number");
                String bankid = (String) map.get("bankid");
                user.setUserId(userId);
                user.setBankId(bankid);
                user.setdId(did);
                user.setIdNumber(id_number);
                user.setPassword(password);
                user.setName(name);
                user.setProfession(profession);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(String userId, String name, String password, String dId, String profession, String idNumber, String bankId) {
        String sql = "INSERT into user_info VALUES(?,?,?,?,?,?,?);";
        try {
            int i = updateSQL(sql, userId, name, password, dId, profession, idNumber, bankId);
            System.out.println("插入成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String userId, String name, String password, String dId, String profession, String idNumber, String bankId) {
        String sql = "UPDATE user_info \n" +
                "SET name=?, password=?, did=?,profession=?,id_number=?,bankid=? \n" +
                "WHERE userid=?;";
        try {
            int i = updateSQL(sql, name, password, dId, profession, idNumber, bankId, userId);
            System.out.println("修改成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delUser(String userId) {

        UserDaoImpl userDao = new UserDaoImpl();
        ArrayList<Salary> salaries = userDao.querySalary(userId);
        if (!salaries.isEmpty()){
            delSalary(userId);
            System.out.println("删除工资单");
        }
        Attendance attendance = queryAttendance(userId);
        if (attendance.getUserId()!=null){
            delAttendance(userId);
            System.out.println("删除出勤表");
        }
        String sql = "DELETE FROM user_info WHERE userid=?;";
        try {
            int i = updateSQL(sql, userId);
            System.out.println("删除成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delSalary(String userId) {
        String sql = "DELETE FROM salary WHERE userid=?;";
        try {
            int i = updateSQL(sql, userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delAttendance(String userId) {
        String sql = "DELETE FROM attendance WHERE userid=?;";
        try {
            int i = updateSQL(sql, userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Salary> queryAllSalary() {
        ArrayList<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM salary;";
        try {
            ArrayList<Map<String, Object>> maps = querySQL(sql);
            for (Map<String, Object> map : maps) {
                String userid = (String) map.get("userid");
                String name = (String) map.get("name");
                float basic_salary = (float) map.get("basic_salary");
                float bonus = (float) map.get("bonus");
                float fine = (float) map.get("fine");
                String date = (String) map.get("date");
                Salary salary = new Salary(userid,name,basic_salary,bonus,fine,date);
                salaries.add(salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return salaries;
    }

    @Override
    public void addSalary(String userId, String name, float basicSalary, float bonus, float fine, String date) {
        Salary salary = new Salary(userId, name, basicSalary, bonus, fine, date);
        float tax = salary.getTax();
        float total = salary.getTotal();
        String sql = "INSERT INTO salary VALUES(?,?,?,?,?,?,?,?);";
        try {
            int i = updateSQL(sql, userId, name, basicSalary, bonus, fine, tax, total, date);
            System.out.println("添加成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Attendance queryAttendance(String userId) {
        String sql = "SELECT * FROM attendance WHERE userid=?;";
        ArrayList<Map<String, Object>> maps = null;
        Attendance attendance = new Attendance();
        try {
            maps = querySQL(sql, userId);
            for (Map<String, Object> map : maps) {
                String userid = (String) map.get("userid");
                int required_time = (int) map.get("required_time");
                int actual_time = (int) map.get("actual_time");
                int leave_time = (int) map.get("leave_time");
                int absence_time = (int) map.get("absence_time");
                attendance.setUserId(userId);
                attendance.setRequiredTime(required_time);
                attendance.setActualTime(actual_time);
                attendance.setLeaveTime(leave_time);
                attendance.setAbsenceTime(absence_time);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    @Override
    public void updateAttendance(String userId, int requiredTime, int actualTime, int leaveTime, int absenceTime) {

        Attendance attendance = queryAttendance(userId);

        if (attendance.getUserId()==null){
            String sql = "INSERT INTO attendance VALUES(?,?,?,?,?);";
            try {
                int i = updateSQL(sql, userId, requiredTime, actualTime, leaveTime, absenceTime);
                System.out.println("插入成功");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            String sql = "UPDATE attendance \n" +
                    "SET required_time=?, actual_time=?, leave_time=?,absence_time=? \n" +
                    "WHERE userid=?;";
            try {
                int i = updateSQL(sql, requiredTime, actualTime, leaveTime, absenceTime, userId);
                System.out.println("修改成功");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
