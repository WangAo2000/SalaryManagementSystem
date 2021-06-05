package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.bean.Attendance;
import com.example.SalaryManagementSystem.bean.Salary;
import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;
import com.example.SalaryManagementSystem.dao.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "AddSalary", value = "/addSalary")
public class AddSalary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        float basicSalary;
        float bonus;
        float fine;
        String date;
        ServletContext context = getServletContext();
        String msg = (String) context.getAttribute("msg");
        response.setContentType("text/html;charset=GB2312");
        String userId = request.getParameter("userId");
        AdminDaoImpl adminDao = new AdminDaoImpl();
        Attendance attendance = adminDao.queryAttendance(userId);
        User user = adminDao.queryUser(userId);
        if (msg == null) {
            msg = "";
        }
        // 是否有出勤表
        if (attendance.getUserId() == null) {
            basicSalary = 0;
            bonus = 0;
            fine = 0;
        } else {
            // 羁绊工资
            if (user.getProfession() == "teacher") {
                basicSalary = 3000;
            } else if (user.getProfession().equals("professor")) {
                basicSalary = 5000;
            } else if (user.getProfession().equals("headmaster")) {
                basicSalary = 10000;
            } else {
                basicSalary = 1000;
            }
            // 奖金
            if (attendance.getAbsenceTime() == 0 && attendance.getActualTime() >= attendance.getRequiredTime()) {
                bonus = 1000 + 100 * (attendance.getActualTime() - attendance.getRequiredTime());
            } else {
                bonus = 0;
            }
            //罚金
            if (attendance.getRequiredTime() > attendance.getActualTime()) {
                fine = 200 * (attendance.getRequiredTime() - attendance.getActualTime()) + 100 * attendance.getLeaveTime() + 200 * attendance.getAbsenceTime();
            } else {
                fine = 100 * attendance.getLeaveTime() + 200 * attendance.getAbsenceTime();
            }
        }
        // 日期
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        date = year + "." + month;

        Salary salary = new Salary(userId, user.getName(), basicSalary, bonus, fine, date);


        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<a href=\"/adminDetails\">返回</a>");
        out.println("<form action=\"/addSalary\" method=\"post\">\n" +
                "    <table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>教职工号</th>\n" +
                "            <th>姓名</th>\n" +
                "            <th>基本工资</th>\n" +
                "            <th>奖金</th>\n" +
                "            <th>罚金</th>\n" +
                "            <th>交税</th>\n" +
                "            <th>总额</th>\n" +
                "            <th>日期</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"userId\" readonly value=" + salary.getUserId() + " ></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"name\" readonly value=\"" + salary.getName() + "\"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"basicSalary\" readonly value=" + salary.getBasicSalary() + "></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"bonus\" readonly value=" + salary.getBonus() + "></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"fine\" readonly value=" + salary.getFine() + "></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"tax\" readonly value=" + salary.getTax() + "></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"total\" readonly value=" + salary.getTotal() + "></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"date\" value=" + salary.getDate() + "></td>\n" +
                "            <td><input type=\"submit\" value=\"添加\"></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</form>");
        out.println("<p>" + msg + "</p>");
        out.println("</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isExist = false;
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        float basicSalary = Float.parseFloat(request.getParameter("basicSalary"));
        float bonus = Float.parseFloat(request.getParameter("bonus"));
        float fine = Float.parseFloat(request.getParameter("fine"));
        String date = request.getParameter("date");
        UserDaoImpl userDao = new UserDaoImpl();
        AdminDaoImpl adminDao = new AdminDaoImpl();
        ArrayList<Salary> salaries = userDao.querySalary(userId);
        for (Salary salary : salaries) {
            if (salary.getDate().equals(date)) {
                isExist = true;
            }
        }
        if (!isExist) {
            adminDao.addSalary(userId, name, basicSalary, bonus, fine, date);
            response.sendRedirect("/adminDetails");
        } else {
            ServletContext context = getServletContext();
            context.setAttribute("msg", "该月工资单已存在");
            response.sendRedirect("/addSalary?userId="+userId+"");
        }


    }
}
