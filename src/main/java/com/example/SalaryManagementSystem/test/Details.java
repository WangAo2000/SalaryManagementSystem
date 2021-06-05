package com.example.SalaryManagementSystem.test;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Details", value = "/details")
public class Details extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=GB2312");

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>"
                );
        out.println("<table border=\"1\">");
        for (int i = 0; i <=5; i++){
            out.println("    <tr>\n" +
                    "        <td>row 1, cell 1</td>\n" +
                    "        <td>row 1, cell 2</td>\n" +
                    "    </tr>"
            );
        }
        out.println("</table>");
        out.println("</body>\n" +
                "</html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
