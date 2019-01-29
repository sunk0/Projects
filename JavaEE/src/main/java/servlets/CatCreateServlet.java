package servlets;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import data.Cat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String htmlCreateCat = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Create a cat!</h1>\n" +
                "<form action=\"create\" method=\"post\">\n" +
                "    <label>Name:\n" +
                "        <input type=\"text\"  name=\"name\">\n" +
                "    </label>\n" +
                "    <br>\n" +
                "    <label>Breed:\n" +
                "        <input type=\"text\"  name=\"breed\">\n" +
                "    </label>\n" +
                "    <br>\n" +
                "    <label>Color:\n" +
                "        <input type=\"text\"  name=\"color\">\n" +
                "    </label>\n" +
                "    <br>\n" +
                "    <label>Legs:\n" +
                "        <input type=\"text\"  name=\"legs\">\n" +
                "    </label>\n" +
                "    <br>\n" +
                "    <button type=\"submit\">Create Cat!</button>\n" +
                "\n" +
                "</form>\n" +
                "<br>\n" +
                "<a href=\"/\">Back to home</a>\n" +
                "</body>\n" +
                "</html>";



        out.print(htmlCreateCat);

//        out.println("<h1>Create cat</h1>");
//        out.println("<form action=/cats/create method=post>" +
//                "  Name: <input type= text name=name><br>" +
//                "  Breed: <input type=text name=breed><br>" +
//                "  Color: <input type=text name=color><br>" +
//                "  Number of legs: <input type=text name=legs><br>" +
//                "  <input type=submit value=Create cat><br>" +
//                "  </form>");
//
//
//        out.println("<a href=/>Back to home</a>");
//BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\sunk0\\IdeaProjects\\JavaEE\\src\\main\\java\\data\\test.html"))));
//StringBuilder builder = new StringBuilder();
//String html;
//while((html = reader.readLine())!=null){
//    builder.append(html).append(System.lineSeparator());
//}
//out.print(html);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat(req.getParameter("name")
        ,req.getParameter("breed")
        ,req.getParameter("color")
        ,req.getParameter("legs"));


        if(req.getSession().getAttribute("cats")==null){
            req.getSession().setAttribute("cats",new LinkedHashMap<>());
        }
        ((Map<String,Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(),cat);


        resp.sendRedirect("/"+"cats/profile?catName="+req.getParameter("name"));
    }
}


