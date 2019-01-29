package servlets;

import data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
@SuppressWarnings("unchecked")
public class AllCatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Map<String, Cat> cats = ((Map<String, Cat>) this.getServletContext().getAttribute("cats"));
        final StringBuilder html = new StringBuilder()
                .append("<h1>All Cats</h1>")
                .append("<hr />");

        if (cats.isEmpty()) {
            html
                    .append("<h3>There are no cats. <a href=\"/cats/create\">Create Some!</a></h3>");
        } else {
            cats.keySet()
                    .forEach(catName -> html
                            .append("<p><a href=\"/cats/profile?catName=")
                            .append(catName).append("\">")
                            .append(catName).append("</a>"));
        }

        html.append("<p><a href=\"/\">Back To Home</a></p>");
        resp.getWriter().write(html.toString());
    }
}

