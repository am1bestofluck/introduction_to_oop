import java.time.LocalDate;

class Food extends Ware{
    LocalDate validTill;
    public Food(String name, Double price, Integer quantity, measurEnum measureUnit, LocalDate validTill){
        super(name,price,quantity,measureUnit);
        this.validTill =validTill;
    }

}

class Drink extends Ware{
    Double volume;
    public Drink(String name, Double price, Integer quantity, measurEnum measureUnit, double volume){
        super(name,price,quantity,measureUnit);
        this.volume = volume;
    }
}
class Higienic extends Ware{
    Integer PerPack;
    public Higienic(String name, Double price, Integer quantity, measurEnum measureUnit, Integer PerPack){
        super(name,price,quantity,measureUnit);
        this.PerPack = PerPack;
}}

class ChildFocused extends Ware{
    Boolean hypoallergenic;
    Integer legalAge;
    public ChildFocused(String name, Double price, Integer quantity, measurEnum measureUnit, Boolean hypoallergenic, Integer legalAge){
        super(name,price,quantity,measureUnit);
        this.hypoallergenic = hypoallergenic;
        this.legalAge = legalAge;
    }
}

