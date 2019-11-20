package by.andersenlab.servlet;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/allTour")
public class AllTourServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tours = new Read<>(Tour.class).getAll();
        req.setAttribute("tours", tours);
        req.getRequestDispatcher("/allTour.jsp").forward(req, resp);
    }
}
