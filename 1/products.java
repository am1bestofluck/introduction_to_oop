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
        return String.format("%s Fat concentration: %d %%. Valid till %s.",
         super.toString(), fat, validTill.toString() );
    }
}

class Lemonade extends Drink{

    public Lemonade(String name, Double price, Integer quantity, measurEnum measureUnit, double volume){
        super(name, price, quantity, measureUnit, volume);
        
    }
    @Override
    public String toString() {
        return String.format("%s", super.toString());}
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
    @Override
    public String toString() {
        return String.format("%s Flour sort: %s.",super.toString(),this.ingridFlour.name());
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
        return String.format("%s %d in a pack.", super.toString(),PerPack);
    }
}

class Masks extends Higienic{
    public Masks (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack){
        super(name, price, quantity, measureUnit, PerPack);
    }
    @Override
    public String toString() {

        return String.format("%s", super.toString());
    }

}
class HigienicPaper extends Higienic{
    Integer layers;
    
    public HigienicPaper (String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack, Integer layers){
        super(name, price, quantity, measureUnit, PerPack);
        this.layers = layers;
    }
    @Override
    public String toString() {
        return String.format("%s %d layer(s).", super.toString(), layers);
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
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s Size %d. %s.Child weight between %d and %d. ", super.toString(), this.size, this.typeOf.name(),this.minWeight , this.maxWeight );
    }
}
class Pacifier extends ChildFocused{
    public Pacifier( String name, Double price, Integer quantity, measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge){
        super(name,price,quantity,measureUnit, hypoallergenic, legalAge);
    }
}