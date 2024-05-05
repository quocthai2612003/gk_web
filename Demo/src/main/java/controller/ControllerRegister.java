package controller;

import DAO.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerRegister", value = "/register")
public class ControllerRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name") == null ? "" : req.getParameter("name");
        String email = req.getParameter("email") == null ? "" : req.getParameter("email");
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");
        String re_password = req.getParameter("re_password") == null ? "" : req.getParameter("re_password");
        String notify = "";
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || re_password.isEmpty()) {
            notify = "Vui long nhap day du thong tin";
        } else {
            if (!password.equals(re_password)) {
                notify = "password khong trung khop";
            } else {
                User user = new User(name, email, password);
                user.setPassword(user.encryptMd5(password));
                UserDAO userDAO = UserDAO.getInstance();
                if (userDAO.insert(user) > 0) {
                    notify = "dang ky thanh cong";
                } else {
                    notify = "dang ky that bai";
                }
            }
        }
        req.setAttribute("notify", notify);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
