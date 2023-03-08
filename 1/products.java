import java.time.LocalDate;

final class Milk extends Drink{
    Integer fat;
    LocalDate validTill;

    public Milk(String name, Double price, Integer quantity, measurEnum measureUnit, Integer fat, LocalDate validTill, Double volume){
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
    enum flour{
        first,second,top_grade
    }
    
}