/**
 * тут интерфейс, базовый класс фигур, и сами фигуры
 */

interface operations_i {

    public String getPublicName();

    public String getSimpleName();

    public Double getPerimeter();

    public Double getArea();
}

abstract class Plot implements operations_i{
    protected String simpleName;
    protected String publicName;
    protected Double perimeter;
    protected Double area;
    
    @Override
    public Double getPerimeter() {
        return this.perimeter;
    }

    protected void EvalPerimeter(){
        throw new UnsupportedOperationException(
            "called through childs");
    }

    @Override
    public String getSimpleName() {
        return this.simpleName;
    }

    protected void setSimpleName(String arg){
        this.simpleName = arg;
    }

    @Override
    public Double getArea() {
        return this.area;
    }

    protected void evalArea(){
        throw new UnsupportedOperationException(
            "called through childs");
    }

    @Override
    public String getPublicName() {
        return this.publicName;
    }

    public void setPublicName(String arg) {
        this.publicName = arg;
    }
}

class Triange extends Plot{
    private static final String simpleName_ = "triangle";
    private Double sideA;
    private Double sideB;
    private Double sideC;

    public Triange(String publicName, Double side_a, Double side_b,
    Double side_c){
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.sideA = side_a;
        this.sideB = side_b;
        this. sideC = side_c;
    }

    @Override
    protected void evalArea() {
        Double temp = super.getPerimeter()/2;
        super.area =
            Math.sqrt( 
                temp
                * (temp - this.sideA) 
                * (temp - this.sideB)
                * (temp - this.sideC)
                        );
    
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = this.sideA+ this.sideB + this.sideC;
    }
    
}

class Square extends Plot{
    private static final String simpleName_ = "square";
    private Double side;

    public Square(String publicName, Double side){
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.side = side;
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = this.side*4;
    }

    @Override
    protected void evalArea() {
        super.area= Math.pow(this.side, 2.0);
    }

}

class Rectangle extends Plot{
    private static final String simpleName_ = "rectangle";
    private Double sidea;
    private Double sideb;

    public Rectangle(String publicName, Double sideA, Double sideB){
        if (sideA == sideB){
            throw new ExceptionInInitializerError("Na -ah. Call it square please.");
        }
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.sidea = sideA;
        this.sideb = sideB;
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = 2*(this.sidea+this.sideb);
    }
    

    @Override
    protected void evalArea() {
        super.area = this.sidea*this.sideb;
    }


}

class Circle extends Plot{

    private static final String simpleName_ = "square";
    private Double  radius;

    public Circle(String publicName, Double radius){
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.radius = radius;
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = 2*this.radius * Math.PI;
    }

    @Override
    protected void evalArea() {
        super.area = Math.pow(this.radius, 2) * Math.PI;
    }
    

}
    