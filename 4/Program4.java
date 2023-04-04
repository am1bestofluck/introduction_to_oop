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
        
        UTcollection<Integer> b = new UTcollection<>();
        b.add(1);
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(100);
        b.add(100);
        b.add(100);
        b.purge(100);
        b.add(-1);
        System.out.println(b);
        System.out.println("ul");
        System.out.println(b.size());
        b.unlink(b.size()-1);
        System.out.println(b);
        var temp = b.get(0);
        b.edit(0, b.get(b.size()-1));
        b.edit(b.size()-1,temp);
        System.out.println("Интернет говорит что в jav'е операторы не перегружаются... сделал перегрузку функции ._.");
        temp = b.get(0);
        b.getSet(0);
        b.getSet(0,b.get(b.size()-1));
        b.getSet(b.size()-1, temp);
        
        System.out.println(b);
        int flag = 50;
        System.out.println(String.format("%d exists: %b",flag,b.exists(flag)));
        System.out.println(String.format("Index of missing(%d) element= %d",flag,b.getIndex(flag)));
        flag = 4;
        System.out.println(String.format("%d exists: %b",flag,b.exists(flag)));
        System.out.println(String.format("Index of present(%d) element=%d",flag, b.getIndex(flag)));
        System.out.println(String.format("sum mult for %s", b.toString()));
        System.out.println(String.format("sum = %.1f",b.sum()));
        System.out.println(String.format("mult = %.1f", b.mult()));
        


    }
}