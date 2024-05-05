package controller;

import DAO.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerChange", value = "/delete")
public class ControllerChange extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id") == null ? "" : req.getParameter("id");
        if (!id.isEmpty()) {
            UserDAO userDAO = UserDAO.getInstance();
            User user = userDAO.selectUserById(id);
            userDAO.delete(user);
        }
    }
}
