import java.time.LocalDate;
import java.util.Hashtable;


/**
 * Program
 */
public class Program1 {

    public static void main(String[] args) {
        Ware milk1 = new Milk("\"Casuta mea\"",20.5,1,
            Ware.measurEnum.Bottle,6,LocalDate.now(),1.0);
        Ware lemonade1  = new Lemonade("\"Fanta\"", 30.0, 40, 
            Ware.measurEnum.Bottle, 2);
        Ware egg1 = new Egg("\"chicken egg\"", 20.0, 40, 
            Ware.measurEnum.pack, LocalDate.now().plusDays(3), 49);
        Ware mask1 = new Masks("\"blue masks\"", 0.3, 20, 
            Ware.measurEnum.pack, 100);
        Ware tpaper1 = new HigienicPaper("\"usual tpaper\"", 12.3, 40, 
            Ware.measurEnum.pack, 8, 1);
        Ware diapear1 = new Diaper("\"Usual Diapear\"", 
            123.0, 60, Ware.measurEnum.pack, false, 
            3, 2, 8, 16, Diaper.typeD.singleUse);

        System.out.println(milk1);
        System.out.println(lemonade1);
        System.out.println(egg1);
        System.out.println(mask1);
        System.out.println(tpaper1);
        System.out.println( diapear1);

    }
    // public Hashtable<String, Object> getDetails (Ware obj)
    // {
    //     //Реализовать в классе Program, метод выводящий все данные о товаре.
    //     Hashtable<String, Object> out = new Hashtable<>();
    //     return out;
    // }
}
