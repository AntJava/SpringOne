package geekbrains.springone.config;

import geekbrains.springone.main.Cup;
import geekbrains.springone.main.CupImpl;
import geekbrains.springone.main.Liquid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("geekbrains.springone")
public class AppConfig {
    @Bean(name = "cup")
    public Cup cup(Liquid liquid){
        return new CupImpl(liquid);
    }
}
