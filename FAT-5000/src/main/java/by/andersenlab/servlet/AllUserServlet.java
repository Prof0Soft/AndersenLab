package by.andersenlab.servlet;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/allUser")
public class AllUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Read read = new Read<>(User.class);
        List<User> users = read.getAll();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/allUser.jsp").forward(req, resp);
    }
}
