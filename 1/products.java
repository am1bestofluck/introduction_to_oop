import java.time.LocalDate;

class Milk extends Drink{
    Integer fat;
    LocalDate validTill;

    public Milk(String name, Double price, Integer quantity, measurEnum measureUnit, Integer fat, 
    LocalDate validTill, Double volume){
        super(name, price, quantity, measureUnit, volume);
        this.fat = fat;
        this.validTill = validTill;
    }
    @Override
    public String toString() {
        return String.format("%s Bottle volume: %.2f %s (s). Fat concentration: %d %%. Valid till %s.",
         super.toString(), volume, measureUnit.name(), fat, validTill.toString() );
    }
}

class Lemonade extends Drink{

    public Lemonade(String name, Double price, Integer quantity, measurEnum measureUnit, double volume){
        super(name, price, quantity, measureUnit, volume);
        
    }
    @Override
    public String toString() {
        return String.format("%s Comes in bottles of %.2f %s.", super.toString(), volume, measureUnit.name());}
}

class Bread extends Food{
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
class Egg extends Food{
    Integer PerPack;
    public Egg (String name, Double price, Integer quantity, measurEnum measureUnit, 
    LocalDate validTill, Integer PerPack){
        super(name, price, quantity, measureUnit,validTill);
        this.PerPack = PerPack;
    }
    @Override
    public String toString() {
        return String.format("%s , %d in a pack, valid till %s.", super.toString(),PerPack,validTill.toString());
    }
}

class Masks extends Higienic{
    public Masks (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack){
        super(name, price, quantity, measureUnit, PerPack);
    }
    @Override
    public String toString() {

        return String.format("%s In packs of %d.", super.toString(),PerPack);
    }

}
class HigienicPaper extends Higienic{
    Integer layers;
    
    public HigienicPaper (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack, Integer layers){
        super(name, price, quantity, measureUnit, PerPack);
        this.layers = layers;
    }
}

class Diaper extends ChildFocused{
    Integer size;
    Integer minWeight;
    Integer maxWeight;
    typeD typeOf;
    public static enum typeD{
        singleUse, reUsable
    }

    public Diaper( String name, Double price, Integer quantity, measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge, Integer size, Integer minWeight, Integer maxWeight, typeD typeOfDiapear){
        super(name,price,quantity,measureUnit, hypoallergenic, legalAge);
        this.size = size;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.typeOf = typeOfDiapear;


    }
}
class Pacifier extends ChildFocused{
    public Pacifier( String name, Double price, Integer quantity, measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge){
        super(name,price,quantity,measureUnit, hypoallergenic, legalAge);
    }
}