package geekbrains.springone.main;

public class CupImpl implements Cup{
    private Liquid liquid;

    public CupImpl(Liquid liquid){ this.liquid = liquid; }

    public void doGulp() {
        System.out.println("Sipping");
        liquid.gulp();
    }

    public Liquid getLiquid() {
        return liquid;
    }

    public void setLiquid(Liquid liquid) {
        this.liquid = liquid;
    }
}
