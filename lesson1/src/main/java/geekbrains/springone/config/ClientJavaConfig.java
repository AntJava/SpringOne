package geekbrains.springone.config;

import geekbrains.springone.main.Coffee;
import geekbrains.springone.main.Cup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientJavaConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Cup cupComp = context.getBean("cupComp", Cup.class);
        cupComp.doGulp();

        //Cup cup = context.getBean()
    }
}
