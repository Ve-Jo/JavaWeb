package itstep.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // ViewData["fromServlet"] = "HomeServlet"
        req.setAttribute("fromServlet", "HomeServlet");
        req.setAttribute("pageBody", "index.jsp");
        // return View("index.jsp")
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }

}

/*
* Сервлети - спеціалізовані класи для мережних задач.
* У веб-проєктах грають роль контролерів (API-контролерів)
* Для підключення сервлету потрібно
* або зареєструвати його у web.xml
* або додати сервлетну анотацію
* або зареєструвати його у службі інжекції
* */