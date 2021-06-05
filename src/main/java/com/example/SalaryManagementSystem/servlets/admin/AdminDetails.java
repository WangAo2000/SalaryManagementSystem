package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.bean.User;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AdminDetails", value = "/adminDetails")
public class AdminDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        AdminDaoImpl adminDao = new AdminDaoImpl();
        ArrayList<User> users = adminDao.queryAllUser();

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>"
        );
        out.println("<a href=\"/addUser\">添加员工</a>");
        out.println("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>教职工号</th>\n" +
                "        <th>姓名</th>\n" +
                "        <th>部门编号</th>\n" +
                "        <th>职务</th>\n" +
                "        <th>身份证号</th>\n" +
                "        <th>银行卡号</th>\n" +
                "    </tr>");

        for (User user : users) {
            out.println("<tr>\n" +
                    "        <td>"+user.getUserId()+"</td>\n" +
                    "        <td>"+user.getName()+"</td>\n" +
                    "        <td>"+user.getdId()+"</td>\n" +
                    "        <td>"+user.getProfession()+"</td>\n" +
                    "        <td>"+user.getIdNumber()+"</td>\n" +
                    "        <td>"+user.getBankId()+"</td>\n" +
                    "        <td><a href=\"/delUser?userId="+user.getUserId()+"\">删除</a></td>\n" +
                    "        <td><a href=\"/updateUser?userId="+user.getUserId()+"\">修改</a></td>\n" +
                    "        <td><a href=\"/addSalary?userId="+user.getUserId()+"\">添加工资单</a></td>\n" +
                    "        <td><a href=\"/checkAttendance?userId="+user.getUserId()+"\">查看出勤</a></td>\n" +
                    "    </tr>");
        }
        out.println("</table>");
        out.println("</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
