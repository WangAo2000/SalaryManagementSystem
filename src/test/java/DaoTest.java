import com.example.SalaryManagementSystem.bean.Attendance;
import com.example.SalaryManagementSystem.bean.Salary;
import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;
import com.example.SalaryManagementSystem.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.Calendar;

public class DaoTest {

    public static void main(String[] args) {
//        AdminDaoImpl adminDao = new AdminDaoImpl();
//        ArrayList<User> users = adminDao.queryAllUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
//        User user = adminDao.queryUser("001");
//        System.out.println(user);
//        adminDao.addUser("004","王奥","123456","1","校长","123456","123");

//        adminDao.delUser("004");

//        adminDao.addSalary("002","李四",3000,100,20,"2021.6");

//        ArrayList<Salary> salaries = adminDao.queryAllSalary();
//        for (Salary salary : salaries) {
//            System.out.println(salary);
//        }

//        adminDao.updateAttendance("003",1,1,1,1);
//        Attendance attendance = adminDao.queryAttendance("002");
//        System.out.println(attendance);

//        UserDaoImpl userDao = new UserDaoImpl();
//        ArrayList<Salary> salaries = userDao.querySalary("001");
//        for (Salary salary : salaries) {
//            System.out.println(salaries);
//        }
//        userDao.updatePassword("001","123456");
//        AdminDaoImpl adminDao = new AdminDaoImpl();
//        adminDao.updateUser("001","张三","123456","1","教师","123123","123");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        System.out.println(year);
        System.out.println(month);

    }
}
