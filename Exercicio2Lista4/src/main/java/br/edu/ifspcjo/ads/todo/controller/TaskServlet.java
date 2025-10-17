package br.edu.ifspcjo.ads.todo.controller;

import java.io.IOException;
import java.util.List;

import br.edu.ifspcjo.ads.todo.model.Task;
import br.edu.ifspcjo.ads.todo.model.TaskUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public TaskServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String taskData = req.getParameter("tasks");
        TaskUtil util = new TaskUtil();
        List<Task> taskList = util.getTaskList(taskData);

        req.setAttribute("taskList", taskList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);
    }
}