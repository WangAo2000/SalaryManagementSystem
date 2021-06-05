package com.example.SalaryManagementSystem.servlets.user;

import com.example.SalaryManagementSystem.bean.Salary;
import com.example.SalaryManagementSystem.dao.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CheckSalary", value = "/checkSalary")
public class CheckSalary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Salary selectedSalary = new Salary();
        response.setContentType("text/html;charset=GB2312");
        UserDaoImpl userDao = new UserDaoImpl();
        ServletContext context = this.getServletContext();
        String userId = (String) context.getAttribute("userid");
        String selectedMonth = request.getParameter("month");

        ArrayList<Salary> salaries = userDao.querySalary(userId);
        // 默认单
        if (!salaries.isEmpty()){
            selectedSalary = salaries.get(0);
        }

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>"
        );
        // 下拉月份选择
        out.println("<select onChange=\"location.replace(this.value)\">");
        for (Salary salary : salaries) {
            String date = salary.getDate();
            String[] split = date.split("\\.");
            String month = split[split.length-1];
            if (month.equals(selectedMonth)){
                selectedSalary = salary;

                System.out.println(selectedSalary);

                out.println("<option value=\"/checkSalary?month="+month+"\" selected>"+month+"月</option>");
            }else {
                out.println("<option value=\"/checkSalary?month="+month+"\">"+month+"月</option>");
            }
        }
        out.println("</select>");

        if (salaries.isEmpty()){
            out.println("<p>暂无工资单</p>");
        }else {
            out.println("<table border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <th>教职工号</th>\n" +
                    "        <th>姓名</th>\n" +
                    "        <th>基本工资</th>\n" +
                    "        <th>奖金</th>\n" +
                    "        <th>罚金</th>\n" +
                    "        <th>扣税</th>\n" +
                    "        <th>总额</th>\n" +
                    "        <th>日期</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>"+selectedSalary.getUserId()+"</td>\n" +
                    "        <td>"+selectedSalary.getName()+"</td>\n" +
                    "        <td>"+selectedSalary.getBasicSalary()+"</td>\n" +
                    "        <td>"+selectedSalary.getBonus()+"</td>\n" +
                    "        <td>"+selectedSalary.getFine()+"</td>\n" +
                    "        <td>"+selectedSalary.getTax()+"</td>\n" +
                    "        <td>"+selectedSalary.getTotal()+"</td>\n" +
                    "        <td>"+selectedSalary.getDate()+"</td>\n" +
                    "    </tr>\n" +
                    "</table>");
        }
        out.println("</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
