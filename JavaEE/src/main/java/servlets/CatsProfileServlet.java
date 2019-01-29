package servlets;

import data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatsProfileServlet extends HttpServlet {
    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat  = ((Map<String,Cat>) req.getSession().getAttribute("cats")).get(req.getQueryString()
                .split("=")[1]);
        PrintWriter out = resp.getWriter();
        if(cat == null){
            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Cat, with name - {{catName}} was not found.</h1>\n"
                            .replace("{{catName}}",req.getQueryString()
                                    .split("=")[1]) +
                    "<a href=\"/cats/all\">Back</a>\n" +
                    "</body>\n" +
                    "</html>");
        }
        else {
            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Cat - {{catName}}</h1>\n".replace("{{catName}}", cat.getName()) +
                    "<hr>\n" +
                    "<h3>Breed: {{catBreed}}</h3>\n".replace("{{catBreed}}", cat.getBreed()) +
                    "<h3>Color: {{catColor}}</h3>\n".replace("{{catColor}}", cat.getColor()) +
                    "<h3>Legs: {{catLegs}}</h3>\n".replace("{{catLegs}}", cat.getNumberOfLegs()) +
                    "<hr>\n" +
                    "<a href=\"/cats/all\">Back</a>\n" +
                    "</body>\n" +
                    "</html>");
        }
    }
}
