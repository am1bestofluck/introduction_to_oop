import java.time.LocalDate;


/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        Ware milk1 = new Milk("\"Casuta mea\"",20.5,1,
            Ware.measurEnum.liter,6,LocalDate.now(),1.0);
        Ware lemonade1  = new Lemonade("Fanta", 30.0, 40, 
            Ware.measurEnum.liter, 2);
        Ware egg1 = new Egg("chicken egg", 20.0, 40, 
            Ware.measurEnum.pack, LocalDate.now().plusDays(3), 49);
        Ware mask1 = new Masks("blue masks", 0.3, 20, 
            Ware.measurEnum.pack, 100);
        

        System.out.println(milk1);
        System.out.println(lemonade1);
        System.out.println(egg1);
        System.out.println(mask1);
    }
}
