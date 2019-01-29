package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<h1>Welcome to Fluffy Duffy Munchkin Cats!</h1>");
        out.println("<h3>Navigate through the application using the links below!");
        out.println("<hr>");
        out.println("<a href=/cats/create>Create cat</a>");
        out.println("<br/>");
        out.println("<a href=/cats/all>All cats</a>");
        System.out.println();

    }
}
