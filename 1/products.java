import java.time.LocalDate;

final class Milk extends Drink{
    Integer fat;
    LocalDate validTill;

    public Milk(String name, Double price, Integer quantity, measurEnum measureUnit, Integer fat, 
    LocalDate validTill, Double volume){
        super(name, price, quantity, measureUnit, volume);
        this.fat = fat;
        this.validTill = validTill;
    }
}

final class Limonade extends Drink{

    public Limonade(String name, Double price, Integer quantity, measurEnum measureUnit, double volume){
        super(name, price, quantity, measureUnit, volume);
    }
}

final class Bread extends Food{
    flour ingridFlour;
    enum flour{
        first,second,top_grade
    }
public Bread (String name, Double price, Integer quantity, measurEnum measureUnit, 
    LocalDate validTill, flour ingrid_flour){
        super(name, price, quantity, measureUnit,validTill);
        this.ingridFlour = ingrid_flour;
    }
    
}
final class Egg extends Food{
    Integer PerPack;
    public Egg (String name, Double price, Integer quantity, measurEnum measureUnit, 
    LocalDate validTill, Integer PerPack){
        super(name, price, quantity, measureUnit,validTill);
        this.PerPack = PerPack;
    }
}

final class Masks extends Higienic{
    public Masks (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack){
        super(name, price, quantity, measureUnit, PerPack);
    }

}
final class HigienicPaper extends Higienic{
    Integer layers;
    
    public HigienicPaper (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack, Integer layers){
        super(name, price, quantity, measureUnit, PerPack);
        this.layers = layers;
    }
}

final class Diaper extends ChildFocused{
    Integer size;
    Integer minWeight;
    Integer maxWeight;

    public Diapear( String name, Double price, Integer quantity, 
    measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge, Integer size,
    Integer minWeight, Integer maxWeight){
        super(name, price,quantity,measureUnit, hypoallergenic, legalAge);
        this.size = size;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;


    }
}