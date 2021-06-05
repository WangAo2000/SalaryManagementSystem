package com.example.SalaryManagementSystem.servlets.admin;

import com.example.SalaryManagementSystem.bean.Attendance;
import com.example.SalaryManagementSystem.dao.AdminDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckAttendance", value = "/checkAttendance")
public class CheckAttendance extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");
        String userId = request.getParameter("userId");
        ServletContext context = getServletContext();
        context.setAttribute("userId",userId);
        AdminDaoImpl adminDao = new AdminDaoImpl();
        Attendance attendance = adminDao.queryAttendance(userId);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>");

        out.println("<a href=\"/adminDetails\">返回</a>");
        out.println("<form action=\"/checkAttendance\" method=\"post\">\n" +
                "    <table border=\"1\">\n" +
                "        <tr>\n" +
                "            <th>教职工号</th>\n" +
                "            <th>应出勤时间</th>\n" +
                "            <th>实际出勤时间</th>\n" +
                "            <th>请假时间</th>\n" +
                "            <th>无故缺勤时间</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><input type=\"text\" size=\"5\" value="+userId+" disabled></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"requiredTime\" value="+ attendance.getRequiredTime()+"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"actualTime\" value="+attendance.getActualTime()+"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"leaveTime\" value="+attendance.getLeaveTime()+"></td>\n" +
                "            <td><input type=\"text\" size=\"5\" name=\"absenceTime\" value="+attendance.getAbsenceTime()+"></td>\n" +
                "            <td><input type=\"submit\" value=\"保存\"></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "<input type=\"text\" name=\"userId\" value=\""+userId+"\" hidden>\n" +
                "</form>");

        out.println("</body>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        String  userId = (String) context.getAttribute("userId");
        int requiredTime = Integer.parseInt(request.getParameter("requiredTime"));
        int actualTime = Integer.parseInt(request.getParameter("actualTime"));
        int leaveTime = Integer.parseInt(request.getParameter("leaveTime"));
        int absenceTime = Integer.parseInt(request.getParameter("absenceTime"));
        AdminDaoImpl adminDao = new AdminDaoImpl();

        adminDao.updateAttendance(userId,requiredTime,actualTime,leaveTime,absenceTime);
        response.sendRedirect("/checkAttendance?userId="+userId+"");

    }
}
