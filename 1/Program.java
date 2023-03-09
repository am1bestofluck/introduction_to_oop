import java.time.LocalDate;


/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        Ware milk1 = new Milk("\"Casuta mea\"",20.5,1,Ware.measurEnum.liter,6,LocalDate.now(),1.0);
        System.out.println(milk1);
    }
}
