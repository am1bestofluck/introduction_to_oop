/**
 * Program4
 */
import java.util.Arrays;
import java.util.ArrayList;

public class Program4 {

    public static void main(String[] args) {
        System.out.println("default:");
        UTcollection a = new UTcollection<>();
        System.out.println(a.size());
        System.out.println(a);
        System.out.println("with arg");
        a = new UTcollection(
            new ArrayList<Double>(
                Arrays.asList(1.0,2.0,5.0,22.0,19.6,18.2,300.2))); 
        System.out.println(a);
        System.out.println("bubblesort");
        a.bubbleSort();
        System.out.println(a);
        System.out.println("tweaks");
        a.add(140.0);
        a.add(123123.0);
        a.add(-800.0);
        a.edit(0, -600.0);
        System.out.println(a);
        System.out.println("insertion sort");
        System.out.println(a);
        a.insertionSort();
        System.out.println(a);

        System.out.println("selection sort");
        a.add(150.0);
        a.add(100001312.0);
        a.add(-9999999.0);
        System.out.println(a);
        a.selectionSort();
        System.out.println(a);
        System.out.println("info");
        System.out.println(String.format("max= %s",a.max().toString()));
        System.out.println(String.format("min=%s", a.min().toString()));


    }
}