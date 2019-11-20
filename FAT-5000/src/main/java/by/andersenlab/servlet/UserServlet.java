package by.andersenlab.servlet;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new Read<>(User.class).getUser();

        req.getRequestDispatcher("/user.jsp").forward(req, resp);
    }
}
