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
    
    protected final String negativeValueNote = "Can't have negative sides!";
    
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

    @Override
    public String toString() {
        return String.format("%s %s with perimeter %.2f and area %.2f.",
         this.getSimpleName(), this.getPublicName(), this.perimeter ,this.area);
    }
}

class Triange extends Plot{
    private static final String simpleName_ = "Triangle";
    private Double sideA;
    private Double sideB;
    private Double sideC;

    public Triange(String publicName, Double side_a, Double side_b,
    Double side_c) throws 
        NegativePhysicalValueException
        , UnrealisticTriangleException{
        if (side_a <=0.0 || side_b <= 0.0 || side_c <= 0.0 ){
            throw new NegativePhysicalValueException(
                super.negativeValueNote);
        }
        if (
            side_a > side_b+side_c
            || side_b > side_a+side_c
            || side_c > side_a+side_b){
                throw new UnrealisticTriangleException(
        "Can't build triangle with suggested sides.");
        }
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.sideA = side_a;
        this.sideB = side_b;
        this. sideC = side_c;
        this.EvalPerimeter();
        this.evalArea();
        
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
    @Override
    public String toString() {

        return String.format("%s With sides %.2f, %.2f, %.2f",
            super.toString(), this.sideA, this.sideB, this.sideC);
    }
    
}

class Square extends Plot{
    private static final String simpleName_ = "Square";
    private Double side;

    public Square(String publicName, Double side)
        throws NegativePhysicalValueException
    {
        if (side <= 0.0){
            throw new NegativePhysicalValueException(
                super.negativeValueNote);
        }
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.side = side;
        this.EvalPerimeter();
        this.evalArea();
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = this.side*4;
    }

    @Override
    protected void evalArea() {
        super.area= Math.pow(this.side, 2.0);
    }

    @Override
    public String toString() {
        return String.format("%s and sides %.2f.", 
        super.toString(), this.side);
    }

}

class Rectangle extends Plot{
    private static final String simpleName_ = "Rectangle";
    private Double sidea;
    private Double sideb;

    public Rectangle(String publicName, Double sideA, Double sideB)
        throws ExceptionInInitializerError,
        NegativePhysicalValueException {
        if ( sideA <= 0.0 || sideB <= 0.0){
            throw new NegativePhysicalValueException(super.negativeValueNote);
        }
        if (sideA == sideB){
            throw new ExceptionInInitializerError("Na -ah. Call it square please.");
        }
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.sidea = sideA;
        this.sideb = sideB;
        this.EvalPerimeter();
        this.evalArea();
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = 2*(this.sidea+this.sideb);
    }
    

    @Override
    protected void evalArea() {
        super.area = this.sidea*this.sideb;
    }
    
    @Override
    public String toString() {
        return String.format("%s and sides %.2f/ %.2f", 
        super.toString(), this.sidea, this.sideb);
    }


}

class Circle extends Plot{

    private static final String simpleName_ = "square";
    private Double  radius;

    public Circle(String publicName, Double radius) throws  
        NegativePhysicalValueException{
        if (radius <= 0.0) {
            throw new NegativePhysicalValueException(
                super.negativeValueNote);
        }
        super.setSimpleName(simpleName_);
        super.setPublicName(publicName);
        this.radius = radius;
        this.EvalPerimeter();
        this.evalArea();
    }

    @Override
    protected void EvalPerimeter() {
        super.perimeter = 2*this.radius * Math.PI;
    }

    @Override
    protected void evalArea() {
        super.area = Math.pow(this.radius, 2) * Math.PI;
    }
    
    @Override
    public String toString() {
        return String.format("%s and radius %.2f",
            super.toString(), this.radius);
        
    }

}
    