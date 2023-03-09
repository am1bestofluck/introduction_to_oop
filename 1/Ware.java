/**
 * Базовый класс товара
 */
public class Ware {
    protected String name;
    protected Double price;
    protected Integer quantity;
    protected measurEnum measureUnit;
    protected static final Integer defaultQuantity = 0;
    protected static final Double defaultPrice = 0.0;
    protected static enum measurEnum{
        unit,liter,kilo, pack, undefined
    }

    public Ware(String name, Double price, Integer quantity, Ware.measurEnum measureUnit ){
        this.name = name;
        this.price = price;
        this.quantity = (quantity < Ware.defaultQuantity)? Ware.defaultQuantity:quantity;
        this.price = (price < Ware.defaultPrice)? Ware.defaultPrice:price;
        this.measureUnit = measureUnit;
    }
    
    public Ware(String name){
        this(name,defaultPrice,defaultQuantity,measurEnum.undefined);

    }
    
    public String getMeasureUnit(){
        return this.measureUnit.toString();
    }
    
    public void setMeasureUnit(measurEnum newMeasure){
        this.measureUnit = newMeasure;
    }

    public String getName(){
        return this.name;
    }
    
    public void Rename(String newName){
        this.name = newName;
    }

    public Double getPrice(){
        return this.price;
    }
    
    public void setPrice(Double newPrice){
        if (newPrice < Ware.defaultPrice){
            System.out.println("Negative price won't do. Aborting.");
        }
        else{
            this.price = newPrice;
        }
    }

    public Integer getQuantity(){
        return this.quantity;
    }

    public void changeQuantity(Integer delta){
        if (delta > 0){
            this.quantity += delta;
        }
        else {
            if (Math.abs(delta) > this.quantity){
                System.out.println("Don't have as much. Aborting.");
            }
            else{
                
                this.quantity -= Math.abs(delta);
            }
        }
    }
    @Override
    public String toString() {
        return String.format("Ware %s , price: %.2f, currently: %d %s(s).", this.name,this.price,this.quantity, this.measureUnit.name());
    }
    // public static void main(String[] args) {
        // Ware some = new Ware("milk",2.0,20,measurEnum.liter);
        // System.out.println(some);
        // some.Rename("coffee");
        // System.out.println(some.getName());
        // System.out.println(some.getPrice());
        // some.setPrice(-5.0);
        // System.out.println(some.getPrice());
        // some.changeQuantity(50);
        // System.out.println(some.getQuantity());
        // some.changeQuantity(-500);
        // System.out.println(some.getQuantity());
        // System.out.println(some);
        // Ware other= new Ware("banana");
        // other.setMeasureUnit(measurEnum.kilo);
        // other.changeQuantity(20);
        // other.setPrice(35.6);
        // System.out.println(other);
    // }
    }