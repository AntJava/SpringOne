package geekbrains.springone.config;

import geekbrains.springone.main.Cup;
import geekbrains.springone.main.Liquid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cupComp")
//@Scope("prototype")
public class CupComp implements Cup {
    private Liquid liquid;

    @Override
    public void doGulp() {
        System.out.println("Sipping");
        liquid.gulp();
    }

    @Override
    public Liquid getLiquid() {
        return liquid;
    }

    @Autowired
    @Qualifier("liquid")
    public void setLiquid(Liquid liquid) {
        this.liquid = liquid;
    }
}
