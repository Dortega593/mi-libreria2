package client.config;

import spark.ModelAndView;

import java.util.Map;

public class Util {
    public static String render(Map<String, Object> model, String tempPath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, tempPath));
    }
}
