/**
 * Program
 */
public class Program {

    public static void main(String[] args) {
        Plot[] test = new Plot[]{};
        try{
            test = new Plot[]{
            new Circle("external circle",2.5),
            new Square("external square",4.0),
            new Triange("external triangle", 3.0, 4.0, 5.0),
            new Rectangle("external rectangle", 5.0, 6.0)};}
        catch (Exception begone){};
        
        try {
            Triange FailedTriangle = new Triange("none", 1.0, 2.0, 100.0);}
        catch (Exception fails_here){
            System.out.println("exected error");
        }
        
        PlotCollection new_ = new PlotCollection();
        new_.add(test[0]);
        new_.add(test[1]);
        new_.add(test[2]);
        new_.add(test[3]);

        new_.add();//для теста круга             circle 2.0
        new_.add();// для теста квадрата         squarE 1.5
        new_.add();// для теста прямоугольника   rectangle 4.2 4.4
        new_.add();// для теста треугольника     triangle 3.0 4.0 5.0
        
        System.out.println(new_.inventorization());
        System.out.println(new_.reportAllArea());
        System.out.println(new_.reportAllPerimeter());
        }
}