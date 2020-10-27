package geekbrains.springone.main;

import org.springframework.stereotype.Component;

@Component("liquid")
public class Tea implements Liquid{
    @Override
    public void gulp() {
        System.out.println("Nice tea!");
    }
}
