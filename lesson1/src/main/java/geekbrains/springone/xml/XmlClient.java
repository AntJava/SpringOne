package geekbrains.springone.xml;

import geekbrains.springone.main.Cup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlClient {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        Cup cup = context.getBean("cup", Cup.class);
        cup.doGulp();

        Cup cupCoffee = context.getBean("coffee", Cup.class);
        cupCoffee.doGulp();
    }
}
