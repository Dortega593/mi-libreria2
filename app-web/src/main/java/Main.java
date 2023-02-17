import client.config.AppConfig;
import client.controller.AuthorController;
import client.controller.BookController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) {
        var projectDir = System.getProperty("user.dir");
        staticFiles.externalLocation(projectDir + "/src/main/resources/templates");
        port(8084);
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(BookController.class).init();
        context.getBean(AuthorController.class).init();
    }
}
