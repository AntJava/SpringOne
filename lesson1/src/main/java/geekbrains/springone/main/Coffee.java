package geekbrains.springone.main;

import org.springframework.stereotype.Component;

@Component("coffee")
public class Coffee implements Liquid{
    @Override
    public void gulp() {
        System.out.println("Nice coffee!");
    }
}
