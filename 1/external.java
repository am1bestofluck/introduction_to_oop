/**
 * external
 */
public class external {

    public static void main(String[] args) {
        Ware q = new Ware("coffe");
        q.setMeasureUnit(Ware.measurEnum.kilo);
        q.changeQuantity(50);
        q.setPrice(100.0);
        System.out.println(q);
        Ware w = new Ware("milk");
        System.out.println(w);
    }
}