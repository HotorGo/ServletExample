package ru.mirea;

import freemarker.cache.FileTemplateLoader;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoServlet extends HttpServlet {
    // todo: нужно создать класс-реализацию интерфейса TodoModel и создать объект этого класса
    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
    private TodoModel model = new MyTodoModel();

    public TodoServlet() throws IOException{
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(".")));
        configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Template template = configuration.getTemplate("todo.html");
        List<TodoItem> items = model.getItems();
        Map<String, Object> data = new HashMap<>();
        data.put("items" , items);
        try {
            template.process(data, resp.getWriter());
        } catch (TemplateException ex) {
            throw new ServletException(ex);
        }
        // todo: нужно вызвать метод model.getItems и вывести в шаблоне
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        String taskText = req.getParameter("taskText");
        if(taskId != null){
            model.delete(Integer.parseInt(taskId));
        }
        else if(taskText != null){
            model.add(taskText);
        }
        resp.sendRedirect("todo.html");
        // todo: в случае добавления будет задан параметр taskText - нужно вызвать model.add(taskText)
        // todo: в случае удаленя будет задан параметр taskId - нужно вызвать model.delete(taskId)
        // todo: после изменения данных нужно вызвать resp.sendRedirect("todo.html") для того, чтобы браузер пере-запросил данные через метод GET
    }
}
