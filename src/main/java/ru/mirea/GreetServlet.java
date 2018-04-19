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

public class GreetServlet extends HttpServlet {

    private final Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    public GreetServlet() throws IOException {
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(".")));
        configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String yourname = req.getParameter("yourname");
        Template template = configuration.getTemplate("greet.html");
        Map<String, Object> data = new HashMap<>();
        data.put("yourname", yourname);
        List<String> friends = new ArrayList<>();
        friends.add("Иа-Иа");
        friends.add("Пятачок");
        data.put("friends", friends);
        try {
            template.process(data, resp.getWriter());
        } catch (TemplateException ex) {
            throw new ServletException(ex);
        }
    }
}
