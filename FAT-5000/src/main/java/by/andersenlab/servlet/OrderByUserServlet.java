package by.andersenlab.servlet;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/orderByUser")
public class OrderByUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idOrder = Integer.parseInt(req.getParameter("idOrder"));

        Order order = new Read<>(Order.class).getOrder(idOrder);

        req.setAttribute("order", order);
        req.getRequestDispatcher("/orderByUser.jsp").forward(req, resp);
    }
}
