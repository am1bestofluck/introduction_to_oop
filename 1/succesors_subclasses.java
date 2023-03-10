import java.time.LocalDate;

class Food extends Ware{
    LocalDate validTill;
    public Food(String name, Double price, Integer quantity, measurEnum measureUnit, LocalDate validTill){
        super(name,price,quantity,measureUnit);
        this.validTill =validTill;
    }
    @Override
    public String toString() {
        return String.format("%s Valid till %s.", super.toString(),this.validTill.toString());
    }

}

class Drink extends Ware{
    Double volume;
    public Drink(String name, Double price, Integer quantity, measurEnum measureUnit, double volume){
        super(name,price,quantity,measureUnit);
        this.volume = volume;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s %s volume is %.2f.", super.toString(),super.measureUnit.name(),this.volume);
    }
}
class Higienic extends Ware{
    Integer PerPack;
    public Higienic(String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack){
        super(name,price,quantity,measureUnit);
        this.PerPack = PerPack;
}
@Override
public String toString() {
    return String.format("%s Pack contains %d units.", super.toString(), PerPack, measureUnit.name());
}}

class ChildFocused extends Ware{
    private String tmp;
    Boolean hypoallergenic;
    Integer legalAge;
    public ChildFocused(String name, Double price, Integer quantity, measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge){
        super(name,price,quantity,measureUnit);
        this.hypoallergenic = hypoallergenic;
        this.legalAge = legalAge;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s, %s allergy.Valid age %d+.", 
        super.toString(), tmp  = (hypoallergenic)? "Doesn't cause":"Causes", this.legalAge);
    }
}

